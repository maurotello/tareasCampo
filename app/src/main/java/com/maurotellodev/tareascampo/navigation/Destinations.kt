package com.maurotellodev.tareascampo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.JoinFull
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object BottomNavigationBar: Destinations("BottomNavigationBar", "BottomNavigationBar", Icons.Filled.JoinFull)
    object Login: Destinations("Login", "Login", Icons.Filled.Login)
    object Jobs: Destinations("Trabajos", "Trabajos", Icons.Filled.JoinFull)
    object Options: Destinations("Opciones", "Opciones", Icons.Filled.Settings)
    object Settings: Destinations("Settings", "Configuración", Icons.Filled.Settings)
}