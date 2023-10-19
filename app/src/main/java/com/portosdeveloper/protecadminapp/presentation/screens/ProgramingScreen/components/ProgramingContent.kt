package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.navigation.DetailsScreen
import com.portosdeveloper.protecadminapp.presentation.screens.InputMaterialScreen.components.DefaultCard

@Composable
fun ProgramingContent(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .clickable { navController.navigate(route = DetailsScreen.ProgramingLine.route) },
            image = painterResource(id = R.drawable.programing_oxford),
            title = "Programación Camisa de Linea")
        DefaultCard(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            image = painterResource(id = R.drawable.programing_special),
            title = "Programación Pedido Especifico")

    }
}

