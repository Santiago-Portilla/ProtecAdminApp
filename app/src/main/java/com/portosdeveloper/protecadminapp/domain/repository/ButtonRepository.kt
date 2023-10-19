package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Button
import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Yarn
import kotlinx.coroutines.flow.Flow

interface ButtonRepository {

    suspend fun createButton(button: Button): Response<Boolean>
    suspend fun updateButton( button: Button): Response<Boolean>
    suspend fun getTotalButton(id: String, idCamp: String): String
    suspend fun addTotalButtonDay(totalYarn : String, button : Button): Response<Boolean>
    fun stockTotal(): Flow<Response<List<Button>>>

}