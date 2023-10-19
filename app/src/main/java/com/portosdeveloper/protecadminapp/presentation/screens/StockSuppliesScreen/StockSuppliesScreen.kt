package com.portosdeveloper.protecadminapp.presentation.screens.StockSuppliesScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.StockSuppliesScreen.components.StockSuppliesContent
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StockSuppliesScreen(navController: NavHostController) {
    Scaffold(
        topBar = { DefaultTopBar(
            title = "INVENTARIO DE INSUMOS", color = Gray200,
            upAvailable = true,
            navController = navController
        ) },
        content = {
            StockSuppliesContent()
        }
    )
}