package com.portosdeveloper.protecadminapp.presentation.screens.InputWaddingScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.InputWaddingScreen.InputWaddingViewModel

@Composable
fun UpdateRollWadding( viewModel : InputWaddingViewModel = hiltViewModel()){
    when(val updateTollWadding = viewModel.updateRollWaddingResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success -> {

        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, updateTollWadding.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{}
    }
}