package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.components.*
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProgramingLineScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "PROGRAMACION LINEA", color = Gray200,
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            ProgramingLineContent()
        }
    )
    UpdateTotalCloth()
    UpdateCloth()
    UpdatePlotter()
    UpdateWadding()
    CreatePackage(navController)
    GetShirtById()
    UpdateShirt()
    CreateShirt()
}