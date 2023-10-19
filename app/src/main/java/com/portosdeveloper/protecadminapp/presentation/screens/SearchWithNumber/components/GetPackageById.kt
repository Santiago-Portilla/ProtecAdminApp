package com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber.SearchWithNumberViewModel

@Composable
fun GetPackageById(viewModel: SearchWithNumberViewModel = hiltViewModel()){
    when(val getPackageById = viewModel.getPackageByIdResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            PackageShirtCard(packageShirt = getPackageById.data)
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, getPackageById.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{}
    }

}