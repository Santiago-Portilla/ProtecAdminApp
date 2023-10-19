package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Package
import com.portosdeveloper.protecadminapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface PackageRepository {

    fun stockPackage(): Flow<Response<List<Package>>>
    suspend fun create(packageShirt: Package): Response<Boolean>
    fun getIdPackage(): Flow<String>
    suspend fun updatePaidJob(packageShirt: Package, job: String, paid: String): Response<Boolean>
    suspend fun newGetPackageById(id: String) : Flow<Response<Package>>
}