package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.portosdeveloper.protecadminapp.core.Constants.WADDING
import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Wadding
import com.portosdeveloper.protecadminapp.domain.repository.WaddingRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class WaddingRepositoryImpl @Inject constructor(
    @Named(WADDING) private val waddingRef : CollectionReference
): WaddingRepository {

    override suspend fun createWadding(wadding: Wadding): Response<Boolean> {
        return try {
            waddingRef.document(wadding.id).set(wadding).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override suspend fun updateWadding(wadding: Wadding): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = hashMapOf()
            map["totalWadding"] = wadding.totalWadding
            waddingRef.document(wadding.id).update(map)
            Response.Success(true)
        }
        catch (e : Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getTotalWadding(id: String): Flow<String> = callbackFlow{
        val getTotalWadding = waddingRef.document(id).get().addOnSuccessListener{
            val wadding = it.getString("totalWadding") ?: "0"
            trySend(wadding)
        }
        awaitClose{
            getTotalWadding.isCanceled
        }
    }

    override suspend fun addDateWadding(newDate: String, wadding : Wadding): Response<Boolean> {
        return try {
            waddingRef.document(wadding.id)
                .update(FieldPath.of("inputOutputDateArray"), FieldValue.arrayUnion(newDate)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun stockTotal(): Flow<Response<List<Wadding>>> = callbackFlow{
        val snapshotListener = waddingRef.addSnapshotListener{
                snapshot,e ->

            val waddingResponse = if(snapshot != null){
                val waddings = snapshot.toObjects(Wadding :: class.java)
                Response.Success(waddings)
            }else{
                Response.Failure(e!!)
            }
            trySend(waddingResponse)

        }
        awaitClose{
            snapshotListener.remove()
        }
    }
}