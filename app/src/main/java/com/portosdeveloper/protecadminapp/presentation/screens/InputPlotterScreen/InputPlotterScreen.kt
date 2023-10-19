package com.portosdeveloper.protecadminapp.presentation.screens.InputPlotterScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputPlotterScreen.components.CreatePlotter
import com.portosdeveloper.protecadminapp.presentation.screens.InputPlotterScreen.components.InputPlotterContent
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputPlotterScreen(navController: NavHostController){

    Scaffold(
        topBar = { DefaultTopBar(
            title = "INGRESO PLOTTER", color = Gray200,
            upAvailable = true,
            navController = navController
        ) },
        content = { InputPlotterContent() }
    )
    CreatePlotter(navController)
}