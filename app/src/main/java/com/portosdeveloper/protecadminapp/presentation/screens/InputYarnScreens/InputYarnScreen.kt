package com.portosdeveloper.protecadminapp.presentation.screens.InputYarnScreens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputYarnScreens.components.*
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputYarnScreen(navController: NavHostController){

    Scaffold(
        topBar = { DefaultTopBar(
            title = "INGRESO HILAZA", color = Gray200,
            upAvailable = true,
            navController = navController
        ) },
        content = { InputYarnContent() }
    )
    UpdateYarn()
    CreateYarn()
    AddTotalYarnDay(navController)
}