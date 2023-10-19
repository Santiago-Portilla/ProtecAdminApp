package com.portosdeveloper.protecadminapp.domain.repository

import com.portosdeveloper.protecadminapp.domain.model.Cloth
import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.TotalCloth
import kotlinx.coroutines.flow.Flow

interface PlotterRepository {

    suspend fun createPlotter(plotter : Plotter): Response<Boolean>
    suspend fun updatePlotter(plotter: Plotter): Response<Boolean>
    fun getIdPlotter(): Flow<String>
    fun stockTotal(): Flow<Response<List<Plotter>>>
    fun getPlotterList(plotter:String): Flow<Response<List<Plotter>>>
}