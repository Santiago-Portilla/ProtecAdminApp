package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.model.Packing
import com.portosdeveloper.protecadminapp.domain.repository.PackingRepository
import javax.inject.Inject

class CreatePacking @Inject constructor(private val repository: PackingRepository) {

    suspend operator fun invoke(packing: Packing) = repository.createPacking(packing)

}