package com.maurotellodev.tareascampo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.maurotellodev.tareascampo.login.viewmodel.LoginViewModel
import com.maurotellodev.tareascampo.navigation.NavigationHost
import com.maurotellodev.tareascampo.navigation.viewmodel.SettingsViewModel
import com.maurotellodev.tareascampo.ui.theme.TareasCampoTheme
import dagger.hilt.android.AndroidEntryPoint

// Con esta anotación preparo la Activity para inyección de dependencias
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Lo que hacmos con la siguiente linea es inyectar el viewmodel
    // Nos olvidamos de crear instancias a mano
    private val loginViewModel: LoginViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TareasCampoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    /*
                    var show by remember { mutableStateOf(false) }
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Button(onClick = { show = true }) {
                            Text(text = "Mostrar Dialogo")
                        }*/

                        /*
                        MyAlertDialog(
                            show = show,
                            onDismiss = { show = false },
                            onConfirm = {
                                Log.i("aris", "Presione en confirmar")
                            }
                        )*/


                    NavigationHost(
                        navController = rememberNavController(),
                        loginViewModel = loginViewModel,
                        settingsViewModel = settingsViewModel)
                }
            }
        }
    }
}


@Composable
fun HelloApp() {
    TareasCampoTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}