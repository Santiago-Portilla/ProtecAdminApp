package com.portosdeveloper.protecadminapp.presentation.screens.NotificationScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.ui.theme.ProtecAdminAppTheme

@Composable
fun NotificationContent(){

    Column {
        NotificationCard(
            image = painterResource(id = R.drawable.user),
            name = "Nombre del personal",
            msn = "Mensaje Escrito"
            )
        NotificationCard(
                image = painterResource(id = R.drawable.user),
        name = "Nombre del personal",
            msn = "Mensaje Escrito"
        )
        NotificationCard(
            image = painterResource(id = R.drawable.user),
            name = "Nombre del personal",
            msn = "Mensaje Escrito"
        )
        NotificationCard(
            image = painterResource(id = R.drawable.user),
            name = "Nombre del personal",
            msn = "Mensaje Escrito"
        )
        NotificationCard(
            image = painterResource(id = R.drawable.user),
            name = "Nombre del personal",
            msn = "Mensaje Escrito"
        )
    }

}
