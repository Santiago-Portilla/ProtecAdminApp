package com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber.components.SearchWithNumberContent
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchWithNumberScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "BUSQUEDA DE PAQUETE", color = Gray200,
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            SearchWithNumberContent()
        }
    )

}