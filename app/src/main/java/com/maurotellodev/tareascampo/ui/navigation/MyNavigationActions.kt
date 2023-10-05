package com.maurotellodev.tareascampo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.maurotellodev.tareascampo.R

class MyAppNavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: MyAppTopLevelDestination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
        }
    }
}

data class MyAppTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val iconTextId: Int
)

object MyAppRoute {
    const val JOBS = "Trabajos"
    const val OPTIONS = "Opciones"
    const val SETTINGS = "settings"
}


val TOP_LEVEL_DESTINATIONS = listOf(
    MyAppTopLevelDestination(
        route = MyAppRoute.JOBS,
        selectedIcon = Icons.Default.Home,
        iconTextId = R.string.home
    ),
    MyAppTopLevelDestination(
        route = MyAppRoute.OPTIONS,
        selectedIcon = Icons.Default.AccountCircle,
        iconTextId = R.string.account
    ),
    MyAppTopLevelDestination(
        route = MyAppRoute.SETTINGS,
        selectedIcon = Icons.Default.Settings,
        iconTextId = R.string.settings
    ),
)