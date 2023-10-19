package com.portosdeveloper.protecadminapp.presentation.screens.InputReflectiveScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Reflective
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.ReflectiveUseCases
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputReflectiveViewModel @Inject constructor(
    private val reflectiveUseCases: ReflectiveUseCases
): ViewModel() {

    val colorList = listOf(
        "Azul",
        "Blanco",
        "Gris",
        "Rosado",
        "Morado",
        "Cafe",
        "Azul Oscuro",
        "Celeste",
        "Amarillo",
        "Rojo",
        "Negro",
        "Verde",
        "Verde Claro",
        "Verde Oscuro",
        "Azul Rey",
        "Verde Cali",
        "Verde Militar",
        "Purpura",
        "Naranja"
    )

    val confirmList = listOf(
        "Si",
        "No"
    )

    //COLOR
    val stateColor = mutableStateListOf<String>()
    val listColorValidate = mutableStateListOf<Boolean>()
    val colorErrMsg = mutableStateListOf<String>()
    var isColorValid by mutableStateOf(false)

    //QUANTITY
    val stateQuantity = mutableStateListOf<String>()
    val listQuantityValidate = mutableStateListOf<Boolean>()
    val quantityErrMsg = mutableStateListOf<String>()
    var isQuantityValid by mutableStateOf(false)

    //COMBINED
    val stateCombined = mutableStateListOf<String>()
    val listCombinedValidate = mutableStateListOf<Boolean>()
    val combinedErrMsg = mutableStateListOf<String>()
    var isCombinedValid by mutableStateOf(false)

    //REINFORCED
    val stateReinforced = mutableStateListOf<String>()
    val listReinforcedValidate = mutableStateListOf<Boolean>()
    val reinforcedErrMsg = mutableStateListOf<String>()
    var isReinforcedValid by mutableStateOf(false)

    //THREEM
    val stateThreeM = mutableStateListOf<String>()
    val listThreeMValidate = mutableStateListOf<Boolean>()
    val threeMErrMsg = mutableStateListOf<String>()
    var isThreeMValid by mutableStateOf(false)

    //BUTTON
    var isEnabledInputReflectiveButton by mutableStateOf(false)

    // DATE
    private var inputDate by mutableStateOf("")

    var reflective = Reflective()
    var date = ActualDate()

    // INPUT BB DD
    var createReflectiveResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateReflectiveResponse by mutableStateOf<Response<Boolean>?>(null)
    var addDateReflectiveResponse by mutableStateOf<Response<Boolean>?>(null)
    var addTotalReflectiveDayResponse by mutableStateOf<Response<Boolean>?>(null)
    val stateReflectiveInBBDD = mutableStateListOf<String>()

    init {
        inputDate = date.actualDate
    }

    private fun createReflective(reflective: Reflective) = viewModelScope.launch {
        createReflectiveResponse = Response.Loading
        val result = reflectiveUseCases.createReflective(reflective)
        createReflectiveResponse = result
    }
    private fun updateReflective(reflective: Reflective) = viewModelScope.launch {
        updateReflectiveResponse = Response.Loading
        val result = reflectiveUseCases.updateReflective(reflective)
        updateReflectiveResponse = result
    }
    private fun addDateReflective(newDate: String, reflective: Reflective) = viewModelScope.launch {
        addDateReflectiveResponse = Response.Loading
        val result = reflectiveUseCases.addDateReflective(newDate,reflective)
        addDateReflectiveResponse = result
    }
    private fun addTotalReflectiveDay(totalWadding: String, reflective: Reflective) = viewModelScope.launch {
        addTotalReflectiveDayResponse = Response.Loading
        val result = reflectiveUseCases.addTotalReflectiveDay(totalWadding,reflective)
        addTotalReflectiveDayResponse = result
    }
    private fun getTotalReflective(id: String, index: Int) = viewModelScope.launch{
        val newValue = reflectiveUseCases.getTotalReflective(id)
        stateReflectiveInBBDD[index] = newValue
    }

    fun onGetTotalReflective(index: Int){
        val id = "${stateColor[index]},${stateCombined[index]},${stateReinforced[index]},${stateThreeM[index]}"
        getTotalReflective(id, index)
    }
    fun onCreateReflective(index: Int){
        val id = "${stateColor[index]},${stateCombined[index]},${stateReinforced[index]},${stateThreeM[index]}"
        reflective.id = id
        reflective.combined = stateCombined[index]
        reflective.reinforced = stateReinforced[index]
        reflective.threeM = stateThreeM[index]
        reflective.totalReflective = stateQuantity[index]
        createReflective(reflective)
    }
    fun onUpdateReflective(index: Int){
        val id = "${stateColor[index]},${stateCombined[index]},${stateReinforced[index]},${stateThreeM[index]}"
        reflective.id = id
        reflective.totalReflective = (stateQuantity[index].toInt() + stateReflectiveInBBDD[index].toInt()).toString()
        updateReflective(reflective)
    }
    fun onAddDateReflective(index: Int){
        val id = "${stateColor[index]},${stateCombined[index]},${stateReinforced[index]},${stateThreeM[index]}"
        reflective.id = id
        addDateReflective(inputDate,reflective)
    }
    fun onAddTotalReflectiveDay(index: Int){
        val id = "${stateColor[index]},${stateCombined[index]},${stateReinforced[index]},${stateThreeM[index]}"
        reflective.id = id
        addTotalReflectiveDay("${stateQuantity[index]},${date.actualHour}",reflective)
    }

    private fun enabledInputReflectiveButton(){
        isEnabledInputReflectiveButton =
            isColorValid &&
            isQuantityValid &&
            isCombinedValid &&
            isReinforcedValid &&
            isThreeMValid
    }

    fun onColorInput(index: Int, color: String) {
        stateColor[index] = color
    }
    fun onQuantityInput(index: Int, quantity: String) {
        stateQuantity[index] = quantity
    }
    fun onCombinedInput (index : Int, combined: String ){
        stateCombined[index] = combined
    }
    fun onReinforcedInput (index : Int, reinforced: String ){
        stateReinforced[index] = reinforced
    }
    fun onThreeMdInput (index : Int, threeM: String ){
        stateThreeM[index] = threeM
    }

    fun addTextFieldColor() {
        stateColor.add("")
        colorErrMsg.add("*")
        listColorValidate.add(false)
    }
    fun addTextFieldQuantity() {
        stateQuantity.add("")
        quantityErrMsg.add("*")
        listQuantityValidate.add(false)
    }
    fun addTextFieldCombined() {
        stateCombined.add("")
        combinedErrMsg.add("*")
        listCombinedValidate.add(false)
    }
    fun addTextFieldReinforced() {
        stateReinforced.add("")
        reinforcedErrMsg.add("*")
        listReinforcedValidate.add(false)
    }
    fun addTextFieldThreeM() {
        stateThreeM.add("")
        threeMErrMsg.add("*")
        listThreeMValidate.add(false)
    }
    fun addNewRow(){
        addTextFieldColor()
        addTextFieldQuantity()
        addTextFieldCombined()
        addTextFieldReinforced()
        addTextFieldThreeM()
        stateReflectiveInBBDD.add("0")

    }

    fun removeTextFieldColor() {
        stateColor.removeLast()
        listColorValidate.removeLast()
        colorErrMsg.removeLast()
    }
    fun removeTextFieldQuantity() {
        stateQuantity.removeLast()
        listQuantityValidate.removeLast()
        quantityErrMsg.removeLast()
    }
    fun removeTextFieldCombined() {
        stateCombined.removeLast()
        listCombinedValidate.removeLast()
        combinedErrMsg.removeLast()
    }
    fun removeTextFieldReinforced() {
        stateReinforced.removeLast()
        listReinforcedValidate.removeLast()
        reinforcedErrMsg.removeLast()
    }
    fun removeTextFieldThreeM() {
        stateThreeM.removeLast()
        listThreeMValidate.removeLast()
        threeMErrMsg.removeLast()
    }
    fun removeLastRow(){
        removeTextFieldColor()
        removeTextFieldQuantity()
        removeTextFieldCombined()
        removeTextFieldReinforced()
        removeTextFieldThreeM()
        stateReflectiveInBBDD.removeLast()

    }

    fun validateColor(index: Int){
        if(stateColor[index].isNotEmpty()){
            listColorValidate[index] =  true
            colorErrMsg[index] = ""
        }else{
            listColorValidate[index] =  false
            colorErrMsg[index] = "*"
        }
        enabledInputReflectiveButton()
    }

    fun validateAllColor(){
        isColorValid = listColorValidate.all{it}
        enabledInputReflectiveButton()
    }

    fun validateCombined(index: Int){
        if(stateCombined[index].isNotEmpty()){
            listCombinedValidate[index] =  true
            combinedErrMsg[index] = ""
        }else{
            listCombinedValidate[index] =  false
            combinedErrMsg[index] = "*"
        }
        enabledInputReflectiveButton()
    }

    fun validateAllCombined(){
        isCombinedValid = listCombinedValidate.all{it}
        enabledInputReflectiveButton()
    }

    fun validateReinforced(index: Int){
        if(stateReinforced[index].isNotEmpty()){
            listReinforcedValidate[index] =  true
            reinforcedErrMsg[index] = ""
        }else{
            listReinforcedValidate[index] =  false
            reinforcedErrMsg[index] = "*"
        }
        enabledInputReflectiveButton()
    }

    fun validateAllReinforced(){
        isReinforcedValid = listReinforcedValidate.all{it}
        enabledInputReflectiveButton()
    }

    fun validateThreeM(index: Int){
        if(stateThreeM[index].isNotEmpty()){
            listThreeMValidate[index] =  true
            threeMErrMsg[index] = ""
        }else{
            listThreeMValidate[index] =  false
            threeMErrMsg[index] = "*"
        }
        enabledInputReflectiveButton()
    }

    fun validateAllThreeM(){
        isThreeMValid = listThreeMValidate.all{it}
        enabledInputReflectiveButton()
    }

    fun validateQuantity(index: Int){
        if(stateQuantity[index].isNotEmpty()){
            listQuantityValidate[index] =  true
            quantityErrMsg[index] = ""
        }else{
            listQuantityValidate[index] =  false
            quantityErrMsg[index] = "*"
        }
        enabledInputReflectiveButton()
    }

    fun validateAllQuantity(){
        isQuantityValid = listQuantityValidate.all{it}
        enabledInputReflectiveButton()
    }


}