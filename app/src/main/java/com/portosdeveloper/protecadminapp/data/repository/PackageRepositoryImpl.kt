package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.portosdeveloper.protecadminapp.core.Constants.PACKAGE
import com.portosdeveloper.protecadminapp.domain.model.*
import com.portosdeveloper.protecadminapp.domain.repository.PackageRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class PackageRepositoryImpl @Inject constructor(
    @Named(PACKAGE) private val packageRef: CollectionReference
    ):PackageRepository {
    override suspend fun create(packageShirt: Package): Response<Boolean> {
        return try {
            packageRef.document(packageShirt.id).set(packageShirt).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getIdPackage(): Flow<String> = callbackFlow {
        val sizePlotter = packageRef.get().addOnSuccessListener{
            val plotterId = it.size().toString()
            trySend(plotterId)
        }
        awaitClose{
            sizePlotter.isCanceled
        }
    }

    override fun stockPackage(): Flow<Response<List<Package>>> = callbackFlow{
        val snapshotListener = packageRef.addSnapshotListener{
                snapshot,e ->

            val packageResponse = if(snapshot != null){
                val packageShirt = snapshot.toObjects(Package :: class.java)
                Response.Success(packageShirt)
            }else{
                Response.Failure(e!!)
            }
            trySend(packageResponse)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override suspend fun updatePaidJob(packageShirt: Package, job: String, paid: String): Response<Boolean> {
        return try {
            val map : MutableMap<String, Any> = hashMapOf()
            when(job){
                "Corte" ->{
                    map["cutJobPaid"] = paid
                }
                "Fusionado" ->{
                    map["mergedJobPaid"] = paid
                }
                "CorteCuellosPuños" ->{
                    map["cutNecksFistsJobPaid"] = paid
                }
                "TerminacionCuellosPuños" ->{
                    map["necksFistsJobPaid"] = paid
                }
                "Cuerpos" ->{
                    map["bodiesJobPaid"] = paid
                }
                "Cerradora"->{
                    map["closedJobPaid"] = paid
                }
                "Terminacion"->{
                    map["terminationJobPaid"] = paid
                }
                "OjalBoton"->{
                    map["buttonHoleButtonJobPaid"] = paid
                }
                "Remate"->{
                    map["polishJobPaid"] = paid
                }
                "Empaque"->{
                    map["packingJobPaid"] = paid
                }
            }
            packageRef.document(packageShirt.id).update(map).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun newGetPackageById(id: String): Flow<Response<Package>> = callbackFlow {
        val snapshotListener = packageRef.document(id)
            .addSnapshotListener{
                    snapshot,e ->
                val packageShirt = if(snapshot != null){
                    val userWorkShop = snapshot.toObject(Package :: class.java) ?: Package()
                    Response.Success(userWorkShop)
                }else{
                    Response.Failure(e!!)
                }
                trySend(packageShirt)
            }
        awaitClose{
            snapshotListener.remove()
        }
    }
}