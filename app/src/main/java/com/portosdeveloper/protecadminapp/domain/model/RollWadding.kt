package com.portosdeveloper.protecadminapp.domain.model

data class RollWadding (
    var id: String = "",
    var totalRollWadding: String = "0",
    var totalMetersRollWadding: String = "",
    var inputOutputDateArray : List<String> = listOf(),
)