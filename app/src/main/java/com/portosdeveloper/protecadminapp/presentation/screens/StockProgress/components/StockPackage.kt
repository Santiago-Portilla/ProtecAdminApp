package com.portosdeveloper.protecadminapp.presentation.screens.StockProgress.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.presentation.screens.StockProgress.StockProgressViewModel
import com.portosdeveloper.protecadminapp.domain.model.Package
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar

@Composable
fun StockPackage(viewModel: StockProgressViewModel = hiltViewModel()){

    when(val packageShirtResponse = viewModel.packageShirtResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success ->{

            StockProgressContent(packageShirts = packageShirtResponse.data)
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, packageShirtResponse.exception.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}