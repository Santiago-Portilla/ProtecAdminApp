package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import com.portosdeveloper.protecadminapp.domain.model.Package
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.presentation.components.DefaultButton
import com.portosdeveloper.protecadminapp.presentation.components.DefaultDropDownMenu
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTextField
import com.portosdeveloper.protecadminapp.presentation.components.QRCodeGenerator
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.ProgramingLineViewModel
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray700

@Composable
fun ProgramingLineContent(viewModel: ProgramingLineViewModel = hiltViewModel()){

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Gray700

        ) {
            Column {

                if(state.buttonAppear){
                    DefaultTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        value = state.plotter,
                        onValueChange ={ plotter -> viewModel.onPlotterInput(plotter) } ,
                        totalInBBDD = { viewModel.onSpecificPlotterList() },
                        label = "Ingrese Tallas"
                    )
                    GetPlotterList()
                }
                if(state.selectClothAppear){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Column(){
                            DefaultDropDownMenu(
                                modifier = Modifier
                                    .size(height = 63.dp, width = 120.dp),
                                modifierDropDownMenu = Modifier
                                    .fillMaxWidth()
                                    .height(130.dp),
                                value = state.clothText1,
                                onValueChange = { viewModel.onClothText1Input(it) },
                                validateField = { viewModel.appearColorOne = true },
                                validateTotal = { state.sizesList.forEachIndexed { index, s -> viewModel.onGetWaddingInput(index) }},
                                label = "Tela",
                                listItem = state.clothList,
                                onClick = {viewModel.onClothText1Input(it)},
                            )
                            if(viewModel.appearColorOne){
                                DefaultDropDownMenu(
                                    modifier = Modifier
                                        .size(height = 63.dp, width = 120.dp),
                                    modifierDropDownMenu = Modifier
                                        .fillMaxWidth()
                                        .height(130.dp),
                                    value = state.colorText1,
                                    onValueChange ={ viewModel.onColorText1Input(it) } ,
                                    label = "Color",
                                    listItem = state.colorList,
                                    validateTotal = { viewModel.onSpecificClothListOne() },
                                    onClick = {viewModel.onColorText1Input(it) }
                                )
                            }
                        }
                        Column() {
                            DefaultDropDownMenu(
                                modifier = Modifier
                                    .size(height = 63.dp, width = 120.dp),
                                modifierDropDownMenu = Modifier
                                    .fillMaxWidth()
                                    .height(130.dp),
                                value = state.clothText2,
                                onValueChange = { viewModel.onClothText2Input(it) },
                                validateField = { viewModel.appearColorTwo = true },
                                label = "Tela",
                                listItem = state.clothList,
                                onClick = { viewModel.onClothText2Input(it) }
                            )
                            if(viewModel.appearColorTwo){
                                DefaultDropDownMenu(
                                    modifier = Modifier
                                        .size(height = 63.dp, width = 120.dp),
                                    modifierDropDownMenu = Modifier
                                        .fillMaxWidth()
                                        .height(130.dp),
                                    value = state.colorText2,
                                    onValueChange ={ viewModel.onColorText2Input(it) } ,
                                    validateTotal = { viewModel.onSpecificClothListTwo() },
                                    label = "Color",
                                    listItem = state.colorList,
                                    onClick = { viewModel.onColorText2Input(it) }

                                )
                            }
                        }
                        Column(){
                            DefaultDropDownMenu(
                                modifier = Modifier
                                    .size(height = 63.dp, width = 120.dp),
                                modifierDropDownMenu = Modifier
                                    .fillMaxWidth()
                                    .height(130.dp),
                                value = state.clothText3,
                                onValueChange = { viewModel.onClothText3Input(it)},
                                validateField = { viewModel.appearColorThree = true },
                                label = "Tela",
                                listItem = state.clothList,
                                onClick = {viewModel.onClothText3Input(it)},
                            )
                            if(viewModel.appearColorThree){
                                DefaultDropDownMenu(
                                    modifier = Modifier
                                        .size(height = 63.dp, width = 120.dp),
                                    modifierDropDownMenu = Modifier
                                        .fillMaxWidth()
                                        .height(130.dp),
                                    value = state.colorText3,
                                    onValueChange ={viewModel.onColorText3Input(it)} ,
                                    validateTotal = { viewModel.onSpecificClothListThree() },
                                    label = "Color",
                                    listItem = state.colorList,
                                    onClick = {viewModel.onColorText3Input(it)},
                                )
                            }

                        }
                    }
                    Text(text = "Seleccione Cortador")
                    GetCutUserWorkShop()
                    GetClothListOne(
                        label="${state.clothText1}  ${state.colorText1}",
                        totalMeasure = viewModel.totalMeasureOneSelectedOne())
                    GetClothListTwo(
                        label="${state.clothText2}  ${state.colorText2}",
                        totalMeasure = viewModel.totalMeasureOneSelectedTwo())
                    GetClothListThree(
                        label="${state.clothText3}  ${state.colorText3}",
                        totalMeasure = viewModel.totalMeasureOneSelectedThree())
                    DefaultButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        text = "SIMULAR",
                        onClick = {
                            viewModel.tableSimulate = true
                            state.selectClothAppear = false
                        },
                        enabled = viewModel.isValidSimulate
                    )

                }
                if(viewModel.tableSimulate){
                    var id = state.idPackageBBDD.toInt()
                    state.sizesList.forEachIndexed { indexSizes, optionSizes ->
                        viewModel.clothSelected.forEachIndexed { indexCloth , optionCloth ->
                            viewModel.onSimulateListInput(
                                numberId = "$id",
                                sizes = optionSizes,
                                color = optionCloth.color,
                                measure = optionCloth.measure
                            )
                            id++

                        }
                    }
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Consecutivo",
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "Nombre",
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "Cantidad",
                                modifier = Modifier.weight(1f)
                            )
                        }
                        viewModel.simulateList.forEachIndexed { index, option ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = option.id,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 15.dp)
                                )
                                Text(
                                    text = option.name,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 10.dp)
                                )
                                Text(
                                    text = option.quantity,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 20.dp)
                                )
                            }
                        }

                    }
                    DefaultButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        text = "Previsualizar Programcion",
                        onClick = {
                            viewModel.tableSimulate = false
                            viewModel.simulateList.forEachIndexed { index, p ->
                                viewModel.getShirtById(p.name)
                            }
                            state.finalProgramingAppear = true
                        }
                    )
                }
                if(state.finalProgramingAppear){
                    Column {
                        viewModel.simulateList.forEachIndexed { index, p ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .border(1.dp, Color.Black),
                                backgroundColor = Color.White
                            ) {

                                Row() {
                                    QRCodeGenerator(
                                        modifier = Modifier.padding(3.dp),
                                        text = p.id,
                                        size = 500
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 10.dp, top = 15.dp),
                                            horizontalArrangement = Arrangement.SpaceEvenly
                                        ) {
                                            Text(
                                                modifier = Modifier
                                                    .weight(1f),
                                                text = "Id",
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .padding(start = 10.dp),
                                                text = p.id,
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 10.dp, top = 15.dp),
                                            horizontalArrangement = Arrangement.SpaceEvenly
                                        ) {
                                            Text(
                                                modifier = Modifier
                                                    .weight(1f),
                                                text = "Nombre",
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .padding(start = 10.dp),
                                                text = p.name,
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 10.dp, top = 15.dp),
                                            horizontalArrangement = Arrangement.SpaceEvenly
                                        ) {
                                            Text(
                                                modifier = Modifier
                                                    .weight(1f),
                                                text = "Cantidad",
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .padding(start = 10.dp),
                                                text = p.quantity,
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }

                                    }
                                }

                            }
                        }
                        DefaultButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            text = "Ingresar",
                            onClick = {
                                viewModel.simulateList.forEachIndexed { index, p ->
                                    viewModel.createPackage(p)
                                    viewModel.updateWorkProgressList(p.id)
                                    viewModel.onUpdateOrCreateShirt(p.name,p.quantity,index)
                                }
                                viewModel.onUpdateTotalCloth()
                                viewModel.clothSelected.forEachIndexed { index, cloth ->
                                    viewModel.updateCloth(cloth)
                                }
                                viewModel.updatePlotter()
                                state.sizesList.forEachIndexed { index, s ->
                                    viewModel.onUpdateWadding(index)
                                }
                            }
                        )
                    }

                }

            }



        }

    }

}