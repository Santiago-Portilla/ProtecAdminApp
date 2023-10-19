package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.use_cases.reflective.StockReflective

data class ReflectiveUseCases(
    var createReflective: CreateReflective,
    var updateReflective: UpdateReflective,
    var getTotalReflective: GetTotalReflective,
    var addDateReflective: AddDateReflective,
    var addTotalReflectiveDay: AddTotalReflectiveDay,
    var stockReflective: StockReflective
)