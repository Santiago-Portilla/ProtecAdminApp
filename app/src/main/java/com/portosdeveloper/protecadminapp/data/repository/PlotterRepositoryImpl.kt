package com.portosdeveloper.protecadminapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.portosdeveloper.protecadminapp.core.Constants.PLOTTER
import com.portosdeveloper.protecadminapp.domain.model.Cloth
import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.TotalCloth
import com.portosdeveloper.protecadminapp.domain.repository.PlotterRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class PlotterRepositoryImpl @Inject constructor(
    @Named(PLOTTER) private val plotterRef: CollectionReference
): PlotterRepository {


    override suspend fun createPlotter(plotter: Plotter): Response<Boolean> {
        return try {
            plotterRef.document(plotter.id).set(plotter).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun updatePlotter(plotter: Plotter): Response<Boolean> {
        return try {
            val map : MutableMap<String, Any> = hashMapOf()
            map["state"] = "Inactivo"
            plotterRef.document(plotter.id).update(map).await()
            Response.Success(true)
        }
        catch (e : Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getIdPlotter(): Flow<String> = callbackFlow {
        val sizePlotter = plotterRef.get().addOnSuccessListener{
            val plotterId = it.size().toString()
            trySend(plotterId)
        }
        awaitClose{
            sizePlotter.isCanceled
        }
    }

    override fun stockTotal(): Flow<Response<List<Plotter>>> = callbackFlow {
        val snapshotListener = plotterRef.addSnapshotListener{
                snapshot,e ->

            val plotterResponse = if(snapshot != null){
                val plotters = snapshot.toObjects(Plotter :: class.java)
                Response.Success(plotters)
            }else{
                Response.Failure(e!!)
            }
            trySend(plotterResponse)

        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override fun getPlotterList(idPlotter: String): Flow<Response<List<Plotter>>> = callbackFlow {
        val snapshotListener = plotterRef
            .whereEqualTo("id",idPlotter)
            .whereEqualTo("state","Activo")
            .addSnapshotListener{
                    snapshot,e ->
                val plotterResponse = if(snapshot != null){
                    val plotters = snapshot.toObjects(Plotter :: class.java)
                    Response.Success(plotters)
                }else{
                    Response.Failure(e!!)
                }
                trySend(plotterResponse)

            }
        awaitClose{
            snapshotListener.remove()
        }

    }
}