package com.maurotellodev.tareascampo.data.interfaces

import android.content.Context
import java.io.File
import javax.inject.Inject

class FileRepository @Inject constructor(private val context: Context) {

    fun createGesisFolder():Boolean {
        // Obtén la ubicación del directorio de archivos externos de la aplicación.
        val externalFilesDir = context.getExternalFilesDir(null)
        // Comprueba si el directorio "gesis" ya existe en el directorio de archivos externos.
        val gesisFolder = File(externalFilesDir, "gesis")

        // Comprueba si el directorio existe. Si no, créalo.
        if (!gesisFolder.exists()) {
            // Si no existe, crea la carpeta "gesis".
            return gesisFolder.mkdirs()
        } else {
            return true
            // La carpeta "gesis" ya existe, puedes manejarlo como desees.
        }
    }
}