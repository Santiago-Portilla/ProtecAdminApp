package com.portosdeveloper.protecadminapp.presentation.components

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.BarcodeEncoder

@Composable
fun QRCodeGenerator(
    modifier: Modifier,
    text: String,
    size: Int
) {

    val barcodeEncoder : BarcodeEncoder = BarcodeEncoder()
    val bitmap : Bitmap = barcodeEncoder.encodeBitmap(
        text,
        BarcodeFormat.QR_CODE,
        size,
        size
    )

    Image(
        modifier = modifier,
        bitmap = bitmap.asImageBitmap(),
        contentDescription = " QR Code"
    )
}
