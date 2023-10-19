package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.ProgramingLineViewModel

@Composable
fun CreatePackage(navController: NavHostController,viewModel: ProgramingLineViewModel = hiltViewModel()){
    when(val createPackageResponse = viewModel.createPackageResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            LaunchedEffect(Unit){
                navController.popBackStack()
            }
            Toast.makeText(LocalContext.current, "Se a subido a la base de datos", Toast.LENGTH_LONG).show()

        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, createPackageResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}