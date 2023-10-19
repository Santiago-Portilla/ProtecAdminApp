package com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.UserWorkShopViewModel

@Composable
fun UpdateSalaryWorkWeek(navController: NavHostController ,viewModel: UserWorkShopViewModel = hiltViewModel()){

    when(val updateSalaryWorkWeek = viewModel.updateSalaryWorkWeekResponse){
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
            Toast.makeText(LocalContext.current, updateSalaryWorkWeek.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{
        }
    }
}