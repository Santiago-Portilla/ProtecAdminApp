package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.repository.PackingRepository
import javax.inject.Inject

class GetTotalPacking @Inject constructor(private val repository: PackingRepository) {

    suspend operator fun invoke(id: String) = repository.getTotalPacking(id)

}