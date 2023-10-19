package com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.UserWorkShopViewModel
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray700
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.components.DefaultButton
import com.portosdeveloper.protecadminapp.presentation.components.DefaultButtonImage
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Green200


@Composable
fun UserWorkShopContent(viewModel: UserWorkShopViewModel = hiltViewModel()) {

    var expanded1 by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            backgroundColor = Gray700
        ) {
            Row(

            ) {
                if (viewModel.userWorkShop.image != "") {
                    AsyncImage(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape),
                        model = viewModel.userWorkShop.image,
                        contentDescription = "User Image",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "ImageUser"
                    )
                }
                Column(
                    modifier = Modifier.padding(top = 100.dp)
                ) {
                    Text(
                        text = "${viewModel.userWorkShop.name} ${viewModel.userWorkShop.surName}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = viewModel.userWorkShop.job,
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            backgroundColor = Gray700

        ) {
            Column() {
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Hoy",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.padding(end = 10.dp),
                        text = "$ ${viewModel.userWorkShop.workDay}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Quincena",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.padding(end = 10.dp),
                        text = "$ ${viewModel.userWorkShop.workWeek}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Mes",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.padding(end = 10.dp),
                        text = "$ ${viewModel.userWorkShop.workMonth}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Generado",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.padding(end = 10.dp),
                        text = "$ ${viewModel.userWorkShop.workLife}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            backgroundColor = Gray700

        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                        .clickable { expanded2 = !expanded2 },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        text = "Trabajando",
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
                if (expanded2) {
                    if (viewModel.userWorkShop.workListInProgress.isEmpty()) {
                        Text(
                            modifier = Modifier.padding(start = 15.dp),
                            text = "Aun no ha empezado",
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 20.dp),
                                text = "Paquete",
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 20.dp),
                                text = "Fecha",
                                fontWeight = FontWeight.Bold
                            )
                        }
                        viewModel.userWorkShop.workListInProgress.forEachIndexed { index, string ->

                            val parts = string.split(",")
                            val id = parts.getOrElse(0) { "" }
                            val date = parts.getOrElse(1) { "" }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 20.dp),
                                    text = "- $id",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 10.dp),
                                    text = date
                                )
                            }
                        }
                    }
                }

            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            backgroundColor = Gray700

        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                        .clickable { expanded1 = !expanded1 },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        text = "Trabajado",
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
                if (expanded1) {
                    if (viewModel.userWorkShop.workListFinish.isEmpty()) {
                        Text(
                            modifier = Modifier.padding(start = 15.dp),
                            text = "Aun no ha empezado",
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 20.dp),
                                text = "Paquete",
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 20.dp),
                                text = "Fecha",
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 10.dp),
                                text = "Cantidad",
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 20.dp),
                                text = "Total",
                                fontWeight = FontWeight.Bold
                            )

                        }

                        viewModel.userWorkShop.workListFinish.forEachIndexed { index, string ->

                            val parts = string.split(",")
                            val id = parts.getOrElse(0) { "" }
                            val date = parts.getOrElse(1) { "" }
                            val quantity = parts.getOrElse(3) { "" }
                            val price = parts.getOrElse(4) { "" }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 20.dp),
                                    text = "- $id",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 10.dp),
                                    text = date
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 30.dp),
                                    text = quantity
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 10.dp),
                                    text = "+$ $price",
                                    color = Green200,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

            }
        }
        DefaultButtonImage(
            modifier = Modifier
                .padding(top = 100.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth(),
            text = "Pagar",
            onClick = {
                      viewModel.userWorkShop.workListFinish.forEachIndexed { index, string ->
                          val parts = string.split(",")
                          val id = parts.getOrElse(0) { "" }
                          viewModel.getPackageById(id)
                      }
            },
            icon = painterResource(id = R.drawable.icon_paid)
        )


    }


}