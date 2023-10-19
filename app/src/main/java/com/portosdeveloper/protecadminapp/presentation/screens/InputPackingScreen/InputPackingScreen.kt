package com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen.components.*
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputPackingScreen(navController: NavHostController){


    Scaffold(
        topBar = { DefaultTopBar(
            title = "INGRESO EMPAQUE", color = Gray200,
            upAvailable = true,
            navController = navController
        ) },
        content = { InputPackingContent() }

    )
    CreatePacking()
    UpdatePacking()
    AddDatePacking()
    AddTotalPackingDay(navController)
}