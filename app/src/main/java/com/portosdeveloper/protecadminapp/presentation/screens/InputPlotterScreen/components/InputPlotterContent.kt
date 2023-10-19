package com.portosdeveloper.protecadminapp.presentation.screens.InputPlotterScreen.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.components.*
import com.portosdeveloper.protecadminapp.presentation.screens.InputPlotterScreen.InputPlotterViewModel
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray700

@Composable
fun InputPlotterContent(viewModel : InputPlotterViewModel = hiltViewModel()) {

    val state = viewModel.state

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(30.dp)
        )
        DefaultTextFieldImage(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.width,
            onValueChange = { width -> viewModel.onWidthInput(width) },
            validateField = { viewModel.validateWidth() },
            errorMsg = viewModel.widthErrMsg,
            label = "Ancho",
            icon = painterResource(id = R.drawable.icon_width),
            keyboartype = KeyboardType.Number
        )
        DefaultTextFieldImage(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.height,
            onValueChange = { bill -> viewModel.onHeightInput(bill) },
            validateField = { viewModel.validateHeight() },
            errorMsg = viewModel.heightErrMsg,
            label = "Alto",
            icon = painterResource(id = R.drawable.icon_height),
            keyboartype = KeyboardType.Number
        )
        Card(
            modifier = Modifier
                .padding(
                    start = 5.dp,
                    end = 5.dp
                ),
            backgroundColor = Gray700
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 1.dp, end = 1.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Column() {
                        viewModel.stateSize.forEachIndexed { index, value ->
                            DefaultInputDropDownMenu(
                                modifier = Modifier.size(height = 63.dp, width = 200.dp),
                                modifierDropDownMenu = Modifier.fillMaxWidth().height(200.dp),
                                state = value,
                                onValueChange = { newValue -> viewModel.onSizeInput(index, newValue) },
                                validateField = { viewModel.validateSize(index) },
                                validateList = { viewModel.validateAllSize() },
                                label = "Talla",
                                list = state.sizesList,
                                errorMsg = viewModel.sizeErrMsg[index]
                            )
                        }
                    }
                    Column() {
                        viewModel.stateGender.forEachIndexed { index, value ->
                            DefaultInputDropDownMenu(
                                modifier = Modifier.size(height = 63.dp, width = 200.dp),
                                modifierDropDownMenu = Modifier.fillMaxWidth().height(100.dp),
                                state = value,
                                onValueChange = { newValue -> viewModel.onGenderInput(index, newValue) },
                                validateField = { viewModel.validateGender(index) },
                                validateList = { viewModel.validateAllGender() },
                                label = "Genero",
                                list = state.genderList,
                                errorMsg = viewModel.genderErrMsg[index]
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = {
                        viewModel.isEnabledInputPlotterButton = false
                        viewModel.isSizeValid = false
                        viewModel.isGenderValid = false
                        viewModel.addTextFieldSize()
                        viewModel.addTextFieldGender()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "IconAdd"
                        )
                    }
                    if (viewModel.stateSize.size > 1) {
                        IconButton(onClick = {
                            viewModel.removeTextFieldSize()
                            viewModel.removeTextFieldGender()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "IconAdd"
                            )
                        }
                    }
                }
            }
        }
        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 5.dp, end = 5.dp),
            text = "CARGAR",
            onClick = {
                viewModel.stateGender.forEachIndexed { index, value ->
                    viewModel.plotterSizes(index)
                }
                viewModel.onCretePlotter()
            },

            enabled = viewModel.isEnabledInputPlotterButton
        )
    }
}




