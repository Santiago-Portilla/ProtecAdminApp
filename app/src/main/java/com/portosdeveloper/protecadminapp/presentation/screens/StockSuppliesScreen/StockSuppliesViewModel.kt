package com.portosdeveloper.protecadminapp.presentation.screens.StockSuppliesScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.*
import com.portosdeveloper.protecadminapp.domain.use_cases.cloth.ClothUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.plotter.PlotterUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.portosdeveloper.protecadminapp.domain.model.Thread
import javax.inject.Inject


@HiltViewModel
class StockSuppliesViewModel @Inject constructor(
    private val clothUseCases: ClothUseCases,
    private val plotterUseCases: PlotterUseCases,
    private val waddingUseCases: WaddingUseCases,
    private val rollWaddingUseCases: RollWaddingUseCases,
    private val threadUseCases: ThreadUseCases,
    private val yarnUseCases: YarnUseCases,
    private val reflectiveUseCases: ReflectiveUseCases,
    private val buttonUseCases: ButtonUseCases,
    private val packingUseCases: PackingUseCases,
): ViewModel(){

    var stockTotalClothResponse by mutableStateOf<Response<List<TotalCloth>>?>(null)
    var stockPlotterResponse by mutableStateOf<Response<List<Plotter>>?>(null)
    var stockWaddingResponse by mutableStateOf<Response<List<Wadding>>?>(null)
    var stockRollWaddingResponse by mutableStateOf<Response<List<RollWadding>>?>(null)
    var stockThreadResponse by mutableStateOf<Response<List<Thread>>?>(null)
    var stockYarnResponse by mutableStateOf<Response<List<Yarn>>?>(null)
    var stockReflectiveResponse by mutableStateOf<Response<List<Reflective>>?>(null)
    var stockButtonResponse by mutableStateOf<Response<List<Button>>?>(null)
    var stockPackingResponse by mutableStateOf<Response<List<Packing>>?>(null)


    init {
        startScreen()
    }

    private fun startScreen(){
        stockTotalCloth()
        stockPlotter()
        stockWadding()
        stockRollWadding()
        stockThread()
        stockYarn()
        stockReflective()
        stockButton()
        stockPacking()
    }

    private fun stockTotalCloth() = viewModelScope.launch{
        stockTotalClothResponse = Response.Loading
        clothUseCases.stockTotalCloth().collect{ responseCloth ->
            stockTotalClothResponse = responseCloth
        }
    }
    private fun stockPlotter() = viewModelScope.launch{
        stockPlotterResponse = Response.Loading
        plotterUseCases.stockPlotter().collect{ responsePlotter ->
            stockPlotterResponse = responsePlotter
        }
    }
    private fun stockWadding() = viewModelScope.launch{
        stockWaddingResponse = Response.Loading
        waddingUseCases.stockWadding().collect{ responseWadding ->
            stockWaddingResponse = responseWadding
        }
    }
    private fun stockRollWadding() = viewModelScope.launch{
        stockRollWaddingResponse = Response.Loading
        rollWaddingUseCases.stockRollWadding().collect{ responseRollWadding ->
            stockRollWaddingResponse = responseRollWadding
        }
    }
    private fun stockThread() = viewModelScope.launch{
        stockThreadResponse = Response.Loading
        threadUseCases.stockThread().collect{ responseThread ->
            stockThreadResponse = responseThread
        }
    }
    private fun stockYarn() = viewModelScope.launch{
        stockYarnResponse = Response.Loading
        yarnUseCases.stockYarn().collect{ responseYarn ->
            stockYarnResponse = responseYarn
        }
    }
    private fun stockReflective() = viewModelScope.launch{
        stockReflectiveResponse = Response.Loading
        reflectiveUseCases.stockReflective().collect{ responseReflective ->
            stockReflectiveResponse = responseReflective
        }
    }
    private fun stockButton() = viewModelScope.launch{
        stockButtonResponse = Response.Loading
        buttonUseCases.stockButton().collect{ responseButton ->
            stockButtonResponse = responseButton
        }
    }
    private fun stockPacking() = viewModelScope.launch{
        stockPackingResponse = Response.Loading
        packingUseCases.stockPacking().collect{ responsePacking ->
            stockPackingResponse = responsePacking
        }
    }
}