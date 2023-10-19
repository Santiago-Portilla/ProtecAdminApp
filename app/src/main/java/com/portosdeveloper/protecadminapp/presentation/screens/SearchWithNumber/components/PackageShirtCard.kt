package com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portosdeveloper.protecadminapp.domain.model.Package
import com.portosdeveloper.protecadminapp.presentation.components.QRCodeGenerator


@Composable
fun PackageShirtCard(packageShirt: Package) {

    Card(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(1.dp, Color.Black),
        backgroundColor = Color.White,
        contentColor = Color.Black,

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                QRCodeGenerator(
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f),
                    text = packageShirt.id,
                    size = 500
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    text = packageShirt.id,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            TextCardPackage(title = "NOMBRE", personal = packageShirt.name)
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f),
                    text = "GENERO",
                    fontWeight = FontWeight.Bold
                )
                when (packageShirt.gender) {
                    "C" -> {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically),
                            text = "Camisa"
                        )
                    }
                    "B" -> {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically),
                            text = "Blusa"
                        )
                    }
                    else -> {}
                }

            }

            TextCardPackage(title = "CANTIDAD", personal = packageShirt.quantity)
            TextCardPackage(title = "TALLA", personal = packageShirt.size)
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(15.dp),
                text = "PROGRESO",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            TextCardPackage(title = "CORTE", personal = packageShirt.cutJob)
            TextCardPackage(title = "FUSIONADO", personal = packageShirt.mergedJob)
            TextCardPackage(title = "COSE Y CORTA", personal = packageShirt.cutNecksFistsJob)
            TextCardPackage(title = "CUELLOS", personal = packageShirt.necksFistsJob)
            TextCardPackage(title = "CUERPOS", personal = packageShirt.bodiesJob)
            TextCardPackage(title = "CERRADORA", personal = packageShirt.closedJob)
            TextCardPackage(title = "TERMINACION", personal = packageShirt.terminationJob)
            TextCardPackage(title = "OJAL Y BOTON", personal = packageShirt.buttonHoleButtonJob)
            TextCardPackage(title = "REMATE", personal = packageShirt.polishJob)
            TextCardPackage(title = "EMPAQUE", personal = packageShirt.packingJob)
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(15.dp),
                text = "FECHA DE PAGO",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            TextCardPackage(title = "CORTE", personal = packageShirt.cutJobPaid)
            TextCardPackage(title = "FUSIONADO", personal = packageShirt.mergedJobPaid)
            TextCardPackage(title = "COSE Y CORTA", personal = packageShirt.cutNecksFistsJobPaid)
            TextCardPackage(title = "CUELLOS", personal = packageShirt.necksFistsJobPaid)
            TextCardPackage(title = "CUERPOS", personal = packageShirt.bodiesJobPaid)
            TextCardPackage(title = "CERRADORA", personal = packageShirt.closedJobPaid)
            TextCardPackage(title = "TERMINACION", personal = packageShirt.terminationJobPaid)
            TextCardPackage(title = "OJAL Y BOTON", personal = packageShirt.buttonHoleButtonJobPaid)
            TextCardPackage(title = "REMATE", personal = packageShirt.polishJobPaid)
            TextCardPackage(title = "EMPAQUE", personal = packageShirt.packingJobPaid)

        }
    }

}
