package com.portosdeveloper.protecadminapp.presentation.screens.InputReflectiveScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputReflectiveScreen.InputReflectiveViewModel

@Composable
fun AddDateReflective( viewModel: InputReflectiveViewModel = hiltViewModel()){
    when(val addDateReflectiveResponse = viewModel.addDateReflectiveResponse) {
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                viewModel.stateQuantity.forEachIndexed { index, _ ->
                    viewModel.onAddTotalReflectiveDay(index)
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, addDateReflectiveResponse.exception.message ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}