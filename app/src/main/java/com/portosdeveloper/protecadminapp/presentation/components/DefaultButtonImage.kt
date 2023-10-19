package com.portosdeveloper.protecadminapp.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Blue500
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Blue700
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Red500

@Composable
fun DefaultButtonImage(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color = Blue700,
    icon: Painter,
    enabled: Boolean = true
){
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        enabled = enabled
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = icon,
            contentDescription ="" )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text)
    }
}