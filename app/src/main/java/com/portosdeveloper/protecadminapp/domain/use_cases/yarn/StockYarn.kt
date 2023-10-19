package com.portosdeveloper.protecadminapp.domain.use_cases.yarn

import com.portosdeveloper.protecadminapp.domain.repository.YarnRepository
import javax.inject.Inject

class StockYarn @Inject constructor(private val repository: YarnRepository) {

    operator fun invoke() =  repository.stockTotal()

}