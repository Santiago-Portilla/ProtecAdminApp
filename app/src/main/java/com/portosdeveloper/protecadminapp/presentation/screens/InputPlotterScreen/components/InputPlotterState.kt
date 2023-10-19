package com.portosdeveloper.protecadminapp.presentation.screens.InputPlotterScreen.components

data class InputPlotterState(
    var width : String = "",
    var height : String = "",
    var idPlotter: String = "0" ,
    var newIdPlotter: String = "",
    var sizes: String = "",
    var inputDate: String = "",
    var sizesList: List<String> = listOf(),
    var genderList: List<String> = listOf(),
)
