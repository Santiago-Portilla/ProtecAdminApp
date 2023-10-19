package com.portosdeveloper.protecadminapp.presentation.screens.StockProgress.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.presentation.screens.StockProgress.StockProgressViewModel
import com.portosdeveloper.protecadminapp.domain.model.Package

@Composable
fun StockProgressContent(
    viewModel: StockProgressViewModel = hiltViewModel(),
    packageShirts : List<Package>

){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 55.dp
            ),
    ){
        items(
            items = packageShirts
        ) {packageShirt ->
            PackageCards(packageShirt = packageShirt)

        }
    }
}