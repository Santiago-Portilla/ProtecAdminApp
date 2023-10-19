package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

data class WaddingUseCases(
    var createWadding: CreateWadding,
    var updateWadding: UpdateWadding,
    var getTotalWadding: GetTotalWadding,
    var addDateWadding: AddDateWadding,
    var stockWadding: StockWadding
    )