package com.portosdeveloper.protecadminapp.presentation.screens.InputWaddingScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.RollWadding
import com.portosdeveloper.protecadminapp.domain.model.Wadding
import com.portosdeveloper.protecadminapp.domain.use_cases.utils.UtilsUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.RollWaddingUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.WaddingUseCases
import com.portosdeveloper.protecadminapp.presentation.screens.InputWaddingScreen.components.WaddingState
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class InputWaddingViewModel @Inject constructor(
    private val waddingUseCases: WaddingUseCases,
    private val rollWaddingUseCases: RollWaddingUseCases,
    private val utilsUseCases: UtilsUseCases
) : ViewModel() {

    // STATE FORM
    var state by mutableStateOf(WaddingState())

    //PART
    val statePart = mutableStateListOf<String>()
    private val listPartValidate = mutableStateListOf<Boolean>()
    val partErrMsg = mutableStateListOf<String>()
    var isPartValid by mutableStateOf(false)

    //SIZE
    val stateSize = mutableStateListOf<String>()
    private val listSizeValidate = mutableStateListOf<Boolean>()
    val sizeErrMsg = mutableStateListOf<String>()
    var isSizeValid by mutableStateOf(false)

    //QUANTITY
    val stateQuantity = mutableStateListOf<String>()
    private val listQuantityValidate = mutableStateListOf<Boolean>()
    val quantityErrMsg = mutableStateListOf<String>()
    var isQuantityValid by mutableStateOf(false)

    // DATE
    private var inputDate by mutableStateOf("")


    //BUTTON
    var isEnabledInputWaddingButton by mutableStateOf(false)

    var wadding = Wadding()
    var rollWadding = RollWadding()
    var date = ActualDate()

    // INPUT BB DD
    var createWaddingResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateWaddingResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateRollWaddingResponse by mutableStateOf<Response<Boolean>?>(null)
    var addDateResponse by mutableStateOf<Response<Boolean>?>(null)
    var addDateRollWaddingResponse by mutableStateOf<Response<Boolean>?>(null)
    val stateWaddingInBBDD = mutableStateListOf<String>()
    private var listTotalInBBDD by mutableStateOf("")

    init {
        inputDate = date.actualDate
        getSizesList()
        getPartsList()
        getTotalRollWadding()
    }

    private fun getSizesList() = viewModelScope.launch {
        utilsUseCases.getList("Sizes","WaddingSizes").collect(){
            state = state.copy(sizesList = it)
        }
    }
    private fun getPartsList() = viewModelScope.launch {
        utilsUseCases.getList("Parts","Part").collect(){
            state = state.copy(partsList = it)
        }
    }

    private fun updateWadding(wadding: Wadding) = viewModelScope.launch {
        updateWaddingResponse = Response.Loading
        val result = waddingUseCases.updateWadding(wadding)
        updateWaddingResponse = result
    }
    private fun updateRollWadding(rollWadding: RollWadding) = viewModelScope.launch {
        updateRollWaddingResponse = Response.Loading
        val result = rollWaddingUseCases.updateRollWadding(rollWadding)
        updateRollWaddingResponse = result
    }
    private fun createWadding(wadding: Wadding) = viewModelScope.launch {
        createWaddingResponse = Response.Loading
        val result = waddingUseCases.createWadding(wadding)
        createWaddingResponse = result
    }
    private fun addDateWadding(newDate: String, wadding: Wadding) = viewModelScope.launch {
        addDateResponse = Response.Loading
        val result = waddingUseCases.addDateWadding(newDate,wadding)
        addDateResponse = result
    }

    private fun getTotalWadding(id: String, index: Int) = viewModelScope.launch{
        waddingUseCases.getTotalWadding(id).collect(){
            stateWaddingInBBDD[index] = it
        }
    }
    private fun getTotalRollWadding() = viewModelScope.launch{
        listTotalInBBDD = rollWaddingUseCases.getTotalRollWadding("150 CM")
    }

    fun onGetTotalWadding(index: Int){
        val id = "${statePart[index]} ${stateSize[index]}"
        getTotalWadding(id, index)
    }
    fun onCreateWadding(index: Int){
        val id = "${statePart[index]} ${stateSize[index]}"
        wadding.id = id
        wadding.totalWadding = stateQuantity[index]
        createWadding(wadding)
    }
    fun onUpdateWadding(index: Int){
        val id = "${statePart[index]} ${stateSize[index]}"
        wadding.id = id
        wadding.totalWadding = (stateQuantity[index].toInt() + stateWaddingInBBDD[index].toInt()).toString()
        updateWadding(wadding)
    }
    fun onAddDateWadding(index: Int){
        val id = "${statePart[index]} ${stateSize[index]}"
        wadding.id = id
        addDateWadding("${stateQuantity[index]},$inputDate,Input",wadding)
    }
    private fun addDateRollWadding(newDate: String, rollWadding: RollWadding) = viewModelScope.launch {
        addDateRollWaddingResponse = Response.Loading
        val result = rollWaddingUseCases.addDateRollWadding(newDate,rollWadding)
        addDateRollWaddingResponse = result
    }

    private fun enabledInputWaddingButton(){
        isEnabledInputWaddingButton =
            isPartValid &&
            isSizeValid &&
            isQuantityValid
    }

    fun rollWadding150Output(){
        var totalRollWadding = 0
        when (stateQuantity.map{it.toInt()}.sum()){
            in 0..3800 -> {
                totalRollWadding = 1
            }
            in 4700..7400 ->{
                totalRollWadding = 2
            }
            in 7401..11000 ->{
                totalRollWadding = 3
            }
            in 11001..14600 ->{
                totalRollWadding = 4
            }
        }
        rollWadding.id = "150 CM"
        rollWadding.totalRollWadding = "${listTotalInBBDD.toDouble() - totalRollWadding.toDouble()}"
        rollWadding.totalMetersRollWadding = "${listTotalInBBDD.toDouble()*100 - (totalRollWadding.toDouble()*100)}"
        updateRollWadding(rollWadding)
        addDateRollWadding("${totalRollWadding},${date.actualDate},Output",rollWadding)

    }

    fun onPartInput(index: Int, part: String) {
        statePart[index] = part
    }
    fun onSizeInput(index: Int, size: String) {
        stateSize[index] = size
    }
    fun onQuantityInput(index: Int, quantity: String) {
        stateQuantity[index] = quantity
    }
    fun addTextFieldPart() {
        statePart.add("")
        partErrMsg.add("*")
        listPartValidate.add(false)
    }
    fun addTextFieldSize() {
        stateSize.add("")
        sizeErrMsg.add("*")
        listSizeValidate.add(false)

    }
    fun addTextFieldQuantity() {
        stateQuantity.add("")
        quantityErrMsg.add("*")
        listQuantityValidate.add(false)
    }
    fun removeTextFieldPart() {
        statePart.removeLast()
        listPartValidate.removeLast()
        partErrMsg.removeLast()
    }
    fun removeTextFieldSize() {
        stateSize.removeLast()
        listSizeValidate.removeLast()
        sizeErrMsg.removeLast()
    }
    fun removeTextFieldQuantity() {
        stateQuantity.removeLast()
        listQuantityValidate.removeLast()
        quantityErrMsg.removeLast()
    }

    fun addInputWaddingRow(){
        addTextFieldPart()
        addTextFieldSize()
        addTextFieldQuantity()
        stateWaddingInBBDD.add("0")
    }

    fun removeInputWaddingRow(){
        removeTextFieldPart()
        removeTextFieldSize()
        removeTextFieldQuantity()
        stateWaddingInBBDD.removeLast()
    }

    fun validatePart(index: Int){
        if(statePart[index].isNotEmpty()){
            listPartValidate[index] =  true
            partErrMsg[index] = ""
        }else{
            listPartValidate[index] =  false
            partErrMsg[index] = "*"
        }
        enabledInputWaddingButton()
    }

    fun validateAllPart(){
        isPartValid = listPartValidate.all{it}
        enabledInputWaddingButton()
    }

    fun validateSize(index: Int){
        if(stateSize[index].isNotEmpty()){
            listSizeValidate[index] =  true
            sizeErrMsg[index] = ""
        }else{
            listSizeValidate[index] =  false
            sizeErrMsg[index] = "*"
        }
        enabledInputWaddingButton()
    }

    fun validateAllSize(){
        isSizeValid = listSizeValidate.all{it}
        enabledInputWaddingButton()
    }

    fun validateQuantity(index: Int){
        if(stateQuantity[index].isNotEmpty()){
            listQuantityValidate[index] =  true
            quantityErrMsg[index] = ""
        }else{
            listQuantityValidate[index] =  false
            quantityErrMsg[index] = "*"
        }
        enabledInputWaddingButton()
    }

    fun validateAllQuantity(){
        isQuantityValid = listQuantityValidate.all{it}
        enabledInputWaddingButton()
    }

}