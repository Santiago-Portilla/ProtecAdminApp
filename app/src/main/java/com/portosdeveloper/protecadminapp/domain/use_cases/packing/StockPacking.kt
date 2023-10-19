package com.portosdeveloper.protecadminapp.domain.use_cases.packing

import com.portosdeveloper.protecadminapp.domain.repository.PackingRepository
import javax.inject.Inject

class StockPacking @Inject constructor(private val repository: PackingRepository) {

    operator fun invoke() =  repository.stockTotal()

}