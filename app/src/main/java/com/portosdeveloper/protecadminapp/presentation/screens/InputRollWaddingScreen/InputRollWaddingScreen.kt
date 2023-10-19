package com.portosdeveloper.protecadminapp.presentation.screens.InputRollWaddingScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputRollWaddingScreen.components.*
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputRollWaddingScreen(navController: NavHostController){


    Scaffold(
        topBar = { DefaultTopBar(
            title = "INGRESO ENTRETELA (ROLLOS)", color = Gray200,
            upAvailable = true,
            navController = navController
        )
                 },
        content = {
            InputRollWaddingContent()
        }

    )
    UpdateRollWadding()
    CreateRollWadding()
    AddDateRollWadding(navController)
}