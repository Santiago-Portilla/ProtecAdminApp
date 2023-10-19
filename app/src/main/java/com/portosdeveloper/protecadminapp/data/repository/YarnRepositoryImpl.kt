package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.portosdeveloper.protecadminapp.core.Constants.YARN
import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Yarn
import com.portosdeveloper.protecadminapp.domain.repository.YarnRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class YarnRepositoryImpl @Inject constructor(
    @Named(YARN) private val yarnRef : CollectionReference
): YarnRepository {

    override suspend fun createYarn(yarn: Yarn): Response<Boolean> {
        return try {
            yarnRef.document(yarn.id).set(yarn).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override suspend fun updateYarn(yarn: Yarn): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = hashMapOf()
            map["totalYarn"] = yarn.totalYarn
            map["totalMetersYarn"] = yarn.totalMetersYarn
            yarnRef.document(yarn.id).update(map)
            Response.Success(true)
        }
        catch (e : Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun getTotalYarn(id: String, idCamp: String): String{
        val getTotalYarn = yarnRef.document(id).get().await()
        return if(getTotalYarn.exists()) {
            getTotalYarn.getString(idCamp)!!
        }else{
            "0"
        }
    }

    override suspend fun addTotalYarnDay(totalYarn: String, yarn: Yarn): Response<Boolean> {
        return try {
            yarnRef.document(yarn.id)
                .update(FieldPath.of("inputTotalYarnArray"), FieldValue.arrayUnion(totalYarn)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun stockTotal(): Flow<Response<List<Yarn>>> = callbackFlow {
        val snapshotListener = yarnRef.addSnapshotListener{
                snapshot,e ->

            val yarnResponse = if(snapshot != null){
                val yarns = snapshot.toObjects(Yarn :: class.java)
                Response.Success(yarns)
            }else{
                Response.Failure(e!!)
            }
            trySend(yarnResponse)

        }
        awaitClose{
            snapshotListener.remove()
        }
    }
}