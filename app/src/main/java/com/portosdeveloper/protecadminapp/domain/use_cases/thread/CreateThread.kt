package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.repository.ThreadRepository
import com.portosdeveloper.protecadminapp.domain.model.Thread
import javax.inject.Inject

class CreateThread @Inject constructor(private val repository: ThreadRepository) {

    suspend operator fun invoke(thread: Thread) = repository.createThread(thread)

}