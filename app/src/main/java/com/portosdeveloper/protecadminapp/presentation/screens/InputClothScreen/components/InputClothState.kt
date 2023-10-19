package com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.components

data class InputClothState(
    val cloth: String = "",
    val color: String = "",
    val bill: String = "",
    val inputDate: String = "",
    val totalMeasure: String = "",
    val totalMeasureInBBDD: String = "",
    var clothList: List<String> = listOf(),
    var colorList: List<String> = listOf(),
)
