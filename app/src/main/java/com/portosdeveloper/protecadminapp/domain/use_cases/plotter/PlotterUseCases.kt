package com.portosdeveloper.protecadminapp.domain.use_cases.plotter

data class PlotterUseCases(
    var createPlotter: CreatePlotter,
    var getIdPlotter : GetIdPlotter,
    var stockPlotter: StockPlotter,
    var getPlotterList : GetPlotterList,
    var updatePlotter: UpdatePlotter
)
