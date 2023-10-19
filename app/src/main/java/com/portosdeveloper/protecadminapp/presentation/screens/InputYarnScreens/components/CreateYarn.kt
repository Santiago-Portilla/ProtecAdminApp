package com.portosdeveloper.protecadminapp.presentation.screens.InputYarnScreens.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputYarnScreens.InputYarnViewModel

@Composable
fun CreateYarn(viewModel : InputYarnViewModel = hiltViewModel()){

    when(val createYarnResponse = viewModel.createYarnResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                viewModel.stateQuantity.forEachIndexed { index, _ ->
                    viewModel.onAddTotalYarnDay(index)
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, createYarnResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}