package com.portosdeveloper.protecadminapp.presentation.screens.InputThreadScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.Thread
import com.portosdeveloper.protecadminapp.domain.use_cases.utils.UtilsUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.ThreadUseCases
import com.portosdeveloper.protecadminapp.presentation.screens.InputThreadScreen.components.InputThreadState
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputThreadViewModel @Inject constructor(
    private val threadUseCases: ThreadUseCases,
    private val utilsUseCases: UtilsUseCases
): ViewModel() {

    var state by mutableStateOf(InputThreadState())

    //YARD
    var isYardValid: Boolean by mutableStateOf(false)
    var yardErrMsg : String by mutableStateOf("*")

    //FUNCTION
    val stateFunction = mutableStateListOf<String>()
    private val listFunctionValidate = mutableStateListOf<Boolean>()
    val functionErrMsg = mutableStateListOf<String>()
    var isFunctionValid by mutableStateOf(false)

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

    // DATE
    private var inputDate by mutableStateOf("")

    //BUTTON
    var isEnabledInputThreadButton by mutableStateOf(false)

    var thread = Thread()
    var date = ActualDate()

    // INPUT BB DD
    var createThreadResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateThreadResponse by mutableStateOf<Response<Boolean>?>(null)
    var addTotalThreadDayResponse by mutableStateOf<Response<Boolean>?>(null)
    val stateThreadInBBDD = mutableStateListOf<String>()
    val stateMetersThreadInBBDD = mutableStateListOf<String>()

    init {
        getColorList()
        getUseList()
        getYardsList()
        inputDate = date.actualDate

    }

    private fun getColorList() = viewModelScope.launch {
        utilsUseCases.getList("Color","color").collect(){
            state = state.copy(colorList = it)
        }
    }
    private fun getUseList() = viewModelScope.launch {
        utilsUseCases.getList("Use","ThreadUses").collect(){
            state = state.copy(useList = it)
        }
    }
    private fun getYardsList() = viewModelScope.launch {
        utilsUseCases.getList("Yards","Yards").collect(){
            state = state.copy(yardList = it)
        }
    }

    private fun createThread(thread: Thread) = viewModelScope.launch {
        createThreadResponse = Response.Loading
        val result = threadUseCases.createThread(thread)
        createThreadResponse = result
    }
    private fun updateThread(thread: Thread) = viewModelScope.launch {
        updateThreadResponse = Response.Loading
        val result = threadUseCases.updateThread(thread)
        updateThreadResponse = result
    }
    private fun addTotalThreadDay(totalWadding: String, thread: Thread) = viewModelScope.launch {
        addTotalThreadDayResponse = Response.Loading
        val result = threadUseCases.addTotalThreadDay(totalWadding,thread)
        addTotalThreadDayResponse = result
    }
    private fun getTotalThread(id: String, index: Int) = viewModelScope.launch{
        val newValue = threadUseCases.getTotalThread(id,"totalThread")
        stateThreadInBBDD[index] = newValue
    }
    private fun getTotalMetersThread(id: String, index: Int) = viewModelScope.launch{
        val newValue = threadUseCases.getTotalThread(id,"totalMetersThread")
        stateMetersThreadInBBDD[index] = newValue
    }

    fun onGetTotalThread(index: Int){
        val id = "${stateFunction[index]} ${stateColor[index]}"
        getTotalThread(id, index)
        getTotalMetersThread(id,index)
    }
    fun onCreateThread(index: Int){
        val id = "${stateFunction[index]} ${stateColor[index]}"
        thread.id = id
        thread.totalThread = "${stateQuantity[index].toDouble()}"
        thread.totalMetersThread = "${stateQuantity[index].toDouble()*yardConvert()}"
        createThread(thread)
    }
    fun onUpdateThread(index: Int){
        val id = "${stateFunction[index]} ${stateColor[index]}"
        thread.id = id
        thread.totalThread = "${stateQuantity[index].toDouble() + stateThreadInBBDD[index].toDouble()}"
        thread.totalMetersThread = "${stateQuantity[index].toDouble()*yardConvert() + stateMetersThreadInBBDD[index].toDouble()}"
        updateThread(thread)
    }
    fun onAddTotalThreadDay(index: Int){
        val id = "${stateFunction[index]} ${stateColor[index]}"
        thread.id = id
        addTotalThreadDay("${stateQuantity[index]},${state.yards},${date.actualHour},Input",thread)
    }

    private fun yardConvert(): Double{
        var yardConverter = 0.0
        when(state.yards){
            "10000 Yardas" ->{
                yardConverter = 9144.0
            }
            "5000 Yardas" ->{
                yardConverter = 4572.0
            }
            "2500 Metros" ->{
                yardConverter = 2500.0
            }
        }
        return yardConverter
    }

    private fun enabledInputThreadButton(){
        isEnabledInputThreadButton =
            isFunctionValid &&
            isColorValid &&
            isQuantityValid&&
            isYardValid
    }

    fun onYardsInput(yard: String){
        state = state.copy(yards = yard)
    }

    fun onFunctionInput(index: Int, function: String) {
        stateFunction[index] = function
    }
    fun onColorInput(index: Int, color: String) {
        stateColor[index] = color
    }
    fun onQuantityInput(index: Int, quantity: String) {
        stateQuantity[index] = quantity
    }
    private fun addTextFieldFunction() {
        stateFunction.add("")
        functionErrMsg.add("*")
        listFunctionValidate.add(false)
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

    private fun removeTextFieldFunction() {
        stateFunction.removeLast()
        listFunctionValidate.removeLast()
        functionErrMsg.removeLast()
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

    fun addInputThreadRow(){
        addTextFieldFunction()
        addTextFieldColor()
        addTextFieldQuantity()
        stateThreadInBBDD.add("0")
        stateMetersThreadInBBDD.add("0")
    }

    fun removeInputThreadRow(){
        removeTextFieldFunction()
        removeTextFieldColor()
        removeTextFieldQuantity()
        stateThreadInBBDD.removeLast()
        stateMetersThreadInBBDD.removeLast()
    }

    fun validateYard(){
        if(state.yards != ""){
            isYardValid = true
            yardErrMsg = ""
        }else{
            isYardValid = false
            yardErrMsg = "*"
        }
        enabledInputThreadButton()
    }

    fun validateFunction(index: Int){
        if(stateFunction[index].isNotEmpty()){
            listFunctionValidate[index] =  true
            functionErrMsg[index] = ""
        }else{
            listFunctionValidate[index] =  false
            functionErrMsg[index] = "*"
        }
        enabledInputThreadButton()
    }

    fun validateAllFunction(){
        isFunctionValid = listFunctionValidate.all{it}
        enabledInputThreadButton()
    }

    fun validateColor(index: Int){
        if(stateColor[index].isNotEmpty()){
            listColorValidate[index] =  true
            colorErrMsg[index] = ""
        }else{
            listColorValidate[index] =  false
            colorErrMsg[index] = "*"
        }
        enabledInputThreadButton()
    }

    fun validateAllColor(){
        isColorValid = listColorValidate.all{it}
        enabledInputThreadButton()
    }

    fun validateQuantity(index: Int){
        if(stateQuantity[index].isNotEmpty()){
            listQuantityValidate[index] =  true
            quantityErrMsg[index] = ""
        }else{
            listQuantityValidate[index] =  false
            quantityErrMsg[index] = "*"
        }
        enabledInputThreadButton()
    }

    fun validateAllQuantity(){
        isQuantityValid = listQuantityValidate.all{it}
        enabledInputThreadButton()
    }
}