package com.portosdeveloper.protecadminapp.presentation.screens.StockSuppliesScreen.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.presentation.components.DefaultProgressBar
import com.portosdeveloper.protecadminapp.presentation.screens.StockSuppliesScreen.StockSuppliesViewModel
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray700

@Composable
fun GetRollWadding(
    iconPainter: Painter,
    label : String,
    viewModel: StockSuppliesViewModel = hiltViewModel()){
    when(
        val rollWaddingResponse = viewModel.stockRollWaddingResponse
    ){
        Response.Loading ->{
            DefaultProgressBar()
        }
        is Response.Success ->{
            var expanded by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 5.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                backgroundColor = Gray700
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp)
                            .clickable { expanded = !expanded },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = iconPainter,
                            contentDescription ="")
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
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ){
                            items(
                                items = rollWaddingResponse.data
                            ){
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        modifier = Modifier.padding(start = 15.dp),
                                        text = it.id)
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        modifier = Modifier.padding(end = 15.dp),
                                        text = it.totalRollWadding
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, rollWaddingResponse.exception.message ?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}