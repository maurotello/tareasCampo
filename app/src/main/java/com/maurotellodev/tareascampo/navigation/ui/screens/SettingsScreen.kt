package com.maurotellodev.tareascampo.navigation.ui.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.maurotellodev.tareascampo.MyDataSavedDialog
import com.maurotellodev.tareascampo.R
import com.maurotellodev.tareascampo.navigation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel, navController: NavHostController) {
    //val navController = rememberNavController()
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center), settingsViewModel, navController)
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.size(24.dp))
    }
}


@Composable
fun Body(modifier: Modifier, settingsViewModel: SettingsViewModel, navController: NavController) {
    val username:String by settingsViewModel.username.observeAsState(initial = "")
    val password:String by settingsViewModel.password.observeAsState(initial = "")
    val isLoginEnable:Boolean by settingsViewModel.isLoginEnable.observeAsState(initial = false)
    val preferencesSaved by settingsViewModel.preferencesSaved.observeAsState(false)

    var show by remember { mutableStateOf(false) }
    Log.i("aris", "Preferences en Screen: $preferencesSaved")

    if(settingsViewModel.isDataSaved){
        MyDataSavedDialog(
            show = preferencesSaved,
            dialogTitle = "GESIS",
            dialogText = "Datos guardados satisfactoriamente",
            onDismiss = {
                settingsViewModel.isDataSaved = false
                Log.i("aris", "Presione en confirmar")
            },
            onConfirm = { }
        )
    }


    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Name(username) {
            settingsViewModel.onLoginChanged(username = it, password = password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            settingsViewModel.onLoginChanged(username = username, password = it)
        }
        Spacer(modifier = Modifier.size(8.dp))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(
            isLoginEnable,
            settingsViewModel
        )
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
    }
}
@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, settingsViewModel: SettingsViewModel) {
    Button(

        onClick = {
           settingsViewModel.onLoginSelected()
        },
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            //disabledBackgroundColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = stringResource(id = R.string.btn_save_user))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Password") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val imagen = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = imagen, contentDescription = "show password")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Name(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors =  TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.aris),
        contentDescription = "logo",
        modifier = modifier.width(120.dp)
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Cerrar APP",
        modifier = modifier.clickable { activity.finish() })
}