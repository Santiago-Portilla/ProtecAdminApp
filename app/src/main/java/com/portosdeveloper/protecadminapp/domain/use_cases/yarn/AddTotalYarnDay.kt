package com.portosdeveloper.protecadminapp.domain.use_cases.wadding


import com.portosdeveloper.protecadminapp.domain.model.Yarn
import com.portosdeveloper.protecadminapp.domain.repository.YarnRepository
import javax.inject.Inject

class AddTotalYarnDay @Inject constructor(private val repository: YarnRepository) {

    suspend operator fun invoke(totalThread: String, yarn: Yarn) = repository.addTotalYarnDay(totalThread,yarn)

}