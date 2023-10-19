package com.portosdeveloper.protecadminapp.presentation.screens.SearchScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.navigation.DetailsScreen
import com.portosdeveloper.protecadminapp.presentation.screens.InputMaterialScreen.components.DefaultCard
import com.portosdeveloper.protecadminapp.presentation.ui.theme.ProtecAdminAppTheme

@Composable
fun SearchContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 20.dp
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .clickable {
                    navController.navigate(route = DetailsScreen.SearchWithQR.route)
                },
            image = painterResource(id = R.drawable.search_qr),
            title = "Búsqueda por Código QR"
        )
        DefaultCard(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .clickable {
                    navController.navigate(route = DetailsScreen.SearchWithNumber.route)
                },
            image = painterResource(id = R.drawable.search_route),
            title = "Búsqueda por Numero de Paquete"
        )


    }
}

