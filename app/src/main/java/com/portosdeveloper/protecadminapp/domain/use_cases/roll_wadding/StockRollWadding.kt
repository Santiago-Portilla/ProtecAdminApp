package com.portosdeveloper.protecadminapp.domain.use_cases.roll_wadding

import com.portosdeveloper.protecadminapp.domain.repository.RollWaddingRepository
import javax.inject.Inject

class StockRollWadding @Inject constructor(private val repository: RollWaddingRepository) {

    operator fun invoke() =  repository.stockTotal()

}