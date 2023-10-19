package com.portosdeveloper.protecadminapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.screens.LoginScreen.LoginViewModel
import com.portosdeveloper.protecadminapp.presentation.screens.LoginScreen.components.Login
import com.portosdeveloper.protecadminapp.presentation.screens.LoginScreen.components.LoginBottomBar
import com.portosdeveloper.protecadminapp.presentation.screens.LoginScreen.components.LoginContent
import com.portosdeveloper.protecadminapp.presentation.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController){



    Scaffold(
        topBar = {
            Column() {
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 20.dp,
                    color = Blue700
                )
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 20.dp,
                    color = Brown700
                )
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 20.dp,
                    color = Gray700
                )
            }
        },
        content = { LoginContent() },
        bottomBar = { LoginBottomBar(navController) }
    )
    Login(navController = navController)
}
