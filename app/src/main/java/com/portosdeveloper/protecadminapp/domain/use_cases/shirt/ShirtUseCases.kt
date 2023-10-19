package com.portosdeveloper.protecadminapp.domain.use_cases.shirt

data class ShirtUseCases(
    var createShirt: CreateShirt,
    var updateShirt: UpdateShirt,
    var getShirtById: GetShirtById
)