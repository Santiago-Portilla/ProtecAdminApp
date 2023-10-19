package com.portosdeveloper.protecadminapp.domain.use_cases.shirt

import com.portosdeveloper.protecadminapp.domain.model.Shirt
import com.portosdeveloper.protecadminapp.domain.repository.ShirtRepository
import javax.inject.Inject

class CreateShirt @Inject constructor(private val repository: ShirtRepository) {

    suspend operator fun invoke(shirt: Shirt) = repository.createShirt(shirt)
}