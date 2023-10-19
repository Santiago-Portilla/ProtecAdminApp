package com.portosdeveloper.protecadminapp.presentation.screens.LoginScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.navigation.AuthScreen
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Brown700

@Composable
fun LoginBottomBar(navController: NavHostController){
    Row(
        modifier =  Modifier.fillMaxWidth().padding(20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No tienes cuenta?",
            fontSize = 14.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = AuthScreen.SignUp.route)
            },
            text = "REGISTRATE AQUI",
            color = Brown700,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}