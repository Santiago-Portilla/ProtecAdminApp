package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.model.Wadding
import com.portosdeveloper.protecadminapp.domain.repository.WaddingRepository
import javax.inject.Inject

class AddDateWadding @Inject constructor(private val repository: WaddingRepository) {

    suspend operator fun invoke(newDate: String, wadding: Wadding) = repository.addDateWadding(newDate,wadding)

}