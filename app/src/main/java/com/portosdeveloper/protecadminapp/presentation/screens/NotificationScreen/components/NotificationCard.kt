package com.portosdeveloper.protecadminapp.presentation.screens.NotificationScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray700

@Composable
fun NotificationCard(
    image: Painter,
    name: String,
    msn: String
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 15.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Gray700
    ) {
        Row() {
            Image(
                modifier = Modifier
                    .padding(6.dp)
                    .size(55.dp)
                    .clip(CircleShape),
                painter = image ,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White
                )
                Text(
                    text = msn,
                    color = Color.White
                )
            }

        }

    }
}
