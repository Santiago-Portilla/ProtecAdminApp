package com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.InputClothViewModel

@Composable
fun AddDateTotalCloth(navController: NavHostController,viewModel : InputClothViewModel = hiltViewModel()){
    when( val addDateResponse = viewModel.addDateResponse){
        Response.Loading ->{

        }
        is Response.Success ->{
            LaunchedEffect(Unit){
                navController.popBackStack()
            }
            Toast.makeText(LocalContext.current, "Se a subido a la base de datos", Toast.LENGTH_LONG).show()
        }
        is Response.Failure ->{

        }
        else ->{}
    }

}