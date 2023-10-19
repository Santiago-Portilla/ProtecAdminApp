package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.ProgramingLineViewModel

@Composable
fun UpdateWadding(viewModel: ProgramingLineViewModel = hiltViewModel()){
    when(val updateWaddingResponse = viewModel.updateWaddingResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, updateWaddingResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{}
    }
}