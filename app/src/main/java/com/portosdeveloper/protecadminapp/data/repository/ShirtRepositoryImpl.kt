package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.portosdeveloper.protecadminapp.core.Constants.SHIRT
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Shirt
import com.portosdeveloper.protecadminapp.domain.model.TotalCloth
import com.portosdeveloper.protecadminapp.domain.repository.ShirtRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class ShirtRepositoryImpl @Inject constructor(
    @Named(SHIRT) private val shirtRef : CollectionReference
) : ShirtRepository {


    override suspend fun createShirt(shirt: Shirt): Response<Boolean> {
        return try {
            shirtRef.document(shirt.id).set(shirt).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getShirtById(id: String): Flow<Response<Shirt>> = callbackFlow {
        val snapshotListener = shirtRef.document(id).addSnapshotListener{
                snapshot,e ->
            val shirtResponse = if(snapshot != null){
                val shirt = snapshot.toObject(Shirt :: class.java) ?: Shirt()
                Response.Success(shirt)
            }else{
                Response.Failure(e!!)
            }
            trySend(shirtResponse)

        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override suspend fun updateShirt(shirt: Shirt): Response<Boolean> {
        return try {
            val map : MutableMap<String, Any> = hashMapOf()
            map["inProgress"] = shirt.inProgress
            shirtRef.document(shirt.id).update(map).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }


}