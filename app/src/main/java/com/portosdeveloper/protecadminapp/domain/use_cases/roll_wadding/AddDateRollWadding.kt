package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.model.RollWadding
import com.portosdeveloper.protecadminapp.domain.repository.RollWaddingRepository
import javax.inject.Inject

class AddDateRollWadding @Inject constructor(private val repository: RollWaddingRepository) {

    suspend operator fun invoke(newDate: String, rollWadding: RollWadding) = repository.addDateRollWadding(newDate,rollWadding)

}