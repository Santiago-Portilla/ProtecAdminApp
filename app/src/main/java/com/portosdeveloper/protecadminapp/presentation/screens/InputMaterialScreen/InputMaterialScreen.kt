package com.portosdeveloper.protecadminapp.presentation.screens.InputMaterialScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputMaterialScreen.components.DefaultCard
import com.portosdeveloper.protecadminapp.presentation.screens.InputMaterialScreen.components.InputMaterialContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputMaterialScreen(navController: NavHostController){

    Scaffold(
        content = {
            InputMaterialContent(navController)
        }
    )
}