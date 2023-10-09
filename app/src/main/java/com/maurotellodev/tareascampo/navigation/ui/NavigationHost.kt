package com.maurotellodev.tareascampo.navigation.ui

import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maurotellodev.tareascampo.login.ui.LoginScreen
import com.maurotellodev.tareascampo.navigation.ui.Destinations.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun NavigationHost(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main.route){
        composable(Main.route){ MainScreen(navController) }
        composable(Settings.route){ SettingsScreen(navController) }
        composable(Jobs.route){ JobsScreen(navController) }
        composable(Options.route){ OptionsScreen(navController) }
    }
}

