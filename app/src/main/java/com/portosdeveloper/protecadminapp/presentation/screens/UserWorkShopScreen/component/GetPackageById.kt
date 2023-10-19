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
fun GetPackageByIdPaid( viewModel: UserWorkShopViewModel = hiltViewModel()){

    val actualDate = ActualDate()

    when(val getPackageById = viewModel.getPackageByIdResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            LaunchedEffect(Unit){
                viewModel.updatePaidJob(getPackageById.data)
                viewModel.updateWorkPaidList("${getPackageById.data.id},Pagado el ${actualDate.actualDate}")
                viewModel.updateWorkFinishList(viewModel.userWorkShop,viewModel.newFinishList(viewModel.userWorkShop,getPackageById.data))
            }
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, getPackageById.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{

        }
    }
}