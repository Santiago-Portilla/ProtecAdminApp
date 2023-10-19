package com.portosdeveloper.protecadminapp.presentation.screens.InputWaddingScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputWaddingScreen.InputWaddingViewModel

@Composable
fun UpdateWadding(viewModel : InputWaddingViewModel = hiltViewModel()){

    when(val updateWaddingResponse = viewModel.updateWaddingResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
               viewModel.stateQuantity.forEachIndexed { index, s ->
                   viewModel.onAddDateWadding(index)
               }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, updateWaddingResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}