package com.portosdeveloper.protecadminapp.domain.model

data class Button(
    var id: String = "",
    var lines: String = "",
    var color: String = "",
    var totalButton: String = "",
    var totalGrandButton: String = "",
    var inputTotalButtonArray : List<String> = listOf()
)
