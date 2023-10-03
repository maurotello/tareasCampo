package com.maurotellodev.tareascampo.ui

import com.maurotellodev.tareascampo.R

sealed class items_bottom_menu(
    val icon: Int,
    val title: String,
    val route: String
){
    object JobsScreen: items_bottom_menu(R.drawable.jobs_list_alt_24, "Trabajos", "JobsScreen")
    object OptionsScreen: items_bottom_menu(R.drawable.options_apps_24, "Opciones", "OptionsScreen")
    object ConfigurationScreen: items_bottom_menu(R.drawable.configurations_services_24, "Configuración", "ConfigurationScreen")
}
