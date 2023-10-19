package com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.presentation.components.DefaultButtonImage
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTextField
import com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber.SearchWithNumberViewModel

@Composable
fun SearchWithNumberContent(viewModel: SearchWithNumberViewModel = hiltViewModel()){

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(0.85f),
                value = state.id,
                onValueChange = {viewModel.onInputId(it)},
                validateField = {viewModel.validatePackage()},
                label = "Ingrese Id del paquete",
                errorMsg = viewModel.packageErrMsg
            )
            DefaultButtonImage(
                modifier = Modifier.padding(top = 10.dp, start = 5.dp),
                text = "",
                onClick = {
                    viewModel.getPackageById(state.id)
                },
                icon = painterResource(id = R.drawable.icon_search),
                enabled = viewModel.isEnabledSearchButton
            )
        }
        GetPackageById()
    }

}