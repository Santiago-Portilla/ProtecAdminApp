package com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen.InputPackingViewModel

@Composable
fun AddDatePacking( viewModel: InputPackingViewModel = hiltViewModel()){
    when(val addDatePackingResponse = viewModel.addDatePackingResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, addDatePackingResponse.exception.message ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}