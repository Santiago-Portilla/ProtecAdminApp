package com.portosdeveloper.protecadminapp.domain.model

data class Packing (
    var id: String = "",
    var totalPacking: String = "0",
    var inputDateArray : List<String> = listOf(),
    var inputTotalPackingArray : List<String> = listOf()
)