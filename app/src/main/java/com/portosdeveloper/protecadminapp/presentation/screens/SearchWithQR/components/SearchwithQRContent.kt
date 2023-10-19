package com.portosdeveloper.protecadminapp.presentation.screens.SearchWithQR.components

import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.components.DefaultButtonImage
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTextField
import com.portosdeveloper.protecadminapp.presentation.screens.SearchWithQR.SearchWithQRViewModel

@Composable
fun SearchWithQRContent(viewModel: SearchWithQRViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state = viewModel.state

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data: Intent? = result.data
        val scanResult: IntentResult? =
            IntentIntegrator.parseActivityResult(result.resultCode, data)
        if (scanResult != null && scanResult.contents != null) {
            viewModel.onInputId(scanResult.contents)
        } else {
            Toast.makeText(context, "Escaneo cancelado o inválido", Toast.LENGTH_SHORT).show()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth(0.85f),
                value = state.id,
                onValueChange = { viewModel.onInputId(it) },
                label = "Ingrese Id del paquete"
            )
            DefaultButtonImage(
                modifier = Modifier.padding(top = 10.dp, start = 5.dp),
                text = "",
                onClick = {
                    viewModel.onInputId("")
                    val integrator = IntentIntegrator(context as ComponentActivity)
                    integrator.setOrientationLocked(false)
                    integrator.setPrompt("Scan a QR Code")
                    launcher.launch(integrator.createScanIntent())
                },
                icon = painterResource(id = R.drawable.icon_search)
            )
        }
        DefaultButtonImage(
            modifier = Modifier.padding(top = 10.dp, start = 5.dp),
            text = "BUSCAR",
            onClick = {
                viewModel.getPackageById(state.id)
            },
            icon = painterResource(id = R.drawable.icon_search)
        )
        GetPackageByIdQR()
    }
}




