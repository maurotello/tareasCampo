package com.maurotellodev.tareascampo.navigation.viewmodel

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maurotellodev.tareascampo.data.databases.DatabaseHelper
import com.maurotellodev.tareascampo.data.interfaces.DataStoreRepository
import com.maurotellodev.tareascampo.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPFile
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val context: Context,
    private val repository: DataStoreRepository,
    private val jobRepositoryImp: JobRepository,
    private val databaseHelper: DatabaseHelper
) : ViewModel() {

    var campos:String = ""
    var inserts:String = ""
    var sql:String = ""
    var tableEdit = ""


    fun getJob() {
        viewModelScope.launch(Dispatchers.IO) {
            val job = jobRepositoryImp.getJobs()
            Log.i("aris", "OptionsViewModel: ${job.toString()}")
        }
    }

    suspend fun downloadFileFromFTP(
        /*
        server: String,
        port: Int,
        username: String,
        password: String,
        remoteFilePath: String,
        localFilePath: String
         */
    ) {
        withContext(Dispatchers.IO) {
            val ftpClient = FTPClient()
            var success = false
            try {
                connectServer(ftpClient)
                // Cambiar al directorio "gesis"
                val changeGesisFolder = ftpClient.changeWorkingDirectory("gesis")
                if (changeGesisFolder) {
                    Log.i("aris", "Cambié a la carpeta gesis")
                    // Cambiar al directorio "tello" dentro de "gesis"
                    val changeFolderUser = ftpClient.changeWorkingDirectory("tello")

                    if (changeFolderUser) {
                        Log.i("aris", "Cambié a la carpeta tello")
                        val filesUsers = ftpClient.listFiles()
                        uploadFiles(ftpClient, filesUsers)
                    }
                }
                success = true
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                ftpClient.logout()
                ftpClient.disconnect()
            }
            success
        }
    }

    private fun uploadFiles(ftpClient: FTPClient, filesUsers: Array<FTPFile>) {
        try {
            for (fileUser in filesUsers) {
                if (fileUser.name != "." && fileUser.name != "..") {
                    //conexion.json
                    Log.i("aris", "Archivo: ${fileUser.name}")

                    val externalFilesDir = context.getExternalFilesDir(null)
                    if (externalFilesDir != null) {
                        val gesisFolder = File(externalFilesDir, "gesis")
                        val archivoLocal = File(gesisFolder, fileUser.name)
                        val archivoLocalOutput = FileOutputStream(archivoLocal)
                        val resultadoDescarga =
                            ftpClient.retrieveFile(fileUser.name, archivoLocalOutput)
                        archivoLocalOutput.close()
                        if (resultadoDescarga) {
                            Log.i(
                                "aris",
                                "Archivo descargado exitosamente: ${archivoLocal.absolutePath}"
                            )
                        } else {
                            Log.e("aris", "Error al descargar el archivo: ${fileUser.name}")
                        }
                    }

                } else {
                    Log.i("aris", "Ningún archivo en tello")
                }
            }
        } catch (e: SecurityException) {
            // Manejar la excepción de permisos
            e.printStackTrace()
            Log.e("aris", "Permiso denegado para realizar la operación de E/S")
        } catch (e: IOException) {
            // Manejar la excepción de E/S
            e.printStackTrace()
            Log.e("aris", "Error de E/S al descargar el archivo")
        }

    }

    private fun connectServer(ftpClient: FTPClient): Boolean {
        return try {
            ftpClient.connect("89.117.7.119", 21)
            ftpClient.login("u828369731.maurotellodev.com", "desaTELLO2014")
            ftpClient.enterLocalPassiveMode()
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE)
            Log.i("aris", "CONECTADO AL SERVIDOR")
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun processFileJson() {
        withContext(Dispatchers.IO) {
            val externalFilesDir = context.getExternalFilesDir(null)
            if (externalFilesDir != null) {
                val gesisFolder = File(externalFilesDir, "gesis")
                if (gesisFolder.exists() && gesisFolder.isDirectory) {
                    val filesJson = gesisFolder.listFiles { file ->
                        file.isFile && file.name.lowercase().endsWith(".json")
                    }
                    for (jsonFile in filesJson) {
                        processJson(jsonFile)
                    }
                }
            }
        }
    }

    private fun processJson(file: File): Boolean {
        if (file.name == "nuevo-relevamiento.json") {
            val content = file.readText()
            val contentJson = JSONObject(content)
            // Obtén el valor de "table"
            val tableValue = contentJson.getString("table")
            // Obtén el valor de "columns"
            val columnsValue = contentJson.getJSONArray("columns")

            // Acá debería crear la tabla con el tableValue y las Columnas

            processColumns(columnsValue, tableValue)
            val columnMap: Map<String, Map<String, Any>> = parserColumnsArray(columnsValue)
            Log.i("aris", "ColumnMap: $columnMap")

            // Mapear las columnas a sus nombres y tipos
            // Acceder a la sección "data"
            val dataValue = contentJson.getJSONArray("data")

            val camposArray = campos.split(", ")

            for (i in 0 until dataValue.length()) {
                val jsonObject = dataValue.getJSONObject(i)
                val contentValues = ContentValues()
                for (j in camposArray.indices) {
                    val campo = camposArray[j]
                    val valor = jsonObject.optString(campo, "")

                    contentValues.put(campo, valor)
                }
                val dbHelper = DatabaseHelper(context)
                val database = dbHelper.writableDatabase
                val insertSuccessful = databaseHelper.populatePrincipalTable(database, tableValue, contentValues)
                if (insertSuccessful) {
                    // La inserción fue exitosa
                    Log.i("aris", "La inserción de los datos resulto Satisfactoria")
                } else {
                    // Hubo un error durante la inserción
                    Log.i("aris", "Ocurrió un error al intentar cargar los datos de la tabla Relevamiento")
                }

            }


            /** Conociendo los campos para agregar los datos sería asi

            // Ahora puedes acceder a los datos
            Log.i("aris", "Valor de 'table': $tableValue")
            // Para acceder a los datos dentro de "columns"
            for (i in 0 until columnsValue.length()) {
            val column = columnsValue.getJSONObject(i)
            val name = column.getString("name")
            val type = column.getString("type")
            val length = column.getString("lenght") // Nota la errata en el nombre de la clave
            val ordenList = column.getInt("ordenList")
            val options = column.getString("options")
            Log.i(
            "aris",
            "Columna $i: Nombre=$name, Tipo=$type, Longitud=$length, OrdenList=$ordenList, Opciones=$options"
            )

            val miEntidad = MyEntity(
            name = name,
            type = type,
            longitude = length,
            orderList = ordenList,
            options = options
            )
            myEntities.add(miEntidad)
            }
            // Guardar las entidades en la base de datos
            viewModelScope.launch {
            try {
            for (miEntidad in myEntities) {
            Log.i("aris", "Agregando entidad: $miEntidad")
            myEntityDao.insertEntity(miEntidad)
            Log.i("aris", "Entidad agregada")
            }
            Log.i("aris", "Todas las entidades se agregaron correctamente a la base de datos")
            } catch (e: Exception) {
            Log.e("aris", "Error al agregar entidades a la base de datos: ${e.message}")
            }
            }
            }*/

        }
        return true
    }


    private fun processColumns(columnsArray: JSONArray, table: String){
        val dbHelper = DatabaseHelper(context)
        val database = dbHelper.writableDatabase
        /* TODO esto después tengo que sacarlo es ahora para borrar las tablas*/
        databaseHelper.dropAllTables(database)
        campos = ""
        sql = ""
        tableEdit = ""

        sql = "CREATE TABLE IF NOT EXISTS $table (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"

        // Esta tabla es para asignarle a cada campo si es editable o no
        databaseHelper.createTablePermissions(database, table)

        // Esta table es para saber que campos se visualizan en el listado
        databaseHelper.createTableInList(database, table)

        for (i in 0 until columnsArray.length()) {
            val column = columnsArray.getJSONObject(i)
            val columnName =  column.getString("name")
            val orderInList =  column.getString("ordenList")
            val orderInListInt = orderInList.toInt()
            val lenghtColumn = column.getString("lenght")
            val editColumn = column.getString("edit")
            val editColumnInt = editColumn.toInt()

            when (column.getString("type")){
                "COMBO" ->{
                    Log.i("aris", "Column: $column")
                    campos += "$columnName, "
                    createTableCombo(column)
                    sql += "$columnName VARCHAR($lenghtColumn), "
                }
                "VARCHAR" ->{
                    campos += "$columnName, "
                    Log.i("aris", "Column String: $column")
                    sql += "$columnName VARCHAR($lenghtColumn), "

                }
                "INT" ->{
                    campos += "$columnName, "
                    Log.i("aris", "Column Int: $column")
                    sql += "$columnName INT($lenghtColumn), "
                }
                "FLOAT" ->{
                    campos += "$columnName, "
                    Log.i("aris", "Column Long: $column")
                    sql += "$columnName FLOAT, "
                }
            }
            databaseHelper.populateTableInList(database, table, columnName, orderInListInt)
            databaseHelper.populateTablePermissions(database, table, columnName, editColumnInt)
        }
        sql += "newLatitude FLOAT, newLongitude FLOAT, processDate DATE, endDate DATE, Observations TEXT, process INTEGER(1), finished INTEGER(1), user VARCHAR(200))"
        campos += "newLatitude, newLongitude, processDate, endDate, Observations, process, finished, user"
        Log.i("aris", "List of Campos: $campos")
        Log.i("aris", "SQL: $sql")
        databaseHelper.createPrincipalTable(database, sql)
    }

    private fun createTableCombo(column: JSONObject?) {
        try {
            val dbHelper = DatabaseHelper(context)
            val database = dbHelper.writableDatabase
            val nameTable = column?.get("name")
            val nameTableCombo = nameTable.toString() + "_combo"
            val options = column?.get("options").toString()
            // "Columna, Mensula, Alumbrado, PosteMadera"
            val optionsList = options.split(",")
            databaseHelper.createTableCombo(database, nameTableCombo)
            for (option in optionsList) {
                populateTableOption(database, nameTableCombo, option)
            }
        } catch (sqlException: SQLException) {
            // Excepción específica de SQLite
            sqlException.printStackTrace() // Imprime la traza de la excepción en la consola
            // Manejar la excepción de SQLite (por ejemplo, mostrar un mensaje de error)
        } catch (otherException: Exception) {
            // Otras excepciones generales
            otherException.printStackTrace() // Imprime la traza de la excepción en la consola
            // Manejar otras excepciones (por ejemplo, mostrar un mensaje de error genérico)
        }

    }

    private fun populateTableOption(database: SQLiteDatabase, table: String, option: String){
        databaseHelper.insertDataOptiones(database, table, option)
        Log.i("aris", "Option Insert in Table: $option")
    }

    private fun parserColumnsArray(columnsArray: JSONArray): Map<String, Map<String, Any>>{
        val columnMappings = mutableMapOf<String, Map<String, String>>()
        for (i in 0 until columnsArray.length()) {
            val column = columnsArray.getJSONObject(i)
            val name = column.getString("name")
            val type = column.getString("type")
            val length = column.getString("lenght") // Nota la errata en el nombre de la clave
            val ordenList = column.getInt("ordenList")
            val options = column.getString("options")
            val columnData = mapOf(
                "type" to type,
                "length" to length,
                "ordenList" to ordenList.toString(),
                "options" to options
            )
            columnMappings[name] = columnData
        }
        return columnMappings
    }
}



