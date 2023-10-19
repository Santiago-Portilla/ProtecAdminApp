package com.portosdeveloper.protecadminapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface UtilsRepository {
    fun getArray( util: String, nameArray: String ): Flow<List<String>>
}