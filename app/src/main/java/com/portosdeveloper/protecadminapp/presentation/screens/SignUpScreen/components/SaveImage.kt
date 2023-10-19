package com.portosdeveloper.protecadminapp.presentation.screens.SignUpScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.navigation.Graph
import com.portosdeveloper.protecadminapp.presentation.screens.SignUpScreen.SignUpViewModel

@Composable
fun SaveImage(viewModel : SignUpViewModel = hiltViewModel()){

    when(val saveImageResponse = viewModel.saveImageResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
                viewModel.onUpdate(saveImageResponse.data)

        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, saveImageResponse.exception.message ?: "Error Desconosido", Toast.LENGTH_LONG).show()
        }
        else ->{}
    }

}