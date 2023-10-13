package com.maurotellodev.tareascampo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maurotellodev.tareascampo.login.ui.LoginScreen
import com.maurotellodev.tareascampo.login.viewmodel.LoginViewModel
import com.maurotellodev.tareascampo.navigation.Destinations.*
import com.maurotellodev.tareascampo.navigation.viewmodel.SettingsViewModel

@Composable

fun NavigationHost( navController: NavController,
                    loginViewModel: LoginViewModel,
                    settingsViewModel: SettingsViewModel
){

    NavHost(navController = navController as NavHostController, startDestination = Login.route){

        composable(BottomNavigationBar.route){ BottomNavigationBar(settingsViewModel, navController = navController) }
        composable(Login.route){ LoginScreen(loginViewModel = loginViewModel, navController = navController)}
    }
}