package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Packing
import com.portosdeveloper.protecadminapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface PackingRepository {
    suspend fun createPacking(packing: Packing): Response<Boolean>
    suspend fun updatePacking( packing: Packing): Response<Boolean>
    suspend fun getTotalPacking(id: String): String
    suspend fun addDatePacking(newDate : String, packing : Packing): Response<Boolean>
    suspend fun addTotalPackingDay(totalPacking : String, packing : Packing): Response<Boolean>
    fun stockTotal(): Flow<Response<List<Packing>>>

}