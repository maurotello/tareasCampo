package com.maurotellodev.tareascampo.navigation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.JoinFull
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    //object LoginScreen: Destinations("Login", "Login", Icons.Default.Home)
    object Main: Destinations("Main", "Main", Icons.Filled.JoinFull)
    object Jobs: Destinations("Trabajos", "Trabajos", Icons.Filled.JoinFull)
    object Options: Destinations("Opciones", "Opciones", Icons.Filled.Settings)
    object Settings: Destinations("Settings", "Configuración", Icons.Filled.Settings)
}
