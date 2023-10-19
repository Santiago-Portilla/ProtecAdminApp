package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingScreen.components.ProgramingContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProgramingScreen(navController: NavHostController){
    Scaffold(
        content = {
            ProgramingContent(navController)
        }
    )
}