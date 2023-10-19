package com.portosdeveloper.protecadminapp.domain.use_cases.reflective

import com.portosdeveloper.protecadminapp.domain.repository.ReflectiveRepository
import javax.inject.Inject

class StockReflective @Inject constructor(private val repository: ReflectiveRepository) {

    operator fun invoke() =  repository.stockTotal()

}