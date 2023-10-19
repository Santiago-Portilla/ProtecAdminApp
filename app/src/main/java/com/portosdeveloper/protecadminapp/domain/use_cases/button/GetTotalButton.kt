package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.repository.ButtonRepository
import javax.inject.Inject

class GetTotalButton @Inject constructor(private val repository: ButtonRepository) {

    suspend operator fun invoke(id: String, idCamp: String) = repository.getTotalButton(id,idCamp)

}