package com.portosdeveloper.protecadminapp.presentation.screens.InputThreadScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.presentation.components.DefaultButton
import com.portosdeveloper.protecadminapp.presentation.components.DefaultDropDownMenu
import com.portosdeveloper.protecadminapp.presentation.components.DefaultInputDropDownMenu
import com.portosdeveloper.protecadminapp.presentation.components.DefaultInputTextField
import com.portosdeveloper.protecadminapp.presentation.screens.InputThreadScreen.InputThreadViewModel
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray700

@Composable
fun InputThreadContent(viewModel: InputThreadViewModel = hiltViewModel()){

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(30.dp)
        )
        DefaultInputDropDownMenu(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            modifierDropDownMenu = Modifier.fillMaxWidth().height(200.dp),
            state = state.yards,
            onValueChange ={ viewModel.onYardsInput(it)} ,
            validateField = { viewModel.validateYard()},
            label = "Cantidad de Hilo",
            list = state.yardList,
            errorMsg = viewModel.yardErrMsg
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = "Porfavor de click en el '+' para adicionar un nuevo campo.",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
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
                ) {
                    Column() {
                        viewModel.stateFunction.forEachIndexed { index, value ->
                            DefaultInputDropDownMenu(
                                modifier = Modifier.size(height = 63.dp, width = 130.dp),
                                modifierDropDownMenu = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                state = value,
                                onValueChange = { newValue -> viewModel.onFunctionInput(index, newValue) },
                                validateField = { viewModel.validateFunction(index) },
                                validateList = { viewModel.validateAllFunction() },
                                validateTotal = {viewModel.onGetTotalThread(index)},
                                label = "Parte",
                                list = state.useList,
                                errorMsg = viewModel.functionErrMsg[index]
                            )
                        }
                    }
                    Column() {
                        viewModel.stateColor.forEachIndexed { index, value ->
                            DefaultInputDropDownMenu(
                                modifier = Modifier.size(height = 63.dp, width = 130.dp),
                                modifierDropDownMenu = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                state = value,
                                onValueChange = { newValue -> viewModel.onColorInput(index, newValue) },
                                validateField = { viewModel.validateColor(index) },
                                validateList = { viewModel.validateAllColor() },
                                validateTotal = {viewModel.onGetTotalThread(index)},
                                label = "Color",
                                list = state.colorList,
                                errorMsg = viewModel.colorErrMsg[index]
                            )

                        }
                    }
                    Column() {
                        viewModel.stateQuantity.forEachIndexed { index, value ->
                            DefaultInputTextField(
                                modifier = Modifier.size(height = 63.dp, width = 130.dp),
                                state = value,
                                onValueChange = { newValue -> viewModel.onQuantityInput(index, newValue) },
                                validateField = { viewModel.validateQuantity(index) },
                                validateList = { viewModel.validateAllQuantity() },
                                label = "Cantidad",
                                errorMsg = viewModel.quantityErrMsg[index]
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = {
                        viewModel.isEnabledInputThreadButton = false
                        viewModel.isFunctionValid = false
                        viewModel.isColorValid = false
                        viewModel.isQuantityValid = false
                        viewModel.addInputThreadRow()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "IconAdd"
                        )
                    }
                    if (viewModel.stateFunction.size > 1) {
                        IconButton(onClick = {
                            viewModel.removeInputThreadRow()
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
                viewModel.stateQuantity.forEachIndexed { index, _ ->
                    if(viewModel.stateThreadInBBDD[index] != "0"){
                        viewModel.onUpdateThread(index)
                    }else{
                        viewModel.onCreateThread(index)
                    }
                }
            },
            enabled = viewModel.isEnabledInputThreadButton
        )

    }

}