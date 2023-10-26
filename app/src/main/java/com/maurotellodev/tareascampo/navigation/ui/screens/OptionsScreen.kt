package com.maurotellodev.tareascampo.navigation.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.maurotellodev.tareascampo.R
import com.maurotellodev.tareascampo.navigation.viewmodel.OptionsViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun OptionsScreen(optionsViewModel: OptionsViewModel, navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        HeaderOptions(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center), optionsViewModel, navController)
        FooterOptions(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun HeaderOptions(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Cerrar APP",
        modifier = modifier.clickable { activity.finish() })
}

@Composable
fun Body(modifier: Modifier, optionsViewModel: OptionsViewModel, navController: NavController) {
    //val username:String by settingsViewModel.username.observeAsState(initial = "")
    //val password:String by settingsViewModel.password.observeAsState(initial = "")
    //val isLoginEnable:Boolean by settingsViewModel.isLoginEnable.observeAsState(initial = false)
    //val preferencesSaved by settingsViewModel.preferencesSaved.observeAsState(false)

    //var show by remember { mutableStateOf(false) }


    Column(modifier = modifier) {
        //ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        OptionsButton(optionsViewModel)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
    }
}
@Composable
fun OptionsButton(optionsViewModel: OptionsViewModel) {
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                optionsViewModel.downloadFileFromFTP()
            }

        },
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

@Composable
fun FooterOptions(modifier: Modifier) {
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