package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultButton
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.ProgramingLineViewModel

@Composable
fun GetPlotterList(viewModel: ProgramingLineViewModel = hiltViewModel()){
     val current = LocalContext.current

    when(val plotterListResponse = viewModel.plotterListResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            plotterListResponse.data.forEachIndexed { index, option ->
                Row() {
                    DefaultButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        text = "${option.id} - ${option.sizes}  Ancho:${option.width}  Alto:${option.height}",
                        onClick = {
                            viewModel.onPlotterSelectedInput(option)
                            viewModel.onSizesListInput(option.sizes)
                            Toast.makeText(current, "A Seleccionado Plotter ${option.id}", Toast.LENGTH_SHORT).show()
                            viewModel.state.buttonAppear = false
                            viewModel.state.selectClothAppear = true
                        })
                }
            }
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, plotterListResponse.exception.message ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }


}