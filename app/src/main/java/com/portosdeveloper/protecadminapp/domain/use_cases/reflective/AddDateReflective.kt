package com.portosdeveloper.protecadminapp.domain.use_cases.wadding


import com.portosdeveloper.protecadminapp.domain.model.Reflective
import com.portosdeveloper.protecadminapp.domain.repository.ReflectiveRepository
import javax.inject.Inject

class AddDateReflective @Inject constructor(private val repository: ReflectiveRepository) {

    suspend operator fun invoke(newDate: String, reflective: Reflective) = repository.addDateReflective(newDate,reflective)

}