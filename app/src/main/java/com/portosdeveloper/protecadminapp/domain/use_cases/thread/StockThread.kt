package com.portosdeveloper.protecadminapp.domain.use_cases.thread

import com.portosdeveloper.protecadminapp.domain.repository.ThreadRepository
import javax.inject.Inject

class StockThread @Inject constructor(private val repository: ThreadRepository) {

    operator fun invoke() =  repository.stockTotal()

}