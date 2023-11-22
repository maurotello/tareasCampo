package com.maurotellodev.tareascampo.data.databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "Gesis.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        // Aquí puedes ejecutar comandos SQL para crear tus tablas
        //db?.execSQL("CREATE TABLE IF NOT EXISTS mi_tabla (id INTEGER PRIMARY KEY, name TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Aquí puedes realizar actualizaciones de la base de datos si la versión cambia
    }



    fun createTableCombo(database: SQLiteDatabase, table: String){
        val createTableOptions = "CREATE TABLE IF NOT EXISTS $table (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT)"
        database.execSQL(createTableOptions)
    }

    fun createTablePermissions(database: SQLiteDatabase, table: String){
        val table = table + "_permissions"
        val createTableOptions = "CREATE TABLE IF NOT EXISTS $table (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "columnName STRING," +
                "permission INTEGER)"
        database.execSQL(createTableOptions)
    }

    fun createTableInList(database: SQLiteDatabase, table: String){
        val table = table + "_list"
        val createTableOptions = "CREATE TABLE IF NOT EXISTS $table (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "columnName STRING," +
                "inList INTEGER)"
        database.execSQL(createTableOptions)
    }

    fun populateTablePermissions(database: SQLiteDatabase, table: String, column: String, permission: Int){
        val contentValues = ContentValues()
        contentValues.put("columnName", column)
        contentValues.put("permission", permission)
        val tablePermi = table + "_permissions"
        database.insert(tablePermi, null, contentValues)
    }

    fun populatePrincipalTable(database: SQLiteDatabase,table: String, contentValues: ContentValues): Boolean{
        val newRowId = database.insert(table, null, contentValues)
        return newRowId != -1L // Devuelve true si newRowId es diferente de -1, indicando que la inserción fue exitosa
    }
    fun populateTableInList(database: SQLiteDatabase, table: String, column: String, inList: Int){
        val contentValues = ContentValues()
        contentValues.put("columnName", column)
        contentValues.put("inList", inList)
        val tableList = table + "_list"
        database.insert(tableList, null, contentValues)

    }

    fun insertDataOptiones(database: SQLiteDatabase, table: String, name: String){
        val contentValues = ContentValues()
        contentValues.put("name", name)
        database.insert("$table", null, contentValues)

    }

    fun createPrincipalTable(database: SQLiteDatabase, sql: String){
        database.execSQL(sql)
    }

    fun table(database: SQLiteDatabase){
        val createTableQuery = "CREATE TABLE IF NOT EXISTS tables (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(100)" +
                ")"
        database.execSQL(createTableQuery)
    }

    fun populateTable(database: SQLiteDatabase, table: String){
        val contentValues = ContentValues()
        contentValues.put("name", table)
        database.insert("tables", null, contentValues)
        database.close()
    }

    private fun createLoginTable(database: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE IF NOT EXISTS login (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "login_datetime DATETIME" +
                ")"
        database.execSQL(createTableQuery)
    }
    fun insertData(database: SQLiteDatabase, name: String) {
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("login_datetime", getCurrentDateTime())
        database.insert("login", null, contentValues)
        database.close()
    }
    private fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

    fun dropAllTables(db: SQLiteDatabase) {
        // Obtener los nombres de todas las tablas en la base de datos
        val cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)
        cursor.use {
            while (it.moveToNext()) {
                val tableName = it.getString(0)
                // Evitar eliminar tablas del sistema
                if (!tableName.startsWith("sqlite_")) {
                    db.execSQL("DROP TABLE IF EXISTS $tableName")
                }
            }
        }
    }



}