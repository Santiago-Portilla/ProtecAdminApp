package com.portosdeveloper.protecadminapp.presentation.screens.InputMaterialScreen.components

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

@Composable
fun InputMaterialContent(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 60.dp
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp , bottom = 10.dp)
                .clickable { navController.navigate(route = DetailsScreen.InputCloth.route) },
            image = painterResource(id = R.drawable.input_cloth),
            title = "Ingreso Tela")
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp , bottom = 10.dp)
                .clickable { navController.navigate(route = DetailsScreen.InputPlotter.route) },
            image = painterResource(id = R.drawable.input_plotter),
            title = "Ingreso Plotter")
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp , bottom = 10.dp)
                .clickable { navController.navigate(route = DetailsScreen.InputWadding.route) },
            image = painterResource(id = R.drawable.input_wadding),
            title = "Ingreso Troquelado")
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp , bottom = 10.dp)
                .clickable { navController.navigate(route = DetailsScreen.InputRollWadding.route) },
            image = painterResource(id = R.drawable.input_roll_wading),
            title = "Ingreso Rollos Troquelado")
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp , bottom = 10.dp)
                .clickable { navController.navigate(route = DetailsScreen.InputThread.route) },
            image = painterResource(id = R.drawable.input_threads),
            title = "Ingreso Hilo")
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp , bottom = 10.dp)
                .clickable { navController.navigate(route = DetailsScreen.InputYarn.route) },
            image = painterResource(id = R.drawable.input_yarns),
            title = "Ingreso Hilazas")
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp , bottom = 10.dp)
                .clickable { navController.navigate(route = DetailsScreen.InputReflective.route) },
            image = painterResource(id = R.drawable.input_reflective),
            title = "Ingreso Reflectivo")
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp , bottom = 10.dp)
                .clickable { navController.navigate(route = DetailsScreen.InputButton.route) },
            image = painterResource(id = R.drawable.input_button),
            title = "Ingreso Boton")
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp , bottom = 10.dp)
                .clickable { navController.navigate(route = DetailsScreen.InputPacking.route) },
            image = painterResource(id = R.drawable.input_packing),
            title = "Ingreso Empaque")
    }
}

