package com.portosdeveloper.protecadminapp.presentation.screens.PersonalScreen.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.PersonalScreen.PersonalViewModel

@Composable
fun GetUserWorkShopList(viewModel: PersonalViewModel = hiltViewModel(), navController: NavHostController){
    when(val getUserWorkShopListResponse = viewModel.userGetWorkShopList){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            PersonalContent(usersWorkShopList = getUserWorkShopListResponse.data, navController = navController)
        }
        is Response.Failure ->{

        }
        else ->{}
    }

}