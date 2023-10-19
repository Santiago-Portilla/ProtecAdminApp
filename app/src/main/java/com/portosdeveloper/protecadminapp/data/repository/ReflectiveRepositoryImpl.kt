package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.portosdeveloper.protecadminapp.core.Constants.REFLECTIVE
import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Reflective
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.repository.ReflectiveRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class ReflectiveRepositoryImpl @Inject constructor(
    @Named(REFLECTIVE) private val reflectiveRef : CollectionReference
): ReflectiveRepository {

    override suspend fun createReflective(reflective: Reflective): Response<Boolean> {
        return try {
            reflectiveRef.document(reflective.id).set(reflective).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override suspend fun updateReflective(reflective: Reflective): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = hashMapOf()
            map["totalReflective"] = reflective.totalReflective
            reflectiveRef.document(reflective.id).update(map)
            Response.Success(true)
        }
        catch (e : Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun getTotalReflective(id: String): String{
        val getTotalReflective = reflectiveRef.document(id).get().await()
        return if(getTotalReflective.exists()) {
            getTotalReflective.getString("totalReflective")!!
        }else{
            "0"
        }
    }

    override suspend fun addDateReflective(newDate: String, reflective : Reflective): Response<Boolean> {
        return try {
            reflectiveRef.document(reflective.id)
                .update(FieldPath.of("inputDateArray"), FieldValue.arrayUnion(newDate)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun addTotalReflectiveDay(totalReflective: String, reflective: Reflective): Response<Boolean> {
        return try {
            reflectiveRef.document(reflective.id)
                .update(FieldPath.of("inputTotalReflectiveArray"), FieldValue.arrayUnion(totalReflective)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun stockTotal(): Flow<Response<List<Reflective>>> = callbackFlow {
        val snapshotListener = reflectiveRef.addSnapshotListener{
                snapshot,e ->

            val reflectiveResponse = if(snapshot != null){
                val reflectives = snapshot.toObjects(Reflective :: class.java)
                Response.Success(reflectives)
            }else{
                Response.Failure(e!!)
            }
            trySend(reflectiveResponse)

        }
        awaitClose{
            snapshotListener.remove()
        }
    }
}