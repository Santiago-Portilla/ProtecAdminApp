package com.portosdeveloper.protecadminapp.presentation.screens.InputThreadScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputThreadScreen.InputThreadViewModel

@Composable
fun UpdateThread(viewModel : InputThreadViewModel = hiltViewModel()){

    when(val updateThreadResponse = viewModel.updateThreadResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                viewModel.stateQuantity.forEachIndexed { index, _ ->
                    viewModel.onAddTotalThreadDay(index)
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, updateThreadResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}