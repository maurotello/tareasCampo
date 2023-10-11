package com.maurotellodev.tareascampo.navigation.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maurotellodev.tareascampo.navigation.ui.Destinations.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun NavigationHost( navController: NavController){

    NavHost(navController = navController as NavHostController, startDestination = Jobs.route){

        //composable(BottomNavigationBar.route){ MainScreen(navController) }
        composable(Settings.route){ SettingsScreen(navController) }
        composable(Jobs.route){ JobsScreen(navController) }
        composable(Options.route){ OptionsScreen(navController) }
    }
}

