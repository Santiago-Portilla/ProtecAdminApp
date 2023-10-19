package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Reflective
import com.portosdeveloper.protecadminapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface ReflectiveRepository {

    suspend fun createReflective(reflective: Reflective): Response<Boolean>
    suspend fun updateReflective( reflective: Reflective): Response<Boolean>
    suspend fun getTotalReflective(id: String): String
    suspend fun addDateReflective(newDate : String, reflective : Reflective): Response<Boolean>
    suspend fun addTotalReflectiveDay(totalReflective : String, reflective : Reflective): Response<Boolean>
    fun stockTotal(): Flow<Response<List<Reflective>>>

}