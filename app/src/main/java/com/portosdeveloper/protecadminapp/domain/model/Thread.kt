package com.portosdeveloper.protecadminapp.domain.model

data class Thread(
    var id: String = "",
    var totalThread: String = "",
    var totalThreadInStorage: String = "",
    var totalThreadInWorkShop: String = "",
    var totalMetersThread:String = "",
    var totalMetersThreadInStorage:String = "",
    var totalMetersThreadInWorkShop:String = "",
    var inputOutputTotalThreadArray : List<String> = listOf(),
)
