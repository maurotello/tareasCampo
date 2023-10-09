package com.maurotellodev.tareascampo.navigation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

//initializing the data class with default parameters
data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Jobs",
                icon = Icons.Filled.Home,
                route = Destinations.Jobs.route
            ),
            BottomNavigationItem(
                label = "Options",
                icon = Icons.Filled.Search,
                route = Destinations.Options.route
            ),
            BottomNavigationItem(
                label = "Settings",
                icon = Icons.Filled.AccountCircle,
                route = Destinations.Settings.route
            ),
        )
    }
}