package com.portosdeveloper.protecadminapp.domain.use_cases.wadding


import com.portosdeveloper.protecadminapp.domain.model.Button
import com.portosdeveloper.protecadminapp.domain.repository.ButtonRepository
import javax.inject.Inject

class AddTotalButtonDay @Inject constructor(private val repository: ButtonRepository) {

    suspend operator fun invoke(totalThread: String, button: Button) = repository.addTotalButtonDay(totalThread,button)

}