package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.repository.RollWaddingRepository
import javax.inject.Inject

class GetTotalRollWadding @Inject constructor(private val repository: RollWaddingRepository) {

    suspend operator fun invoke(id: String) = repository.getTotalRollWadding(id)

}