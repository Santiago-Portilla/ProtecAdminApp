package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.repository.ThreadRepository
import com.portosdeveloper.protecadminapp.domain.model.Thread
import javax.inject.Inject

class AddTotalThreadDay @Inject constructor(private val repository: ThreadRepository) {

    suspend operator fun invoke(totalThread: String, thread: Thread) = repository.addTotalThreadDay(totalThread,thread)

}