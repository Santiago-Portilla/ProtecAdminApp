package com.portosdeveloper.protecadminapp.domain.use_cases.wadding


import com.portosdeveloper.protecadminapp.domain.model.Yarn
import com.portosdeveloper.protecadminapp.domain.repository.YarnRepository
import javax.inject.Inject

class UpdateYarn @Inject constructor(private val repository: YarnRepository) {

    suspend operator fun invoke(yarn: Yarn) = repository.updateYarn(yarn)

}