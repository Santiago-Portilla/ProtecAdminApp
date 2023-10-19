package com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Cloth
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.TotalCloth
import com.portosdeveloper.protecadminapp.domain.use_cases.cloth.ClothUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.utils.UtilsUseCases
import com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.components.InputClothState
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class InputClothViewModel @Inject constructor(
    private val clothUseCases: ClothUseCases,
    private val utilsUseCases: UtilsUseCases
    ) : ViewModel() {

    // STATE FORM
    var state by mutableStateOf(InputClothState())

    //CLOTH
    var isClothValid by mutableStateOf(false)
        private set
    var clothErrMsg by mutableStateOf("Seleccione una Tela")
        private set

    //COLOR
    var isColorValid by mutableStateOf(false)
        private set
    var colorErrMsg by mutableStateOf("Seleccione un Color")
        private set

    //BILL
    var isBillValid by mutableStateOf(false)
        private set
    var billErrMsg by mutableStateOf("Ingrese una Factura")
        private set

    //CONSECUTIVE
    val stateConsecutive = mutableStateListOf<String>()
    val stateConsecutiveValidate = mutableStateListOf<Boolean>()
    val consecutiveErrMsg = mutableStateListOf<String>()
    var isConsecutiveValid by mutableStateOf(false)


    //ID
    val stateIdConsecutive = mutableStateListOf<String>()
    val stateIdConsecutiveValidate = mutableStateListOf<Boolean>()
    val idConsecutiveErrMsg = mutableStateListOf<String>()
    var isIdConsecutiveValid by mutableStateOf(false)


    //MEASURE
    val stateMeasure = mutableStateListOf<String>()
    val stateMeasureValidate = mutableStateListOf<Boolean>()
    val measureErrMsg = mutableStateListOf<String>()
    var isMeasureValid by mutableStateOf(false)


    var clothResponse by mutableStateOf<Response<Boolean>?>(null)

    var totalClothResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateTotalClothResponse by mutableStateOf<Response<Boolean>?>(null)
    var addDateResponse by mutableStateOf<Response<Boolean>?>(null)

    var cloth = Cloth()
    var totalCloth = TotalCloth()
    var newTotalCloth = TotalCloth()
    var totalClothData by mutableStateOf(TotalCloth())
    var inputDate = ActualDate()

    //BUTTON
    var isEnabledInputClothButton by mutableStateOf(false)

    init {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        state = state.copy(inputDate = dateFormat.format(calendar.time))
        getClothList()
        getColorList()
    }
    private fun getClothList() = viewModelScope.launch {
        utilsUseCases.getList("Cloth","cloth").collect(){
            state = state.copy(clothList = it)
        }
    }
    private fun getColorList() = viewModelScope.launch {
        utilsUseCases.getList("Color","color").collect(){
            state = state.copy(colorList = it)
        }
    }

    private fun createCloth(cloth : Cloth) = viewModelScope.launch {
        clothResponse = Response.Loading
        val result = clothUseCases.createCloth(cloth)
        clothResponse = result
    }

    fun getTotalMeasureById() = viewModelScope.launch {
        clothUseCases.getTotalMeasureById("${state.cloth} ${state.color}").collect(){
            totalClothData = it
        }
    }
    private fun updateTotalCloth(totalCloth: TotalCloth) = viewModelScope.launch {
        updateTotalClothResponse = Response.Loading
        val result = clothUseCases.updateTotalCloth(totalCloth)
        updateTotalClothResponse = result
    }

    private fun createTotalCloth(totalCloth: TotalCloth) = viewModelScope.launch {
        totalClothResponse = Response.Loading
        val result = clothUseCases.createTotalCloth(totalCloth)
        totalClothResponse = result
    }
    private fun addDateTotalCloth(newDate: String, totalCloth: TotalCloth) = viewModelScope.launch {
        addDateResponse = Response.Loading
        val result = clothUseCases.addDateTotalCloth(newDate,totalCloth)
        addDateResponse = result
    }

    fun onCreateCloth(index: Int){
        cloth.id = "${stateConsecutive[index]} - ${stateIdConsecutive[index]} - ${state.cloth} - ${state.color}"
        cloth.cloth = state.cloth
        cloth.color = state.color
        cloth.bill = state.bill
        cloth.measure = stateMeasure[index]
        cloth.inputDate = state.inputDate

        createCloth(cloth)
    }


    fun onCreateTotalCloth(){
        totalCloth.id = "${state.cloth} ${state.color}"
        totalCloth.totalMeasure = state.totalMeasure
        createTotalCloth(totalCloth)
    }

    fun onUpdateTotalCloth(){
        newTotalCloth.id = "${state.cloth} ${state.color}"
        val totalMeasureUpdated = state.totalMeasure.toDouble() + totalClothData.totalMeasure.toDouble()
        newTotalCloth.totalMeasure = totalMeasureUpdated.toString()
        updateTotalCloth(newTotalCloth)
    }
    fun onAddDateTotalCloth(){
        val id = "${state.cloth} ${state.color}"
        totalCloth.id = id
        addDateTotalCloth("${state.totalMeasure},${inputDate.actualDate},Input",totalCloth)
    }

     private fun enabledInputClothButton(){
        isEnabledInputClothButton =
            isConsecutiveValid &&
            isClothValid &&
            isColorValid &&
            isBillValid &&
            isIdConsecutiveValid &&
            isMeasureValid
    }


    fun onClothInput(cloth: String){
        state = state.copy(cloth = cloth)
    }
    fun onColorInput(color: String){
        state = state.copy(color = color)
    }
    fun onBillsInput(bill: String){
        state = state.copy(bill = bill)
    }
    fun onConsecutiveInput(rowIndex: Int, consecutive: String) {
        stateConsecutive[rowIndex] = consecutive
    }
    fun onIdConsecutiveInput(rowIndex: Int, idConsecutive: String) {
        stateIdConsecutive[rowIndex] = idConsecutive
    }
    fun onMeasureInput(rowIndex: Int, measure: String) {
        stateMeasure[rowIndex] = measure
    }
    fun onTotalMeasureInput(){
        state = state.copy(totalMeasure = stateMeasure.sumOf { it.toDoubleOrNull() ?: 0.0 }.toString())
    }
    fun addTextFieldConsecutive() {
        stateConsecutive.add("")
        consecutiveErrMsg.add("*")
        stateConsecutiveValidate.add(false)
    }
    fun addTextFieldIdConsecutive() {
        stateIdConsecutive.add("")
        idConsecutiveErrMsg.add("*")
        stateIdConsecutiveValidate.add(false)
    }
    fun addTextFieldMeasure() {
        stateMeasure.add("")
        measureErrMsg.add("*")
        stateMeasureValidate.add(false)
    }

    fun removeTextFieldConsecutive() {
        stateConsecutive.removeLast()
        consecutiveErrMsg.removeLast()
        stateConsecutiveValidate.removeLast()
    }
    fun removeTextFieldIdConsecutive() {
        stateIdConsecutive.removeLast()
        idConsecutiveErrMsg.removeLast()
        stateIdConsecutiveValidate.removeLast()
    }
    fun removeTextFieldMeasure() {
        stateMeasure.removeLast()
        measureErrMsg.removeLast()
        stateMeasureValidate.removeLast()
    }

    fun validateConsecutive(index: Int){
        if(stateConsecutive[index].isNotEmpty()){
            stateConsecutiveValidate[index] =  true
            consecutiveErrMsg[index] = ""
        }else{
            stateConsecutiveValidate[index] =  false
            consecutiveErrMsg[index] = "*"
        }
        enabledInputClothButton()
    }

    fun validateAllConsecutive(){
        isConsecutiveValid = stateConsecutiveValidate.all{it}
        enabledInputClothButton()
    }

    fun validateIdConsecutive(index: Int){
        if(stateIdConsecutive[index].isNotEmpty()){
            stateIdConsecutiveValidate[index] = true
            idConsecutiveErrMsg[index] = ""
        }else{
            stateIdConsecutiveValidate[index] = false
            idConsecutiveErrMsg[index] = "*"
        }
        enabledInputClothButton()
    }

    fun validateAllIdConsecutive(){
        isIdConsecutiveValid = stateIdConsecutiveValidate.all{it}
        enabledInputClothButton()
    }

    fun validateMeasure(index: Int){
        if(stateMeasure[index].isNotEmpty()){
            stateMeasureValidate[index] = true
            measureErrMsg[index] = ""
        }else{
            stateMeasureValidate[index] = false
            measureErrMsg[index] = "*"
        }
        enabledInputClothButton()
    }

    fun validateAllMeasure(){
        isMeasureValid = stateMeasureValidate.all{it}
        enabledInputClothButton()
    }

    fun validateCloth(){
        if(state.cloth.isNotEmpty()){
            isClothValid = true
            clothErrMsg = ""
        }else{
            isClothValid = false
            clothErrMsg = "Porfavor seleccione una Tela"
        }
        enabledInputClothButton()

    }

    fun validateColor(){
        if(state.color.isNotEmpty()){
            isColorValid = true
            colorErrMsg = ""
        }else{
            isColorValid = false
            colorErrMsg = "Por favor ingrese un Color"
        }
        enabledInputClothButton()

    }

    fun validateBill(){
        if(state.bill.isNotEmpty()){
            isBillValid = true
            billErrMsg = ""
        }else{
            isBillValid = false
            billErrMsg = "Por favor ingrese # de Factura"
        }
        enabledInputClothButton()

    }

}


