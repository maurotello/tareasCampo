package com.maurotellodev.tareascampo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Items_bottom_menu(
    //val icon: Int,
    val icon: ImageVector,
    val title: String,
    val route: String
){
    object JobsScreen: Items_bottom_menu(Icons.Default.Home, "Trabajos", "JobsScreen")
    object OptionsScreen: Items_bottom_menu(Icons.Default.Person, "Opciones", "OptionsScreen")
    object ConfigurationScreen: Items_bottom_menu(Icons.Default.Settings, "Configuración", "ConfigurationScreen")
}
