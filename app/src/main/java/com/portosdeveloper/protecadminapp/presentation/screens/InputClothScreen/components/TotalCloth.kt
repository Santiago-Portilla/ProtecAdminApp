package com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.InputClothViewModel

@Composable
fun TotalCloth(viewModel : InputClothViewModel = hiltViewModel()){

    when(val totalClothResponse = viewModel.totalClothResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                viewModel.onAddDateTotalCloth()
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, totalClothResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}

    }
}