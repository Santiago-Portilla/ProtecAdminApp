package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Shirt
import kotlinx.coroutines.flow.Flow

interface ShirtRepository {

    suspend fun createShirt(shirt: Shirt): Response<Boolean>
    fun getShirtById(id: String): Flow<Response<Shirt>>
    suspend fun updateShirt(shirt: Shirt): Response<Boolean>

}