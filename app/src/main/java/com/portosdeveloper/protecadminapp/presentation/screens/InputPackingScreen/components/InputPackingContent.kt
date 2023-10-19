package com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.components.DefaultButton
import com.portosdeveloper.protecadminapp.presentation.components.DefaultTextFieldImage
import com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen.InputPackingViewModel

@Composable
fun InputPackingContent(viewModel: InputPackingViewModel = hiltViewModel()){

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .size(30.dp))
        DefaultTextFieldImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp),
            value = state.paperBoard,
            onValueChange = { paperBoard -> viewModel.onPaperBoardInput(paperBoard) },
            validateField = { viewModel.validatePaperBoard() },
            label = " CARTON ",
            icon = painterResource(id = R.drawable.icon_paper_board),
            keyboartype = KeyboardType.Number
        )
        DefaultTextFieldImage(
            modifier = Modifier.fillMaxWidth(),
            value = state.bag,
            onValueChange = { threeCm -> viewModel.onBagInput(threeCm) },
            validateField = { viewModel.validateBag() },
            label = " BOLSA ",
            icon = painterResource(id = R.drawable.icon_bag),
            keyboartype = KeyboardType.Number
        )
        DefaultTextFieldImage(
            modifier = Modifier.fillMaxWidth(),
            value = state.bigPin,
            onValueChange = { bigPin -> viewModel.onBigPinInput(bigPin) },
            validateField = { viewModel.validateBigPin() },
            label = " ALFILER GRANDE ",
            icon = painterResource(id = R.drawable.icon_pin),
            keyboartype = KeyboardType.Number
        )
        DefaultTextFieldImage(
            modifier = Modifier.fillMaxWidth(),
            value = state.smallPin,
            onValueChange = { smallPin -> viewModel.onSmallPinInput(smallPin) },
            validateField = { viewModel.validateSmallPin() },
            label = " ALFILER PEQUENIO ",
            icon = painterResource(id = R.drawable.icon_pin),
            keyboartype = KeyboardType.Number
        )

        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 5.dp, end = 5.dp),
            text = "CARGAR",
            onClick = {
                      viewModel.onInputToBBDD()
            },
            enabled = viewModel.isEnabledInputPackingButton
        )
    }




}