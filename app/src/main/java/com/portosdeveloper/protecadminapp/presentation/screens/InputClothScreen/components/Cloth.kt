package com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.InputClothViewModel

@Composable
fun Cloth(viewModel : InputClothViewModel = hiltViewModel()){

    when(val clothResponse = viewModel.clothResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                if(viewModel.totalClothData.totalMeasure != ""){
                    viewModel.onUpdateTotalCloth()
                }else{
                    viewModel.onCreateTotalCloth()
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, clothResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}