package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Yarn
import kotlinx.coroutines.flow.Flow

interface YarnRepository {

    suspend fun createYarn(yarn: Yarn): Response<Boolean>
    suspend fun updateYarn(yarn: Yarn): Response<Boolean>
    suspend fun getTotalYarn(id: String, idCamp: String): String
    suspend fun addTotalYarnDay(totalYarn : String, yarn : Yarn): Response<Boolean>
    fun stockTotal(): Flow<Response<List<Yarn>>>

}