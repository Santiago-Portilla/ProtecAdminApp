package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.use_cases.thread.StockThread

data class ThreadUseCases(
    var createThread: CreateThread,
    var updateThread: UpdateThread,
    var getTotalThread: GetTotalThread,
    var addTotalThreadDay: AddTotalThreadDay,
    var stockThread: StockThread
)