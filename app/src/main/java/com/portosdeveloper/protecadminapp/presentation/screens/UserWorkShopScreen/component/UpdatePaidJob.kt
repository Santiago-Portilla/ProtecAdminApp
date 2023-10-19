package com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.component

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.UserWorkShopViewModel

@Composable
fun UpdatePaidJob(viewModel: UserWorkShopViewModel = hiltViewModel()){

    when(val updatePaidJobResponse = viewModel.updatePaidJobResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{

        }
        is Response.Failure ->{

        }
        else ->{

        }
    }

}