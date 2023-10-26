package com.maurotellodev.tareascampo.navigation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maurotellodev.tareascampo.data.interfaces.DataStoreRepository
import com.maurotellodev.tareascampo.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor (private val context: Context, private val repository: DataStoreRepository, private val jobRepositoryImp: JobRepository): ViewModel(){
    fun getJob(){
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
            }finally {
                ftpClient.logout()
                ftpClient.disconnect()
            }
            success
        }
    }

    private fun uploadFiles(ftpClient: FTPClient, filesUsers: Array<FTPFile>){
        try {
            for (fileUser in filesUsers) {
                if(fileUser.name != "." && fileUser.name != ".."){
                    //conexion.json
                    Log.i("aris", "Archivo: ${fileUser.name}")

                    val externalFilesDir = context.getExternalFilesDir(null)
                    if (externalFilesDir != null) {
                        val gesisFolder = File(externalFilesDir, "gesis")
                        val archivoLocal = File(gesisFolder, fileUser.name)
                        val archivoLocalOutput = FileOutputStream(archivoLocal)
                        val resultadoDescarga = ftpClient.retrieveFile(fileUser.name, archivoLocalOutput)
                        archivoLocalOutput.close()
                        if (resultadoDescarga) {
                            Log.i("aris", "Archivo descargado exitosamente: ${archivoLocal.absolutePath}")
                        } else {
                            Log.e("aris", "Error al descargar el archivo: ${fileUser.name}")
                        }
                    }

                }else{
                    Log.i("aris", "Ningún archivo en tello")
                }
            }
        }catch (e: SecurityException) {
            // Manejar la excepción de permisos
            e.printStackTrace()
            Log.e("aris", "Permiso denegado para realizar la operación de E/S")
        }catch (e: IOException) {
            // Manejar la excepción de E/S
            e.printStackTrace()
            Log.e("aris", "Error de E/S al descargar el archivo")
        }

    }

    private fun connectServer(ftpClient: FTPClient):Boolean{
        return try {
            ftpClient.connect("89.117.7.119", 21)
            ftpClient.login("u828369731.maurotellodev.com", "desaTELLO2014")
            ftpClient.enterLocalPassiveMode()
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE)
            Log.i("aris", "CONECTADO AL SERVIDOR")
            true
        }catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}