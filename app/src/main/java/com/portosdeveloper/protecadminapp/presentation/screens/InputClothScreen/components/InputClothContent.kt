package com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.components

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.components.*
import com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.InputClothViewModel
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray700

@Composable
fun InputClothContent(viewModel: InputClothViewModel = hiltViewModel()) {

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
        DefaultDropDownMenuImage(
            modifierOutlineTextField = Modifier.fillMaxWidth(),
            modifierDropDownMenu = Modifier.fillMaxWidth().height(200.dp),
            value = state.cloth,
            onValueChange = { cloth -> viewModel.onClothInput(cloth) },
            validateField = { viewModel.validateCloth() },
            label = "Tela",
            errorMsg = viewModel.clothErrMsg,
            readOnly = true,
            listItem = state.clothList,
            onClick = { cloth -> viewModel.onClothInput(cloth) },
            image = painterResource(id = R.drawable.icon_cloth)
        )
        DefaultDropDownMenuImage(
            modifierOutlineTextField = Modifier.fillMaxWidth(),
            modifierDropDownMenu = Modifier.fillMaxWidth().height(200.dp),
            value = state.color,
            onValueChange = { color -> viewModel.onColorInput(color) },
            validateField = { viewModel.validateColor() },
            label = "Color",
            errorMsg = viewModel.colorErrMsg,
            readOnly = true,
            listItem = state.colorList,
            onClick = { color -> viewModel.onColorInput(color) },
            image = painterResource(id = R.drawable.icon_color)
        )
        DefaultTextFieldImage(
            modifier = Modifier.fillMaxWidth(),
            value = state.bill,
            onValueChange = { bill -> viewModel.onBillsInput(bill) },
            validateField = { viewModel.validateBill() },
            errorMsg = viewModel.billErrMsg,
            label = "Remision",
            icon = painterResource(id = R.drawable.icon_bill),
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
                ) {
                    Column() {
                        viewModel.stateConsecutive.forEachIndexed { index, value ->
                            DefaultInputTextField(
                                modifier = Modifier.size(height = 63.dp, width = 130.dp),
                                state = value,
                                onValueChange = { newValue -> viewModel.onConsecutiveInput(index, newValue) },
                                validateField = { viewModel.validateConsecutive(index) },
                                validateList = { viewModel.validateAllConsecutive() },
                                label = "Consecutivo",
                                errorMsg = viewModel.consecutiveErrMsg[index],
                                keyboartype = KeyboardType.Number
                            )

                        }
                    }
                    Column() {
                        viewModel.stateIdConsecutive.forEachIndexed { index, value ->
                            DefaultInputTextField(
                                modifier = Modifier.size(height = 63.dp, width = 130.dp),
                                state = value,
                                onValueChange = { newValue -> viewModel.onIdConsecutiveInput(index,newValue) },
                                validateField = { viewModel.validateIdConsecutive(index) },
                                validateList = { viewModel.validateAllIdConsecutive() },
                                label = "Id",
                                errorMsg = viewModel.idConsecutiveErrMsg[index],
                                keyboartype = KeyboardType.Number
                            )

                        }
                    }
                    Column() {
                        viewModel.stateMeasure.forEachIndexed { index, value ->
                            DefaultInputTextField(
                                modifier = Modifier.size(height = 63.dp, width = 130.dp),
                                state = value,
                                onValueChange = { newValue -> viewModel.onMeasureInput(index, newValue) },
                                validateField = { viewModel.validateMeasure(index) },
                                validateList = { viewModel.validateAllMeasure() },
                                validateTotal = { viewModel.onTotalMeasureInput() },
                                totalInBBDD = { viewModel.getTotalMeasureById() },
                                label = "Metraje",
                                errorMsg = viewModel.measureErrMsg[index],
                                keyboartype = KeyboardType.Number
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = {
                        viewModel.isEnabledInputClothButton = false
                        viewModel.isConsecutiveValid = false
                        viewModel.isIdConsecutiveValid = false
                        viewModel.isMeasureValid = false
                        viewModel.addTextFieldConsecutive()
                        viewModel.addTextFieldIdConsecutive()
                        viewModel.addTextFieldMeasure()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "IconAdd"
                        )
                    }
                    if (viewModel.stateConsecutive.size > 1) {
                        IconButton(onClick = {
                            viewModel.removeTextFieldConsecutive()
                            viewModel.removeTextFieldIdConsecutive()
                            viewModel.removeTextFieldMeasure()
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

                viewModel.stateConsecutive.forEachIndexed { index, _ ->
                    viewModel.onCreateCloth(index)
                }
                viewModel.stateIdConsecutive.forEachIndexed { index, _ ->
                    viewModel.onCreateCloth(index)
                }
                viewModel.stateMeasure.forEachIndexed { index, _ ->
                    viewModel.onCreateCloth(index)
                }

            },
            enabled = viewModel.isEnabledInputClothButton
        )

    }
}




