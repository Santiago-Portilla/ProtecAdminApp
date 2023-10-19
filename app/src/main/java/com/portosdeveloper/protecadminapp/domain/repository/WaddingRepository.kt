package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Wadding
import kotlinx.coroutines.flow.Flow

interface WaddingRepository {

    suspend fun createWadding(wadding: Wadding): Response<Boolean>
    suspend fun updateWadding( wadding: Wadding): Response<Boolean>
    fun getTotalWadding(id: String): Flow<String>
    suspend fun addDateWadding(newDate : String, wadding : Wadding): Response<Boolean>
    fun stockTotal(): Flow<Response<List<Wadding>>>

}