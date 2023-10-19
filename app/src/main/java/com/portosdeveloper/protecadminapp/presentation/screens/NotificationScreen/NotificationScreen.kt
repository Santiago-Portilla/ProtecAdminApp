package com.portosdeveloper.protecadminapp.presentation.screens.NotificationScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.portosdeveloper.protecadminapp.presentation.screens.NotificationScreen.components.NotificationContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotificationScreen(){

    Scaffold(
        content = {
            NotificationContent()
        }
    )

}