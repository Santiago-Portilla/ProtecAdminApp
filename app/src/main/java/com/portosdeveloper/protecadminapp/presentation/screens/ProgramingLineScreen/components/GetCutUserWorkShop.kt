package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.ProgramingLineViewModel

@Composable
fun GetCutUserWorkShop(viewModel: ProgramingLineViewModel = hiltViewModel()){
    val state = viewModel.state
    when(val getCutUserWorkShopResponse = viewModel.getUserWorkShopResponse){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            getCutUserWorkShopResponse.data.forEachIndexed {
                    index, userWorkShop ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (userWorkShop == state.cutUserWorkShop),
                            onClick = {
                                viewModel.cutUserWorkShopInput(userWorkShop)
                                viewModel.isValidCutUserSelected()
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = ( userWorkShop == state.cutUserWorkShop ),
                        onClick = {
                            viewModel.cutUserWorkShopInput(userWorkShop)
                            viewModel.isValidCutUserSelected()
                        }
                    )
                    Row() {
                        Text(
                            modifier = Modifier
                                .padding( start = 12.dp),
                            text = "${userWorkShop.name} ${userWorkShop.surName}"
                        )
                    }

                }
            }

        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, getCutUserWorkShopResponse.exception.message  ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }
        else ->{}
    }
}