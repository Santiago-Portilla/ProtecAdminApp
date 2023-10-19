package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop
import kotlinx.coroutines.flow.Flow

interface UserWorkShopRepository {

    fun getCutUserWorShop(): Flow<Response<List<UserWorkShop>>>
    fun getUserWorShopList(): Flow<Response<List<UserWorkShop>>>
    suspend fun updateWorkFinishList(userWorkShop: UserWorkShop, newList: List<String>): Response<Boolean>
    suspend fun updateWorkPaidList(userWorkShop: UserWorkShop, newItem: String): Response<Boolean>
    suspend fun updateWorkProgressList(userWorkShop: UserWorkShop, newItem: String): Response<Boolean>
    suspend fun updateSalaryWorkWeek(userWorkShop: UserWorkShop): Response<Boolean>
    suspend fun updateSalaryWorkMonth(userWorkShop: UserWorkShop, newDate: String): Response<Boolean>

}