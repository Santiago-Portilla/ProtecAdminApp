package com.portosdeveloper.protecadminapp.presentation.screens.InputButtonScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputButtonScreen.InputButtonViewModel

@Composable
fun CreateButton(viewModel : InputButtonViewModel = hiltViewModel()){

    when(val createButtonResponse = viewModel.createButtonResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
               viewModel.stateQuantity.forEachIndexed { index, _ ->
                   viewModel.onAddTotalButtonDay(index)
               }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, createButtonResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}