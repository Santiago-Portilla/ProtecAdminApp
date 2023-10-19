package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.portosdeveloper.protecadminapp.core.Constants.THREAD
import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.repository.ThreadRepository
import com.portosdeveloper.protecadminapp.domain.model.Thread
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class ThreadRepositoryImpl @Inject constructor(
    @Named(THREAD) private val threadRef : CollectionReference
): ThreadRepository {

    override suspend fun createThread(thread: Thread): Response<Boolean> {
        return try {
            threadRef.document(thread.id).set(thread).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override suspend fun updateThread(thread: Thread): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = hashMapOf()
            map["totalThread"] = thread.totalThread
            map["totalMetersThread"] = thread.totalMetersThread
            threadRef.document(thread.id).update(map)
            Response.Success(true)
        }
        catch (e : Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun getTotalThread(id: String, idCamp: String): String{
        val getTotalThread = threadRef.document(id).get().await()
        return if(getTotalThread.exists()) {
            getTotalThread.getString(idCamp)!!
        }else{
            "0"
        }
    }

    override suspend fun addTotalThreadDay(totalThread: String, thread: Thread): Response<Boolean> {
        return try {
            threadRef.document(thread.id)
                .update(FieldPath.of("inputOutputTotalThreadArray"), FieldValue.arrayUnion(totalThread)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun stockTotal(): Flow<Response<List<Thread>>> = callbackFlow{
        val snapshotListener = threadRef.addSnapshotListener{
                snapshot,e ->

            val threadResponse = if(snapshot != null){
                val threads = snapshot.toObjects(Thread :: class.java)
                Response.Success(threads)
            }else{
                Response.Failure(e!!)
            }
            trySend(threadResponse)

        }
        awaitClose{
            snapshotListener.remove()
        }    }
}