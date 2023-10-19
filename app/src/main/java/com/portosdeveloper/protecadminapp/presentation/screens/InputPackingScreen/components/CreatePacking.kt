package com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen.InputPackingViewModel

@Composable
fun CreatePacking(viewModel : InputPackingViewModel = hiltViewModel()){

    when(val createPackingResponse = viewModel.createPackingResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, createPackingResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}