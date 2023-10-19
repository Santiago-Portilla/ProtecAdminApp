package com.portosdeveloper.protecadminapp.domain.use_cases.wadding


import com.portosdeveloper.protecadminapp.domain.model.Button
import com.portosdeveloper.protecadminapp.domain.repository.ButtonRepository
import javax.inject.Inject

class UpdateButton @Inject constructor(private val repository: ButtonRepository) {

    suspend operator fun invoke(button: Button) = repository.updateButton(button)

}