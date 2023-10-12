package com.maurotellodev.tareascampo.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maurotellodev.tareascampo.navigation.ui.screens.JobsScreen
import com.maurotellodev.tareascampo.navigation.ui.screens.OptionsScreen
import com.maurotellodev.tareascampo.navigation.ui.screens.SettingsScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }

    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = index == navigationSelectedItem,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navigationSelectedItem = index
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Jobs.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Destinations.Jobs.route) {
                JobsScreen(
                    navController
                )
            }
            composable(Destinations.Options.route) {
                OptionsScreen(
                    navController
                )
            }
            composable(Destinations.Settings.route) {
                SettingsScreen(
                    navController
                )
            }
        }
    }
}