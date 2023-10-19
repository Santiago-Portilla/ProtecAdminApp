package com.portosdeveloper.protecadminapp.domain.use_cases.button

import com.portosdeveloper.protecadminapp.domain.repository.ButtonRepository
import javax.inject.Inject

class StockButton @Inject constructor(private val repository: ButtonRepository) {

    operator fun invoke() =  repository.stockTotal()

}