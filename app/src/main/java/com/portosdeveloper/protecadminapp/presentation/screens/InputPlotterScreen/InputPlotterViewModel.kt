package com.portosdeveloper.protecadminapp.presentation.screens.InputPlotterScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.use_cases.plotter.PlotterUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.utils.UtilsUseCases
import com.portosdeveloper.protecadminapp.presentation.screens.InputPlotterScreen.components.InputPlotterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class InputPlotterViewModel @Inject constructor(
    private val plotterUseCases: PlotterUseCases,
    private val utilsUseCases: UtilsUseCases
): ViewModel() {

    // STATE FORM
    var state by mutableStateOf(InputPlotterState())

    //WIDTH
    var isWidthValid by mutableStateOf(false)
        private set
    var widthErrMsg by mutableStateOf("Ingrese el ancho")
        private set

    //HEIGHT
    var isHeightValid by mutableStateOf(false)
        private set
    var heightErrMsg by mutableStateOf("Ingrese el alto")
        private set

    //SIZE
    val stateSize = mutableStateListOf<String>()
    val listSizeValidate = mutableStateListOf<Boolean>()
    val sizeErrMsg = mutableStateListOf<String>()
    var isSizeValid by mutableStateOf(false)

    //GENDER
    val stateGender = mutableStateListOf<String>()
    val listGenderValidate = mutableStateListOf<Boolean>()
    val genderErrMsg = mutableStateListOf<String>()
    var isGenderValid by mutableStateOf(false)

    //SIZES
    val sizes = mutableStateListOf<String>()

    //BUTTON
    var isEnabledInputPlotterButton by mutableStateOf(false)

    var createPlotterResponse by mutableStateOf<Response<Boolean>?>(null)

    var plotter = Plotter()

    init {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        state = state.copy(inputDate = dateFormat.format(calendar.time))
        getSizesList()
        getGenderList()
        getIdPlotter()
    }

    private fun getSizesList() = viewModelScope.launch {
        utilsUseCases.getList("Sizes","NormalSizes").collect(){
            state = state.copy(sizesList = it)
        }
    }
    private fun getGenderList() = viewModelScope.launch {
        utilsUseCases.getList("Gender","Gender").collect(){
            state = state.copy(genderList = it)
        }
    }

    private fun enabledInputPlotterButton(){
        isEnabledInputPlotterButton =
            isWidthValid &&
            isHeightValid &&
            isGenderValid &&
            isSizeValid
    }

    fun createPlotter(plotter: Plotter) = viewModelScope.launch {
        createPlotterResponse = Response.Loading
        val result = plotterUseCases.createPlotter(plotter)
        createPlotterResponse = result
    }

    private fun getIdPlotter() = viewModelScope.launch{
        plotterUseCases.getIdPlotter().collect(){
            if(it.isNotEmpty()){
                state = state.copy(idPlotter = it)
                state = state.copy(newIdPlotter = (state.idPlotter.toInt()+1).toString())
            }else{
                state.idPlotter
            }
        }
    }
    fun plotterSizes(index: Int){
        sizes.add(stateGender[index][0] + stateSize[index])
        state = state.copy(sizes = sizes.joinToString(separator = ","))
    }

    fun onCretePlotter(){
        plotter.id = state.newIdPlotter
        plotter.height = state.height
        plotter.width = state.width
        plotter.sizes = state.sizes
        plotter.inputDate = state.inputDate
        createPlotter(plotter)
    }

    fun onWidthInput(width: String){
        state = state.copy(width = width)
    }
    fun onHeightInput(height: String){
        state = state.copy(height = height)
    }
    fun onSizeInput(index: Int, size: String) {
        stateSize[index] = size
    }
    fun onGenderInput(index: Int, gender: String) {
        stateGender[index] = gender
    }
    fun addTextFieldSize() {
        stateSize.add("")
        sizeErrMsg.add("*")
        listSizeValidate.add(false)

    }
    fun addTextFieldGender() {
        stateGender.add("")
        genderErrMsg.add("*")
        listGenderValidate.add(false)

    }
    fun removeTextFieldSize() {
        stateSize.removeLast()
        listSizeValidate.removeLast()
        sizeErrMsg.removeLast()
    }
    fun removeTextFieldGender() {
        stateGender.removeLast()
        listGenderValidate.removeLast()
        genderErrMsg.removeLast()
    }

    fun validateWidth(){
        if(state.width.isNotEmpty()){
            isWidthValid = true
            widthErrMsg = ""
        }else{
            isWidthValid = false
            widthErrMsg = "Porfavor seleccione una Tela"
        }
        enabledInputPlotterButton()
    }

    fun validateHeight(){
        if(state.height.isNotEmpty()){
            isHeightValid = true
            heightErrMsg = ""
        }else{
            isHeightValid = false
            heightErrMsg = "Porfavor seleccione una Tela"
        }
        enabledInputPlotterButton()
    }

    fun validateSize(index: Int){
        if(stateSize[index].isNotEmpty()){
            listSizeValidate[index] =  true
            sizeErrMsg[index] = ""
        }else{
            listSizeValidate[index] =  false
            sizeErrMsg[index] = "*"
        }
        enabledInputPlotterButton()
    }

    fun validateAllSize(){
        isSizeValid = listSizeValidate.all{it}
        enabledInputPlotterButton()
    }

    fun validateGender(index: Int){
        if(stateGender[index].isNotEmpty()){
            listGenderValidate[index] =  true
            genderErrMsg[index] = ""
        }else{
            listGenderValidate[index] =  false
            genderErrMsg[index] = "*"
        }
        enabledInputPlotterButton()
    }

    fun validateAllGender(){
        isGenderValid = listGenderValidate.all{it}
        enabledInputPlotterButton()
    }




}