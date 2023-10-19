package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.use_cases.yarn.StockYarn

data class YarnUseCases(
    var createYarn: CreateYarn,
    var updateYarn: UpdateYarn,
    var getTotalYarn: GetTotalYarn,
    var addTotalYarnDay: AddTotalYarnDay,
    var stockYarn: StockYarn
)