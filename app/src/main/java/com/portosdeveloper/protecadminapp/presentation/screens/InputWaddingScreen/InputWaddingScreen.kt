package com.portosdeveloper.protecadminapp.presentation.screens.InputWaddingScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputWaddingScreen.components.*
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputWaddingScreen(navController: NavHostController){

    Scaffold(
        topBar = { DefaultTopBar(
            title = "INGRESO TROQUELADO", color = Gray200,
            upAvailable = true,
            navController = navController
        ) },
        content = {
            InputWaddingContent()
        }
    )
    UpdateRollWadding()
    CreateWadding()
    UpdateWadding()
    AddDateWadding(navController)
}
