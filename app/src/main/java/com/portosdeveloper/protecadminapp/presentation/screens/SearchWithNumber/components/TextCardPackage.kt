package com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TextCardPackage(
    title: String,
    personal: String
){
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
            text = title,
            fontWeight = FontWeight.Bold
        )
        if(personal == ""){
            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                text = "Aun no ha empezado el proceso"
            )
        }else{
            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                text = personal
            )
        }
    }
}