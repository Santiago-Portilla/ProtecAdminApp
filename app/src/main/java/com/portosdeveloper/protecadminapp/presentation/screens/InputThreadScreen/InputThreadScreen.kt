package com.portosdeveloper.protecadminapp.presentation.screens.InputThreadScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputThreadScreen.components.*
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputThreadScreen(navController: NavHostController){

    Scaffold(
        topBar = { DefaultTopBar(
            title = "INGRESO HILOS", color = Gray200,
            upAvailable = true,
            navController = navController
        ) },
        content = { InputThreadContent() }
    )
    UpdateThread()
    CreateThread()
    AddTotalThreadDay(navController)



}