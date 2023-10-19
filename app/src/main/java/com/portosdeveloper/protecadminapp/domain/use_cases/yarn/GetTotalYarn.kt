package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.repository.YarnRepository
import javax.inject.Inject

class GetTotalYarn @Inject constructor(private val repository: YarnRepository) {

    suspend operator fun invoke(id: String, idCamp: String) = repository.getTotalYarn(id,idCamp)

}