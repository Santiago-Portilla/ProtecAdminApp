package com.portosdeveloper.protecadminapp.presentation.screens.StockProgress

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.StockProgress.components.StockPackage
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StockProgressScreen(navController: NavHostController){
    Scaffold(
        topBar = { DefaultTopBar(
            title = "PAQUETE EN PROGRESO", color = Gray200,
            upAvailable = true,
            navController = navController
        ) },
        content = {
            StockPackage()
        },
        bottomBar = {

        }
    )
}