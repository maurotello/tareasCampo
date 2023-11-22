package com.maurotellodev.tareascampo.navigation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.ViewModel
import com.maurotellodev.tareascampo.data.databases.DatabaseHelper
import com.maurotellodev.tareascampo.data.interfaces.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor (private val context: Context, private val repository: DataStoreRepository): ViewModel(){
    fun listFilesinFolderGesis(): List<File> {
        val externalFilesDir = context.getExternalFilesDir(null)
        val gesisFolder = File(externalFilesDir, "gesis")

        if (gesisFolder.exists() && gesisFolder.isDirectory) {
            return gesisFolder.listFiles()?.toList() ?: emptyList()
        }
        return emptyList()
    }

    fun getFilterPrinciaplTable(){
        val dbHelper = DatabaseHelper(context)
        val database = dbHelper.writableDatabase

        val cursor: Cursor? = database.rawQuery("SELECT * FROM Relevamientos", null)

        cursor?.use { c ->
            val fieldNames = getRelevantFields(database)

            
        }

        cursor?.close()
    }

    @SuppressLint("Range")
    private fun getRelevantFields(db: SQLiteDatabase): List<Pair<String, Int>> {
        val columnsInfo = mutableListOf<Pair<String, Int>>()
        val listCursor: Cursor? = db.rawQuery("SELECT columnName FROM Relevamiento_list WHERE inList != 0", null)
        listCursor?.use { c ->
            while (c.moveToNext()) {
                val columnName = c.getString(c.getColumnIndex("columnName"))
                val inListValue = c.getInt(c.getColumnIndex("inList"))
                columnsInfo.add(columnName to inListValue)
            }
        }
        listCursor?.close()
        return columnsInfo
    }
}