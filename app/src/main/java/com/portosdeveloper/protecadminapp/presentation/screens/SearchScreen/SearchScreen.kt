package com.portosdeveloper.protecadminapp.presentation.screens.SearchScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController){
    Scaffold(
        content = {
            SearchContent(navController)
        }
    )
}