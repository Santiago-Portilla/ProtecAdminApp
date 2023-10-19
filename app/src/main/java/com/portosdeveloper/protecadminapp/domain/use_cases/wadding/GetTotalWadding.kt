package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.model.Wadding
import com.portosdeveloper.protecadminapp.domain.repository.WaddingRepository
import javax.inject.Inject

class GetTotalWadding @Inject constructor(private val repository: WaddingRepository) {

    operator fun invoke(id: String) = repository.getTotalWadding(id)

}