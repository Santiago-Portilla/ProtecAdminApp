package com.portosdeveloper.protecadminapp.presentation.screens.InputButtonScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputButtonScreen.components.*
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputButtonScreen(navController: NavHostController){


    Scaffold(
        topBar = { DefaultTopBar(
            title = "INGRESO BOTON", color = Gray200,
            upAvailable = true,
            navController = navController
        ) },
        content = { InputButtonContent() }
    )
    CreateButton()
    UpdateButton()
    AddTotalButtonDay(navController)
}