package com.portosdeveloper.protecadminapp.presentation.screens.PersonalScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.screens.PersonalScreen.components.GetUserWorkShopList
import com.portosdeveloper.protecadminapp.presentation.screens.PersonalScreen.components.PersonalContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonalScreen(navController: NavHostController){

    Scaffold(
        content = {
            GetUserWorkShopList(navController = navController)
        }
    )

}