package com.portosdeveloper.protecadminapp.presentation.screens.StockSuppliesScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.portosdeveloper.protecadminapp.R

@Composable
fun StockSuppliesContent(){

    val items = listOf("Item 1", "Item 2", "Item 3")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 20.dp,
                bottom = 60.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        GetTotalsCloth(
            iconPainter = painterResource(id = R.drawable.icon_roll_wadding),
            label = "Tela")

        GetPlotter(
            iconPainter = painterResource(id = R.drawable.icon_plotter),
            label = "Plotter")

        GetWadding(
            iconPainter = painterResource(id = R.drawable.icon_wadding),
            label = "Entretela")

        GetRollWadding(
            iconPainter = painterResource(id = R.drawable.icon_rolls_wadding),
            label = "Rollos de Entretela")

        GetThread(
            iconPainter = painterResource(id = R.drawable.icon_thread),
            label = "Hilos",)

        GetYarn(
            iconPainter = painterResource(id = R.drawable.icon_yarn),
            label = "Hilazas")

        GetReflective(
            iconPainter = painterResource(id = R.drawable.icon_reflective),
            label = "Reflectivos"
        )

        GetButton(
            iconPainter = painterResource(id = R.drawable.icon_button),
            label = "Botones"
        )

        GetButton(
            iconPainter = painterResource(id = R.drawable.icon_paper_board),
            label = "Empaque",
        )


    }
}