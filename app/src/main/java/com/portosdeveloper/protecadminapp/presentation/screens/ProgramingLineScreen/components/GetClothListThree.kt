package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Cloth
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.ProgramingLineViewModel
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray700

@Composable
fun GetClothListThree (
    label: String,
    viewModel: ProgramingLineViewModel = hiltViewModel(),
    totalMeasure: String
){
    when(val clothListResponse = viewModel.clothListThreeResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{

            val cloth = Cloth()
            val size = clothListResponse.data.size
            viewModel.onClothInBBDDSizeThree(size,cloth)
            var expanded by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 5.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                    .height(if (expanded) 200.dp else 50.dp)
                ,
                backgroundColor = Gray700
            ) {
                Column() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp)
                            .clickable { expanded = !expanded },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = label,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    if (expanded) {
                        Column(
                            modifier = Modifier.verticalScroll(rememberScrollState())
                        ) {
                            Row() {
                                Text(
                                    modifier = Modifier.padding(start = 25.dp),
                                    text = "Metros seleccionados"
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    modifier = Modifier.padding(end = 25.dp),
                                    text = totalMeasure
                                )
                            }
                            clothListResponse.data.forEachIndexed { index, option ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .selectable(
                                            selected = (viewModel.clothInBBDDThree[index] == clothListResponse.data[index]),
                                            onClick = {
                                                viewModel.containsClothInBBDDThree(index, option, cloth)
                                                viewModel.containsClothSelectedThree(option, option.measure.toDouble())
                                                viewModel.isValidClothSelected3()
                                            }
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = ( viewModel.clothInBBDDThree[index] == clothListResponse.data[index] ),
                                        onClick = {
                                            viewModel.containsClothInBBDDThree(index,option,cloth)
                                            viewModel.containsClothSelectedThree(option,option.measure.toDouble())
                                            viewModel.isValidClothSelected3()
                                        }
                                    )
                                    Row() {
                                        Text(
                                            modifier = Modifier
                                                .padding( start = 12.dp),
                                            text = option.id
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(
                                            modifier = Modifier
                                                .padding( end = 25.dp),
                                            text = option.measure
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }

        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, clothListResponse.exception.message ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}