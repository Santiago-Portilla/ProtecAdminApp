package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.repository.ReflectiveRepository
import javax.inject.Inject

class GetTotalReflective @Inject constructor(private val repository: ReflectiveRepository) {

    suspend operator fun invoke(id: String) = repository.getTotalReflective(id)

}