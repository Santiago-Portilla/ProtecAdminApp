package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.portosdeveloper.protecadminapp.core.Constants.USERSWORKSHOP
import com.portosdeveloper.protecadminapp.domain.model.Cloth
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop
import com.portosdeveloper.protecadminapp.domain.repository.UserWorkShopRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named


class UserWorkShopReposiotryImpl @Inject constructor(
    @Named(USERSWORKSHOP) private val userWorkShopRef : CollectionReference
)     : UserWorkShopRepository {
    override fun getCutUserWorShop(): Flow<Response<List<UserWorkShop>>> = callbackFlow {
        val snapshotListener = userWorkShopRef
            .whereEqualTo("job","Corte")
            .addSnapshotListener{
                    snapshot,e ->
                val userWorkShopResponse = if(snapshot != null){
                    val userWorkShop = snapshot.toObjects(UserWorkShop :: class.java)
                    Response.Success(userWorkShop)
                }else{
                    Response.Failure(e!!)
                }
                trySend(userWorkShopResponse)

            }
        awaitClose{
            snapshotListener.remove()
        }
    }
    override suspend fun updateWorkProgressList(userWorkShop: UserWorkShop, newItem: String): Response<Boolean> {
        return try{
            userWorkShopRef.document(userWorkShop.id).
            update(FieldPath.of("workListInProgress"), FieldValue.arrayUnion(newItem)).await()
            Response.Success(true)

        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun updateSalaryWorkWeek(userWorkShop: UserWorkShop): Response<Boolean> {
        return try {
            val map : MutableMap<String, Any> = hashMapOf()
            map["workWeek"] = "0"
            userWorkShopRef.document(userWorkShop.id).update(map).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun updateSalaryWorkMonth(userWorkShop: UserWorkShop, newDate: String): Response<Boolean> {
        return try {
            val map : MutableMap<String, Any> = hashMapOf()
            map["workMonth"] = "0"
            map["dateStart"] = newDate
            userWorkShopRef.document(userWorkShop.id).update(map).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserWorShopList(): Flow<Response<List<UserWorkShop>>> = callbackFlow {
        val snapshotListener = userWorkShopRef
            .addSnapshotListener{
                    snapshot,e ->
                val userWorkShopResponse = if(snapshot != null){
                    val userWorkShop = snapshot.toObjects(UserWorkShop :: class.java)
                    Response.Success(userWorkShop)
                }else{
                    Response.Failure(e!!)
                }
                trySend(userWorkShopResponse)

            }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override suspend fun updateWorkFinishList(userWorkShop: UserWorkShop, newList: List<String>): Response<Boolean> {
        return try {
            val map : MutableMap<String, Any> = hashMapOf()
            map["workListFinish"] = newList
            userWorkShopRef.document(userWorkShop.id).update(map).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun updateWorkPaidList(userWorkShop: UserWorkShop, newItem: String): Response<Boolean> {
        return try {
            userWorkShopRef.document(userWorkShop.id)
                .update(FieldPath.of("workListPaid"), FieldValue.arrayUnion(newItem)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }
}