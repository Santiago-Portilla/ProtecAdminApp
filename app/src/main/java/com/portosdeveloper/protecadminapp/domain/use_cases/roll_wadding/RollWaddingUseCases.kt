package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.use_cases.roll_wadding.GetTotalMetersRollWadding
import com.portosdeveloper.protecadminapp.domain.use_cases.roll_wadding.StockRollWadding

data class RollWaddingUseCases(
    var createRollWadding: CreateRollWadding,
    var updateRollWadding: UpdateRollWadding,
    var getTotalRollWadding: GetTotalRollWadding,
    var addDateRollWadding: AddDateRollWadding,
    var stockRollWadding: StockRollWadding,
    var getTotalMetersRollWadding: GetTotalMetersRollWadding
)