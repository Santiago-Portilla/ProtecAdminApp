package com.portosdeveloper.protecadminapp.domain.use_cases.plotter

import com.portosdeveloper.protecadminapp.domain.repository.PlotterRepository
import javax.inject.Inject

class GetPlotterList @Inject constructor(private val repository: PlotterRepository) {
    operator fun invoke(plotter : String) = repository.getPlotterList(plotter)
}