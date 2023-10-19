package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.portosdeveloper.protecadminapp.core.Constants.ROLLWADDING
import com.portosdeveloper.protecadminapp.core.Constants.WADDING
import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.RollWadding
import com.portosdeveloper.protecadminapp.domain.model.Wadding
import com.portosdeveloper.protecadminapp.domain.repository.RollWaddingRepository
import com.portosdeveloper.protecadminapp.domain.repository.WaddingRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class RollWaddingRepositoryImpl @Inject constructor(
    @Named(ROLLWADDING) private val rollWaddingRef : CollectionReference
): RollWaddingRepository {

    override suspend fun createRollWadding(rollWadding: RollWadding): Response<Boolean> {
        return try {
            rollWaddingRef.document(rollWadding.id).set(rollWadding).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override suspend fun updateRollWadding(rollWadding: RollWadding): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = hashMapOf()
            map["totalRollWadding"] = rollWadding.totalRollWadding
            map["totalMetersRollWadding"] = rollWadding.totalMetersRollWadding
            rollWaddingRef.document(rollWadding.id).update(map)
            Response.Success(true)
        }
        catch (e : Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun getTotalRollWadding(id: String): String{
        val getTotalRollWadding = rollWaddingRef.document(id).get().await()
        return if(getTotalRollWadding.exists()) {
            getTotalRollWadding.getString("totalRollWadding")!!
        }else{
            "0"
        }
    }

    override suspend fun getTotalMetersRollWadding(id: String): String{
        val getTotalRollWadding = rollWaddingRef.document(id).get().await()
        return if(getTotalRollWadding.exists()) {
            getTotalRollWadding.getString("totalMetersRollWadding")!!
        }else{
            "0"
        }
    }

    override suspend fun addDateRollWadding(newDate: String, rollWadding : RollWadding): Response<Boolean> {
        return try {
            rollWaddingRef.document(rollWadding.id)
                .update(FieldPath.of("inputOutputDateArray"), FieldValue.arrayUnion(newDate)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun stockTotal(): Flow<Response<List<RollWadding>>> = callbackFlow{
        val snapshotListener = rollWaddingRef.addSnapshotListener{
                snapshot,e ->

            val rollWaddingResponse = if(snapshot != null){
                val rollWaddings = snapshot.toObjects(RollWadding :: class.java)
                Response.Success(rollWaddings)
            }else{
                Response.Failure(e!!)
            }
            trySend(rollWaddingResponse)

        }
        awaitClose{
            snapshotListener.remove()
        }
    }
}