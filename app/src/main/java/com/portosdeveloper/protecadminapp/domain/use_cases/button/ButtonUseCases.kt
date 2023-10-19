package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.use_cases.button.StockButton

data class ButtonUseCases(
    var createButton: CreateButton,
    var updateButton: UpdateButton,
    var getTotalButton: GetTotalButton,
    var addTotalButtonDay: AddTotalButtonDay,
    var stockButton: StockButton
)