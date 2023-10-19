package com.portosdeveloper.protecadminapp.presentation.screens.StockScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.screens.StockScreen.components.StockContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StockScreen(navController: NavHostController){
    Scaffold(
        content = {
            StockContent(navController)
        }
    )
}