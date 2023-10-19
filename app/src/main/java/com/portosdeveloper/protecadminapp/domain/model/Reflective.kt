package com.portosdeveloper.protecadminapp.domain.model

data class Reflective(
    var id: String = "",
    var totalReflective: String = "",
    var inputDateArray : List<String> = listOf(),
    var inputTotalReflectiveArray : List<String> = listOf(),
    var combined: String = "",
    var reinforced: String = "",
    var threeM: String = ""
)
