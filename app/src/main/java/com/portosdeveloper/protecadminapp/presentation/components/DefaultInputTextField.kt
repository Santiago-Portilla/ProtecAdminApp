package com.portosdeveloper.protecadminapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun DefaultInputTextField(
    modifier: Modifier,
    state: String,
    onValueChange: (value : String) -> Unit,
    validateField: () -> Unit = {},
    validateList: () -> Unit = {},
    validateTotal : () -> Unit = {},
    totalInBBDD : () -> Unit = {},
    keyboartype: KeyboardType = KeyboardType.Text,
    label: String,
    errorMsg: String = "",
) {
        Column() {
            DefaultTextField(
                modifier = modifier,
                value = state,
                onValueChange = {
                    onValueChange(it)
                },
                validateField = {
                    validateField()
                },
                validateList = {
                    validateList()
                },
                validateTotal = {
                    validateTotal()
                },
                totalInBBDD = {
                    totalInBBDD()
                },
                label = label,
                errorMsg = errorMsg,
                keyboartype = keyboartype
                )
        }
}
