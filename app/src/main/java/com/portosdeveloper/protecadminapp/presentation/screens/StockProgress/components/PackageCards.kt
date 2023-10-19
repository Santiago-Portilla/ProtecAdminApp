package com.portosdeveloper.protecadminapp.presentation.screens.StockProgress.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portosdeveloper.protecadminapp.domain.model.Package
import com.portosdeveloper.protecadminapp.presentation.components.QRCodeGenerator
import com.portosdeveloper.protecadminapp.presentation.ui.theme.*

@Composable
fun PackageCards(
    packageShirt: Package
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(1.dp, Color.Black),
        backgroundColor = Color.White
    ) {
        Column() {
            Row() {
                QRCodeGenerator(
                    modifier = Modifier.padding(3.dp),
                    text = packageShirt.id,
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
                            text = packageShirt.id,
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
                            text = packageShirt.name,
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
                            text = packageShirt.quantity,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
            Row(){
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Progreso : ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = packageShirt.ubication,
                    fontSize = 15.sp
                )
                if(packageShirt.ubication == "Cuerpos" || packageShirt.ubication == "Cerradora" ){
                    if(packageShirt.mergedFistsNecks != ""){
                        Text(
                            text = ", Cuellos/Puños",
                            fontSize = 15.sp
                        )
                    }else{
                        Text(
                            text = ", Sin Cuellos/Puños",
                            fontSize = 15.sp
                        )
                    }
                }
                if(packageShirt.ubication == "Cerradora"){
                    if(packageShirt.mergedClosed != ""){
                        Text(
                            text = ", Cerrado",
                            fontSize = 15.sp
                        )
                    }else{
                        Text(
                            text = ", Sin Cerrado",
                            fontSize = 15.sp
                        )
                    }
                }
            }
            Box{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(7.dp)
                        .background(Color.LightGray)
                )
                when(packageShirt.ubication) {
                    "Corte" -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.125f)
                                .padding(10.dp)
                                .height(7.dp)
                                .background(Red)
                        )
                    }
                    "Fusionado" -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.25f)
                                .padding(10.dp)
                                .height(7.dp)
                                .background(LivingCrimson)
                        )
                    }
                    "Cuerpos" -> {
                        if(packageShirt.mergedFistsNecks ==""){
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.375f)
                                    .padding(10.dp)
                                    .height(7.dp)
                                    .background(Yellow)
                            )
                        }else{
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.375f)
                                    .padding(10.dp)
                                    .height(7.dp)
                                    .background(Flirty)
                            )
                        }
                    }
                    "Cerradora" -> {
                        if(packageShirt.mergedFistsNecks == ""){
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(10.dp)
                                    .height(7.dp)
                                    .background(Orange)
                            )
                        }else{
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(10.dp)
                                    .height(7.dp)
                                    .background(ChinesePurple)
                            )
                        }
                    }
                    "CerradoraOk" ->{
                        if(packageShirt.mergedFistsNecks == ""){
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(10.dp)
                                    .height(7.dp)
                                    .background(Orange)
                            )
                        }else{
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(10.dp)
                                    .height(7.dp)
                                    .background(ChinesePurple)
                            )
                        }
                    }
                    "Terminacion" -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.625f)
                                .padding(10.dp)
                                .height(7.dp)
                                .background(InternationalBlue)
                        )
                    }
                    "Ojal y Boton" -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .padding(10.dp)
                                .height(7.dp)
                                .background(DarkBlue)
                        )
                    }
                    "Remate" -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.875f)
                                .padding(10.dp)
                                .height(7.dp)
                                .background(LightBlue)
                        )
                    }
                    "Empaque" -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(10.dp)
                                .height(7.dp)
                                .background(Turquoise)
                        )
                    }
                }
            }

        }


    }
}

