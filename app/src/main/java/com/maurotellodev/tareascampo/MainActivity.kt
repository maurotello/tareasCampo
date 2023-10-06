package com.maurotellodev.tareascampo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maurotellodev.tareascampo.login.ui.LoginScreen
import com.maurotellodev.tareascampo.login.ui.LoginViewModel

import com.maurotellodev.tareascampo.ui.theme.TareasCampoTheme

import com.maurotellodev.tareascampo.navigation.Items_bottom_menu.*
import com.maurotellodev.tareascampo.navigation.MyAppRoute
import com.maurotellodev.tareascampo.navigation.MyAppTopLevelDestination
import com.maurotellodev.tareascampo.navigation.TOP_LEVEL_DESTINATIONS
import dagger.hilt.android.AndroidEntryPoint

// Con esta anotación preparo la Activity para inyección de dependencias
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Lo que hacmos con la siguiente linea es inyectar el viewmodel
    // Nos olvidamos de crear instancias a mano
    private val loginViewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            helloApp()
        }
    }
}

@Composable
fun helloApp(){
    TareasCampoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(LoginViewModel())

            /*
            val navController = rememberNavController()
            val navigationAction = remember(navController){
                MyAppNavigationActions(navController)
            }
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val selectedDestination = navBackStackEntry?.destination?.route ?: MyAppRoute.HOME

            MyAppContent(
                modifier = Modifier,
                navController = navController,
                selectedDestination = selectedDestination,
                navigationTopLevelDestination = navigationAction::navigateTo
            )

             */


        }
    }
}

@Composable
fun MyAppContent(
    modifier: Modifier,
    navController: NavController,
    selectedDestination: String,
    navigationTopLevelDestination: (MyAppTopLevelDestination) -> Unit
){
    Row(modifier = modifier.fillMaxWidth()) {
        Column(modifier = modifier.fillMaxWidth()) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController as NavHostController,
                startDestination = MyAppRoute.JOBS
            ){
                composable(MyAppRoute.JOBS){
                    JobsScreen
                }
                composable(MyAppRoute.OPTIONS){
                    OptionsScreen
                }
                composable(MyAppRoute.SETTINGS){
                    ConfigurationScreen
                }
            }
            MyBottomNavigation(
                selectedDestination= selectedDestination,
                navigationTopLevelDestination = navigationTopLevelDestination
            )
        }
    }
}

@Composable
fun MyBottomNavigation(
    selectedDestination: String,
    navigationTopLevelDestination: (MyAppTopLevelDestination) -> Unit
){
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        TOP_LEVEL_DESTINATIONS.forEach{ destinations->
            NavigationBarItem(
                selected = selectedDestination == destinations.route ,
                onClick = { navigationTopLevelDestination(destinations) },
                icon = {
                    Icon(
                        imageVector = destinations.selectedIcon,
                        contentDescription = stringResource(id = destinations.iconTextId)
                    )
                }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TareasCampoTheme {
        Greeting("AristiDevs")
    }
}