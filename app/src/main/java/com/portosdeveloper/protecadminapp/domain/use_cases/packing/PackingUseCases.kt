package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.use_cases.packing.StockPacking

data class PackingUseCases(
    var createPacking: CreatePacking,
    var updatePacking: UpdatePacking,
    var getTotalPacking: GetTotalPacking,
    var addDatePacking: AddDatePacking,
    var addTotalPackingDay: AddTotalPackingDay,
    var stockPacking: StockPacking
)