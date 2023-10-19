package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Thread
import kotlinx.coroutines.flow.Flow

interface ThreadRepository {

    suspend fun createThread(thread: Thread): Response<Boolean>
    suspend fun updateThread( thread: Thread): Response<Boolean>
    suspend fun getTotalThread(id: String, idCamp: String): String
    suspend fun addTotalThreadDay(totalThread : String, thread : Thread): Response<Boolean>
    fun stockTotal(): Flow<Response<List<Thread>>>

}