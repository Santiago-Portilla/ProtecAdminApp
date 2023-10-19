package com.portosdeveloper.protecadminapp.domain.use_cases.shirt

import com.portosdeveloper.protecadminapp.domain.repository.ShirtRepository
import javax.inject.Inject

class GetShirtById @Inject constructor(private val repository: ShirtRepository) {

    operator fun invoke(id : String) = repository.getShirtById(id)

}