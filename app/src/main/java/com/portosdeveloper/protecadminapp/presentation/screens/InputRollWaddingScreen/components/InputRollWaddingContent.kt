package com.portosdeveloper.protecadminapp.presentation.screens.InputRollWaddingScreen.components

import android.util.Log
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
import com.portosdeveloper.protecadminapp.presentation.screens.InputRollWaddingScreen.InputRollWaddingViewModel

@Composable
fun InputRollWaddingContent(viewModel: InputRollWaddingViewModel = hiltViewModel()){

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
            value = state.twoCm,
            onValueChange = { twoCm -> viewModel.onTwoCmInput(twoCm) },
            validateField = { viewModel.validateTwoCm() },
            label = " 2 CM ",
            icon = painterResource(id = R.drawable.icon_rolls_wadding),
            keyboartype = KeyboardType.Number
        )
        DefaultTextFieldImage(
            modifier = Modifier.fillMaxWidth(),
            value = state.threeCm,
            onValueChange = { threeCm -> viewModel.onThreeCmInput(threeCm) },
            validateField = { viewModel.validateThreeCm() },
            label = " 3 CM ",
            icon = painterResource(id = R.drawable.icon_rolls_wadding),
            keyboartype = KeyboardType.Number
        )
        DefaultTextFieldImage(
            modifier = Modifier.fillMaxWidth(),
            value = state.threeHalfCm,
            onValueChange = { threeHalfCm -> viewModel.onThreeHalfCmInput(threeHalfCm) },
            validateField = { viewModel.validateThreeHalfCm() },
            label = " 3.5 CM ",
            icon = painterResource(id = R.drawable.icon_rolls_wadding),
            keyboartype = KeyboardType.Number
        )
        DefaultTextFieldImage(
            modifier = Modifier.fillMaxWidth(),
            value = state.oneHundredFiftyCm,
            onValueChange = { oneHundredFiftyCm -> viewModel.onOneHundredFiftyCmInput(oneHundredFiftyCm) },
            validateField = { viewModel.validateOneHundredFiftyCm() },
            label = " 150 CM ",
            icon = painterResource(id = R.drawable.icon_roll_wadding),
            keyboartype = KeyboardType.Number
        )

        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 5.dp, end = 5.dp),
            text = "CARGAR",
            onClick = {
                viewModel.updateRollWadding150Output()
                viewModel.onInputToBBDD()
            },
            enabled = viewModel.isEnabledInputRollWaddingButton
        )
    }




}