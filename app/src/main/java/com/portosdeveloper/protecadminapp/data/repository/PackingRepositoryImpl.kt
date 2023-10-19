package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.portosdeveloper.protecadminapp.core.Constants.PACKING
import com.portosdeveloper.protecadminapp.core.Constants.ROLLWADDING
import com.portosdeveloper.protecadminapp.core.Constants.WADDING
import com.portosdeveloper.protecadminapp.domain.model.*
import com.portosdeveloper.protecadminapp.domain.repository.PackingRepository
import com.portosdeveloper.protecadminapp.domain.repository.RollWaddingRepository
import com.portosdeveloper.protecadminapp.domain.repository.WaddingRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class PackingRepositoryImpl @Inject constructor(
    @Named(PACKING) private val packingRef : CollectionReference
): PackingRepository {

    override suspend fun createPacking(packing: Packing): Response<Boolean> {
        return try {
            packingRef.document(packing.id).set(packing).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override suspend fun updatePacking(packing: Packing): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = hashMapOf()
            map["totalPacking"] = packing.totalPacking
            packingRef.document(packing.id).update(map)
            Response.Success(true)
        }
        catch (e : Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun getTotalPacking(id: String): String{
        val getTotalPacking = packingRef.document(id).get().await()
        return if(getTotalPacking.exists()) {
            getTotalPacking.getString("totalPacking")!!
        }else{
            "0"
        }
    }

    override suspend fun addDatePacking(newDate: String, packing : Packing): Response<Boolean> {
        return try {
            packingRef.document(packing.id)
                .update(FieldPath.of("inputDateArray"), FieldValue.arrayUnion(newDate)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun addTotalPackingDay(totalPacking: String, packing: Packing): Response<Boolean> {
        return try {
            packingRef.document(packing.id)
                .update(FieldPath.of("inputTotalPackingArray"), FieldValue.arrayUnion(totalPacking)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun stockTotal(): Flow<Response<List<Packing>>> = callbackFlow {
        val snapshotListener = packingRef.addSnapshotListener{
                snapshot,e ->

            val packingResponse = if(snapshot != null){
                val packins = snapshot.toObjects(Packing :: class.java)
                Response.Success(packins)
            }else{
                Response.Failure(e!!)
            }
            trySend(packingResponse)

        }
        awaitClose{
            snapshotListener.remove()
        }
    }
}