package com.portosdeveloper.protecadminapp.domain.model

data class Yarn(
    var id: String = "",
    var totalYarn: String = "",
    var totalMetersYarn: String = "",
    var inputTotalYarnArray : List<String> = listOf()
)
