package com.maurotellodev.tareascampo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.maurotellodev.tareascampo.ui.items_bottom_menu

import com.maurotellodev.tareascampo.ui.items_bottom_menu.*

@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(navController, startDestination = items_bottom_menu.JobsScreen.route) {
        composable(items_bottom_menu.JobsScreen.route) {
            JobsScreen
        }
        composable(items_bottom_menu.OptionsScreen.route) {
            OptionsScreen
        }
        composable(items_bottom_menu.ConfigurationScreen.route) {
            ConfigurationScreen
        }

    }
}