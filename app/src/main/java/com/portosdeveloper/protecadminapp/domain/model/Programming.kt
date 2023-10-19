package com.portosdeveloper.protecadminapp.domain.model

import com.portosdeveloper.protecadminapp.domain.model.Package

data class Programming(
    var id: String = "",
    var date: String = "",
    var plotter: String = "",
    var programmingList: List<String> = listOf()
)
