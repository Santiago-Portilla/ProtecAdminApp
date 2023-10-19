package com.portosdeveloper.protecadminapp.domain.use_cases.utils

import com.portosdeveloper.protecadminapp.domain.repository.UtilsRepository
import javax.inject.Inject

class GetList @Inject constructor(private val repository: UtilsRepository) {
    operator fun invoke(util: String, nameArray: String) = repository.getArray(util,nameArray)
}