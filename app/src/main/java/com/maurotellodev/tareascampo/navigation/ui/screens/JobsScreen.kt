package com.maurotellodev.tareascampo.navigation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.maurotellodev.tareascampo.R
import com.maurotellodev.tareascampo.navigation.viewmodel.JobsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsScreen(jobsViewModel: JobsViewModel,  navController: NavHostController) {



    Scaffold() {
        JobsBodyContent(jobsViewModel)
    }
}

@Composable
fun JobsBodyContent(jobsViewModel: JobsViewModel){
    val filesInFolderGesis = jobsViewModel.listFilesinFolderGesis()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        LazyColumn {
            items(filesInFolderGesis) { file ->
                Text(text = file.toString())
            }
        }
    }
}