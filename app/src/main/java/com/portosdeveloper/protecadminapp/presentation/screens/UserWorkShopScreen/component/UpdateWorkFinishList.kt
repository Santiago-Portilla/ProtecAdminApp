package com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.UserWorkShopViewModel
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate

@Composable
fun UpdateWorkFinishList(viewModel: UserWorkShopViewModel = hiltViewModel()){
    val actualDate = ActualDate()
    when(val updateWorkFinishList = viewModel.updateWorkFinishListResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            LaunchedEffect(Unit){
                if(viewModel.userWorkShop.workListFinish.size == 1){
                    when(actualDate.actualDate.substring(0,2).toInt()-viewModel.userWorkShop.actualDate.substring(0,2).toInt()){
                        in 10..16 ->{
                            viewModel.updateSalaryWorkWeek()
                        }
                        in 17..31 ->{
                            viewModel.updateSalaryWorkMonth()
                            viewModel.updateSalaryWorkWeek()
                        }
                    }

                }
            }
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, updateWorkFinishList.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{

        }
    }
}