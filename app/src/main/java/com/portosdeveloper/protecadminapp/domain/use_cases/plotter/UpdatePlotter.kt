package com.portosdeveloper.protecadminapp.domain.use_cases.plotter

import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.repository.PlotterRepository
import javax.inject.Inject

class UpdatePlotter @Inject constructor(private val repository: PlotterRepository) {
    suspend operator fun invoke(plotter: Plotter) = repository.updatePlotter(plotter)
}