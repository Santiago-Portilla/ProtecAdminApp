package com.portosdeveloper.protecadminapp.domain.use_cases.wadding


import com.portosdeveloper.protecadminapp.domain.model.Reflective
import com.portosdeveloper.protecadminapp.domain.repository.ReflectiveRepository
import javax.inject.Inject

class UpdateReflective @Inject constructor(private val repository: ReflectiveRepository) {

    suspend operator fun invoke(reflective: Reflective) = repository.updateReflective(reflective)

}