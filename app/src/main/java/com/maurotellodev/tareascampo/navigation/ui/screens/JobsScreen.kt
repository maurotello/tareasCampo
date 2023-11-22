package com.maurotellodev.tareascampo.navigation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.maurotellodev.tareascampo.navigation.viewmodel.JobsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsScreen(jobsViewModel: JobsViewModel,  navController: NavHostController) {
    Scaffold() {
        JobsBodyContent(jobsViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsBodyContent(jobsViewModel: JobsViewModel){
    val filesInFolderGesis = jobsViewModel.listFilesinFolderGesis()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = androidx.constraintlayout.widget.R.color.material_grey_50))
            .wrapContentSize(Alignment.Center)
    ) {
        items(count = 7) { // Cambia count al número de tarjetas que deseas mostrar
            MyCard()
        }

    }
}
@ExperimentalMaterial3Api
@Composable
fun MyCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp),
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.Black  //Card content color,e.g.text
        )
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Ejemplo")
            Text(text = "Ejemplo")
            Text(text = "Ejemplo")

        }
    }
}