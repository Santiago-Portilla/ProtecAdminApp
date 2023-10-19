package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.portosdeveloper.protecadminapp.core.Constants.UTILS
import com.portosdeveloper.protecadminapp.domain.repository.UtilsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named

class UtilsRepositoryImpl @Inject constructor(
    @Named(UTILS) private val utilsRef:CollectionReference
) : UtilsRepository {

    override fun getArray(util: String, nameArray: String): Flow<List<String>> = callbackFlow {
        val snapshotListener = utilsRef.document(util).get().addOnSuccessListener {
            val utilArray = it.get(nameArray) as? List<String>
            trySend(utilArray!!)
        }
        awaitClose{
            snapshotListener.isCanceled
        }
    }
}