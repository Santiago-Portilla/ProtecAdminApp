package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.snapshots
import com.portosdeveloper.protecadminapp.core.Constants.CLOTH
import com.portosdeveloper.protecadminapp.core.Constants.TOTALCLOTH
import com.portosdeveloper.protecadminapp.domain.model.Cloth
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.TotalCloth
import com.portosdeveloper.protecadminapp.domain.model.Wadding
import com.portosdeveloper.protecadminapp.domain.repository.ClothRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class ClothRepositoryImpl @Inject constructor(
    @Named(CLOTH) private val clothRef: CollectionReference,
    @Named(TOTALCLOTH) private val totalClothRef: CollectionReference
) : ClothRepository {


    override suspend fun createCloth(cloth: Cloth): Response<Boolean> {
        return try {
            clothRef.document(cloth.id).set(cloth).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun createTotalCloth(totalCloth: TotalCloth): Response<Boolean> {
        return try {
            totalClothRef.document(totalCloth.id).set(totalCloth).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getTotalMeasureById(id: String): Flow<TotalCloth> = callbackFlow {
        val snapshotListener = totalClothRef.document(id).addSnapshotListener{
                snapshot, e->
            val totalMeasure = snapshot?.toObject(TotalCloth :: class.java) ?: TotalCloth()
            trySend(totalMeasure)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override fun stockTotal(): Flow<Response<List<TotalCloth>>> = callbackFlow{
        val snapshotListener = totalClothRef.addSnapshotListener{
            snapshot,e ->

            val clothsResponse = if(snapshot != null){
                val cloths = snapshot.toObjects(TotalCloth :: class.java)
                Response.Success(cloths)
            }else{
                Response.Failure(e!!)
            }
            trySend(clothsResponse)

        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override suspend fun updateTotalCloth(totalCloth: TotalCloth): Response<Boolean> {
        return try {
            val map : MutableMap<String, Any> = hashMapOf()
            map["totalMeasure"] = totalCloth.totalMeasure
            totalClothRef.document(totalCloth.id).update(map).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }
    override suspend fun updateCloth(cloth: Cloth): Response<Boolean> {
        return try {
            val map : MutableMap<String, Any> = hashMapOf()
            map["state"] = "Inactivo"
            clothRef.document(cloth.id).update(map).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }


    override fun getClothList(cloth: String, color: String): Flow<Response<List<Cloth>>> = callbackFlow {
        val snapshotListener = clothRef
            .whereEqualTo("cloth",cloth)
            .whereEqualTo("color",color)
            .whereEqualTo("state","Activo")
            .addSnapshotListener{
                snapshot,e ->
                val clothsResponse = if(snapshot != null){
                    val cloths = snapshot.toObjects(Cloth :: class.java)
                    Response.Success(cloths)
                }else{
                    Response.Failure(e!!)
                }
                trySend(clothsResponse)

            }
        awaitClose{
            snapshotListener.remove()
        }

    }

    override suspend fun addDateCloth(newDate: String, totalCloth : TotalCloth): Response<Boolean> {
        return try {
            totalClothRef.document(totalCloth.id)
                .update(FieldPath.of("inputOutputDateArray"), FieldValue.arrayUnion(newDate)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }
}