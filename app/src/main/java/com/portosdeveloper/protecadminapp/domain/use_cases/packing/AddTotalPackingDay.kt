package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.model.Packing
import com.portosdeveloper.protecadminapp.domain.repository.PackingRepository
import javax.inject.Inject

class AddTotalPackingDay @Inject constructor(private val repository: PackingRepository) {

    suspend operator fun invoke(totalPacking: String, packing: Packing) = repository.addTotalPackingDay(totalPacking,packing)

}