package com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.components.*
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputClothScreen(navController: NavHostController){

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "INGRESO TELA", color = Gray200,
                upAvailable = true,
                navController = navController
            )
         },
        content = { InputClothContent() }
    )
    AddDateTotalCloth(navController)
    Cloth()
    TotalCloth()
    UpdateCloth()
}