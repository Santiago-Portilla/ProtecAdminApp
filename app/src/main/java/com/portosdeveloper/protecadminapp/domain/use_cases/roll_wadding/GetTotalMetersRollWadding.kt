package com.portosdeveloper.protecadminapp.domain.use_cases.roll_wadding

import com.portosdeveloper.protecadminapp.domain.repository.RollWaddingRepository
import javax.inject.Inject

class GetTotalMetersRollWadding @Inject constructor(private val repository: RollWaddingRepository) {
    suspend operator fun invoke(id: String) = repository.getTotalMetersRollWadding(id)
}