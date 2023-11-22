package com.maurotellodev.tareascampo.navigation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
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
}