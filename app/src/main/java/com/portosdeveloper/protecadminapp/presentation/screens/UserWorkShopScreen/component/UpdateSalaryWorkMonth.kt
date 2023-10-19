package com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.UserWorkShopViewModel

@Composable
fun UpdateSalaryWorkMonth(viewModel: UserWorkShopViewModel = hiltViewModel()){

    when(val updateSalaryWorkMonth = viewModel.updateSalaryWorkMonthResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{


        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, updateSalaryWorkMonth.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{
        }
    }
}