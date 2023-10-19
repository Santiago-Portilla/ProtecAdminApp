package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.repository.WaddingRepository
import javax.inject.Inject

class StockWadding @Inject constructor(private val repository: WaddingRepository) {

    operator fun invoke() =  repository.stockTotal()

}