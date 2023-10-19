package com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTopBar
import com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.component.*
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserWorkShopScreen(navController: NavHostController){
    Scaffold(
        topBar = { DefaultTopBar(
            title = "PERSONAL", color = Gray200,
            upAvailable = true,
            navController = navController
        ) },
        content = {
            UserWorkShopContent()
        }
        )

    GetPackageByIdPaid()
    UpdatePaidJob()
    UpdateWorkPaidList()
    UpdateWorkFinishList()
    UpdateSalaryWorkWeek(navController)
    UpdateSalaryWorkMonth()

}