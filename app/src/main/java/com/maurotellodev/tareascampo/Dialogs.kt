package com.maurotellodev.tareascampo

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.maurotellodev.tareascampo.ui.theme.TareasCampoTheme


@Composable
fun MyCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog(text = "Set backup Account")
                AccountItem(email = "ejemplo1@gmail.com", R.drawable.avatar)
                AccountItem(email = "ejemplo2@gmail.com", R.drawable.avatar)
                AccountItem(email = "Agregar nueva cuenta", R.drawable.add)
            }
        }
    }
}


@Composable
fun MySimpleCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Esto es un ejemplo")
            }
        }
    }
}


@Composable
fun MyAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            confirmButton = {
                Button(
                    onClick = { onConfirm() },
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Text(text = "CERRAR")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Dismiss Button")
                }
            }
        )
    }
}

@Composable
fun MyDataSavedDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    if (show) {
        TareasCampoTheme {
            AlertDialog(
                containerColor = Color.White,
                onDismissRequest = { onDismiss() },
                title = {
                    Text(
                        text = dialogTitle,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                text = {
                    Text(
                        text = dialogText,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                },
                confirmButton = { },
                dismissButton = {
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier.background(Color.Transparent),
                        border = BorderStroke(0.dp, Color.Transparent)

                    ) {
                        Text(
                            text = "Cerrar",
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                modifier = Modifier
                    .background(Color.Transparent, shape = RoundedCornerShape(size = 14.dp))
            )
        }
    }
}


@Composable
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(text = email, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(8.dp))
    }
}


@Composable
fun MyTitleDialog(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}