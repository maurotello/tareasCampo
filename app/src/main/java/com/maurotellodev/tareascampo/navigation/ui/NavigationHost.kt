package com.maurotellodev.tareascampo.navigation.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maurotellodev.tareascampo.login.ui.LoginScreen
import com.maurotellodev.tareascampo.login.ui.LoginViewModel
import com.maurotellodev.tareascampo.navigation.ui.Destinations.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun NavigationHost( navController: NavController, loginViewModel: LoginViewModel): Int {

    NavHost(navController = navController as NavHostController, startDestination = Login.route){

        composable(Login.route){ LoginScreen(loginViewModel = loginViewModel, navController = navController) }
        composable(Settings.route){ SettingsScreen(navController) }
        composable(Jobs.route){ JobsScreen(navController) }
        composable(Options.route){ OptionsScreen(navController) }
    }
}

