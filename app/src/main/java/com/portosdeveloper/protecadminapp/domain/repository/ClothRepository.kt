package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Cloth
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.TotalCloth
import kotlinx.coroutines.flow.Flow

interface ClothRepository {

    suspend fun createCloth( cloth : Cloth) : Response<Boolean>
    suspend fun createTotalCloth(totalCloth : TotalCloth) : Response<Boolean>
    fun getTotalMeasureById(id: String): Flow<TotalCloth>
    fun stockTotal(): Flow<Response<List<TotalCloth>>>
    suspend fun updateTotalCloth(totalCloth : TotalCloth) : Response<Boolean>
    suspend fun updateCloth(cloth : Cloth) : Response<Boolean>
    fun getClothList(cloth:String, color: String): Flow<Response<List<Cloth>>>
    suspend fun addDateCloth(newDate : String, totalCloth : TotalCloth): Response<Boolean>


}