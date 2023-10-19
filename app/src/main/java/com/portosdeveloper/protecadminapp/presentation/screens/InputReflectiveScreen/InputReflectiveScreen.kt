package com.portosdeveloper.protecadminapp.presentation.screens.InputReflectiveScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputReflectiveScreen.components.*
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputReflectiveScreen(navController: NavHostController){

    Scaffold(
        topBar = { DefaultTopBar(
            title = "INGRESO REFLECTIVO", color = Gray200,
            upAvailable = true,
            navController = navController
        ) },
        content = { InputReflectiveContent() }
    )
    CreateReflective()
    UpdateReflective()
    AddDateReflective()
    AddTotalReflectiveDay(navController)
}