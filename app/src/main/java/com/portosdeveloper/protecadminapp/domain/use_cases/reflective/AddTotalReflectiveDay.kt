package com.portosdeveloper.protecadminapp.domain.use_cases.wadding


import com.portosdeveloper.protecadminapp.domain.model.Reflective
import com.portosdeveloper.protecadminapp.domain.repository.ReflectiveRepository
import javax.inject.Inject

class AddTotalReflectiveDay @Inject constructor(private val repository: ReflectiveRepository) {

    suspend operator fun invoke(totalReflective: String, reflective: Reflective) = repository.addTotalReflectiveDay(totalReflective,reflective)

}