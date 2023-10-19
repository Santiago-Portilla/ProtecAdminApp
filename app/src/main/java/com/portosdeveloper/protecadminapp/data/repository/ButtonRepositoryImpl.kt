package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.portosdeveloper.protecadminapp.core.Constants.BUTTON
import com.portosdeveloper.protecadminapp.core.Constants.YARN
import com.portosdeveloper.protecadminapp.domain.model.Button
import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Yarn
import com.portosdeveloper.protecadminapp.domain.repository.ButtonRepository
import com.portosdeveloper.protecadminapp.domain.repository.YarnRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class ButtonRepositoryImpl @Inject constructor(
    @Named(BUTTON) private val buttonRef : CollectionReference
): ButtonRepository {

    override suspend fun createButton(button: Button): Response<Boolean> {
        return try {
            buttonRef.document(button.id).set(button).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override suspend fun updateButton(button: Button): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = hashMapOf()
            map["totalButton"] = button.totalButton
            map["totalGrandButton"] = button.totalGrandButton
            buttonRef.document(button.id).update(map)
            Response.Success(true)
        }
        catch (e : Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun getTotalButton(id: String, idCamp: String): String{
        val getTotalButton = buttonRef.document(id).get().await()
        return if(getTotalButton.exists()) {
            getTotalButton.getString(idCamp)!!
        }else{
            "0"
        }
    }

    override suspend fun addTotalButtonDay(totalButton: String, button: Button): Response<Boolean> {
        return try {
            buttonRef.document(button.id).update(FieldPath.of("inputTotalButtonArray"), FieldValue.arrayUnion(totalButton)).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun stockTotal(): Flow<Response<List<Button>>> = callbackFlow {
        val snapshotListener = buttonRef.addSnapshotListener{
                snapshot,e ->

            val buttonResponse = if(snapshot != null){
                val buttons = snapshot.toObjects(Button :: class.java)
                Response.Success(buttons)
            }else{
                Response.Failure(e!!)
            }
            trySend(buttonResponse)

        }
        awaitClose{
            snapshotListener.remove()
        }
    }
}