package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.RollWadding
import kotlinx.coroutines.flow.Flow

interface RollWaddingRepository {
    suspend fun createRollWadding(rollWadding: RollWadding): Response<Boolean>
    suspend fun updateRollWadding( rollWadding: RollWadding): Response<Boolean>
    suspend fun getTotalRollWadding(id: String): String
    suspend fun getTotalMetersRollWadding(id: String): String
    suspend fun addDateRollWadding(newDate : String, rollWadding : RollWadding): Response<Boolean>
    fun stockTotal(): Flow<Response<List<RollWadding>>>
}