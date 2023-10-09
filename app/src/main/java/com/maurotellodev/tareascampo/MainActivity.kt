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
import com.maurotellodev.tareascampo.login.ui.LoginScreen
import com.maurotellodev.tareascampo.login.ui.LoginViewModel
import com.maurotellodev.tareascampo.ui.theme.TareasCampoTheme
import com.maurotellodev.tareascampo.navigation.ui.BottomNavigationBar
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
            TareasCampoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(loginViewModel)
                    //BottomNavigationBar()
                }
            }
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

        }
    }
}
