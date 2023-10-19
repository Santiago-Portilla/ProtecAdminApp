package com.portosdeveloper.protecadminapp.presentation.screens.InputYarnScreens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Yarn
import com.portosdeveloper.protecadminapp.domain.use_cases.utils.UtilsUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.YarnUseCases
import com.portosdeveloper.protecadminapp.presentation.screens.InputYarnScreens.components.InputYarnState
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputYarnViewModel @Inject constructor(
    private val yarnUseCases: YarnUseCases,
    private val utilsUseCases: UtilsUseCases
) : ViewModel() {

    var state by mutableStateOf(InputYarnState())

    //COLOR
    val stateColor = mutableStateListOf<String>()
    private val listColorValidate = mutableStateListOf<Boolean>()
    val colorErrMsg = mutableStateListOf<String>()
    var isColorValid by mutableStateOf(false)

    //QUANTITY
    val stateQuantity = mutableStateListOf<String>()
    private val listQuantityValidate = mutableStateListOf<Boolean>()
    val quantityErrMsg = mutableStateListOf<String>()
    var isQuantityValid by mutableStateOf(false)

    //BUTTON
    var isEnabledInputYarnButton by mutableStateOf(false)

    // DATE
    private var inputDate by mutableStateOf("")

    var yarn = Yarn()
    var date = ActualDate()

    // INPUT BB DD
    var createYarnResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateYarnResponse by mutableStateOf<Response<Boolean>?>(null)
    var addTotalYarnDayResponse by mutableStateOf<Response<Boolean>?>(null)
    val stateYarnInBBDD = mutableStateListOf<String>()
    private val stateMetersYarnInBBDD = mutableStateListOf<String>()

    init {
        getColorList()
        inputDate = date.actualDate
    }

    private fun getColorList() = viewModelScope.launch {
        utilsUseCases.getList("Color","color").collect(){
            state = state.copy(colorList = it)
        }
    }

    private fun createYarn(yarn: Yarn) = viewModelScope.launch {
        createYarnResponse = Response.Loading
        val result = yarnUseCases.createYarn(yarn)
        createYarnResponse = result
    }
    private fun updateYarn(yarn: Yarn) = viewModelScope.launch {
        updateYarnResponse = Response.Loading
        val result = yarnUseCases.updateYarn(yarn)
        updateYarnResponse = result
    }
    private fun addTotalYarnDay(totalYarn: String, yarn: Yarn) = viewModelScope.launch {
        addTotalYarnDayResponse = Response.Loading
        val result = yarnUseCases.addTotalYarnDay(totalYarn,yarn)
        addTotalYarnDayResponse = result
    }
    private fun getTotalYarn(id: String, index: Int) = viewModelScope.launch{
        val newValue = yarnUseCases.getTotalYarn(id,"totalYarn")
        stateYarnInBBDD[index] = newValue
    }

    private fun getTotalMetersYarn(id: String, index: Int) = viewModelScope.launch{
        val newValue = yarnUseCases.getTotalYarn(id,"totalMetersYarn")
        stateMetersYarnInBBDD[index] = newValue
    }

    fun onGetTotalYarn(index: Int){
        val id = stateColor[index]
        getTotalYarn(id, index)
        getTotalMetersYarn(id,index)
    }
    fun onCreateYarn(index: Int){
        val id = stateColor[index]
        yarn.id = id
        yarn.totalYarn = stateQuantity[index]
        yarn.totalMetersYarn = "${stateQuantity[index].toDouble()*13000.0}"
        createYarn(yarn)
    }
    fun onUpdateYarn(index: Int){
        val id = stateColor[index]
        yarn.id = id
        yarn.totalYarn = (stateQuantity[index].toInt() + stateYarnInBBDD[index].toInt()).toString()
        yarn.totalMetersYarn = "${stateMetersYarnInBBDD[index].toDouble()+stateQuantity[index].toDouble()*13000.0}"
        updateYarn(yarn)
    }
    fun onAddTotalYarnDay(index: Int){
        val id = stateColor[index]
        yarn.id = id
        addTotalYarnDay("${stateQuantity[index]},${date.actualHour},Input",yarn)
    }

    private fun enabledInputYarnButton(){
        isEnabledInputYarnButton =
            isColorValid &&
            isQuantityValid
    }

    fun onColorInput(index: Int, color: String) {
        stateColor[index] = color
    }
    fun onQuantityInput(index: Int, quantity: String) {
        stateQuantity[index] = quantity
    }
    private fun addTextFieldColor() {
        stateColor.add("")
        colorErrMsg.add("*")
        listColorValidate.add(false)

    }
    private fun addTextFieldQuantity() {
        stateQuantity.add("")
        quantityErrMsg.add("*")
        listQuantityValidate.add(false)
    }

    private fun removeTextFieldColor() {
        stateColor.removeLast()
        listColorValidate.removeLast()
        colorErrMsg.removeLast()
    }
    private fun removeTextFieldQuantity() {
        stateQuantity.removeLast()
        listQuantityValidate.removeLast()
        quantityErrMsg.removeLast()
    }

    fun addInputYarnRow(){
        addTextFieldColor()
        addTextFieldQuantity()
        stateYarnInBBDD.add("0")
        stateMetersYarnInBBDD.add("0")
    }

    fun removeInputYarnRow(){
        removeTextFieldColor()
        removeTextFieldQuantity()
        stateYarnInBBDD.removeLast()
        stateMetersYarnInBBDD.removeLast()
    }

    fun validateColor(index: Int){
        if(stateColor[index].isNotEmpty()){
            listColorValidate[index] =  true
            colorErrMsg[index] = ""
        }else{
            listColorValidate[index] =  false
            colorErrMsg[index] = "*"
        }
        enabledInputYarnButton()
    }

    fun validateAllColor(){
        isColorValid = listColorValidate.all{it}
        enabledInputYarnButton()
    }

    fun validateQuantity(index: Int){
        if(stateQuantity[index].isNotEmpty()){
            listQuantityValidate[index] =  true
            quantityErrMsg[index] = ""
        }else{
            listQuantityValidate[index] =  false
            quantityErrMsg[index] = "*"
        }
        enabledInputYarnButton()
    }

    fun validateAllQuantity(){
        isQuantityValid = listQuantityValidate.all{it}
        enabledInputYarnButton()
    }
}