package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.model.Wadding
import com.portosdeveloper.protecadminapp.domain.repository.WaddingRepository
import javax.inject.Inject

class UpdateWadding @Inject constructor(private val repository: WaddingRepository) {

    suspend operator fun invoke(wadding: Wadding) = repository.updateWadding(wadding)

}