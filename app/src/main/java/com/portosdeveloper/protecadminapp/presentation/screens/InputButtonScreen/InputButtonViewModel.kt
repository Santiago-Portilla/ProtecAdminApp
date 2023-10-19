package com.portosdeveloper.protecadminapp.presentation.screens.InputButtonScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Button
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.use_cases.utils.UtilsUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.ButtonUseCases
import com.portosdeveloper.protecadminapp.presentation.screens.InputButtonScreen.components.InputButtonState
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputButtonViewModel @Inject constructor(
    private val buttonUseCases: ButtonUseCases,
    private val utilsUseCases: UtilsUseCases
) : ViewModel() {

    var state by mutableStateOf(InputButtonState())

    val colorList = listOf<String>(
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

    val linesNumberList = listOf<String>(
        "14",
        "18",
        "24"
    )


    //COLOR
    val stateColor = mutableStateListOf<String>()
    val listColorValidate = mutableStateListOf<Boolean>()
    val colorErrMsg = mutableStateListOf<String>()
    var isColorValid by mutableStateOf(false)

    //LINES
    val stateLines = mutableStateListOf<String>()
    val listLinesValidate = mutableStateListOf<Boolean>()
    val linesErrMsg = mutableStateListOf<String>()
    var isLinesValid by mutableStateOf(false)

    //QUANTITY
    val stateQuantity = mutableStateListOf<String>()
    val listQuantityValidate = mutableStateListOf<Boolean>()
    val quantityErrMsg = mutableStateListOf<String>()
    var isQuantityValid by mutableStateOf(false)

    //BUTTON
    var isEnabledInputButtonButton by mutableStateOf(false)

    // DATE
    private var inputDate by mutableStateOf("")

    var button = Button()
    var date = ActualDate()

    // INPUT BB DD
    var createButtonResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateButtonResponse by mutableStateOf<Response<Boolean>?>(null)
    var addTotalButtonDayResponse by mutableStateOf<Response<Boolean>?>(null)
    val stateButtonInBBDD = mutableStateListOf<String>()
    val stateGrandButtonInBBDD = mutableStateListOf<String>()

    init {
        getColorList()
        getNumberLinesList()
        inputDate = date.actualDate
    }
    private fun getColorList() = viewModelScope.launch {
        utilsUseCases.getList("Color","color").collect(){
            state = state.copy(colorList = it)
        }
    }
    private fun getNumberLinesList() = viewModelScope.launch {
        utilsUseCases.getList("ButtonLines","numberLines").collect(){
            state = state.copy(numberLinesList = it)
        }
    }

    private fun createButton(button: Button) = viewModelScope.launch {
        createButtonResponse = Response.Loading
        val result = buttonUseCases.createButton(button)
        createButtonResponse = result
    }
    private fun updateButton(button: Button) = viewModelScope.launch {
        updateButtonResponse = Response.Loading
        val result = buttonUseCases.updateButton(button)
        updateButtonResponse = result
    }

    private fun addTotalButtonDay(totalButton: String, button: Button) = viewModelScope.launch {
        addTotalButtonDayResponse = Response.Loading
        val result = buttonUseCases.addTotalButtonDay(totalButton,button)
        addTotalButtonDayResponse = result
    }
    private fun getTotalButton(id: String, index: Int) = viewModelScope.launch{
        val newValue = buttonUseCases.getTotalButton(id,"totalButton")
        stateButtonInBBDD[index] = newValue
    }
    private fun getTotalGrandButton(id: String, index: Int) = viewModelScope.launch{
        val newValue = buttonUseCases.getTotalButton(id,"totalGrandButton")
        stateGrandButtonInBBDD[index] = newValue
    }

    fun onGetTotalButton(index: Int){
        val id = "${stateLines[index]} Lineas ${stateColor[index]}"
        getTotalButton(id, index)
        getTotalGrandButton(id, index)
    }
    fun onCreateButton(index: Int){
        val id = "${stateLines[index]} Lineas ${stateColor[index]}"
        button.id = id
        button.lines = stateLines[index]
        button.color = stateColor[index]
        button.totalButton = stateQuantity[index]
        button.totalGrandButton = "${stateQuantity[index].toInt()*oneGrandQuantity(index)}"
        createButton(button)
    }
    fun onUpdateButton(index: Int){
        val id = "${stateLines[index]} Lineas ${stateColor[index]}"
        button.id = id
        button.totalButton = (stateQuantity[index].toInt() + stateButtonInBBDD[index].toInt()).toString()
        button.totalGrandButton = "${stateGrandButtonInBBDD[index].toInt() + stateQuantity[index].toInt()*oneGrandQuantity(index)}"
        updateButton(button)
    }

    fun onAddTotalButtonDay(index: Int){
        val id = "${stateLines[index]} Lineas ${stateColor[index]}"
        button.id = id
        addTotalButtonDay("${stateQuantity[index]},${date.actualHour},Input",button)
    }
    private fun oneGrandQuantity(index : Int): Int{
        val oneGrandQuantity = when(stateLines[index]){
            "24"->{
                864
            }
            else->{
                1728
            }
        }
        return oneGrandQuantity
    }

    private fun enabledInputButtonButton(){
        isEnabledInputButtonButton =
            isColorValid &&
            isLinesValid &&
            isQuantityValid
    }

    fun onColorInput(index: Int, color: String) {
        stateColor[index] = color
    }
    fun onLinesInput(index: Int, function: String) {
        stateLines[index] = function
    }
    fun onQuantityInput(index: Int, quantity: String) {
        stateQuantity[index] = quantity
    }

    fun addTextFieldColor() {
        stateColor.add("")
        colorErrMsg.add("*")
        listColorValidate.add(false)
    }
    fun addTextFieldLines() {
        stateLines.add("")
        linesErrMsg.add("*")
        listLinesValidate.add(false)
    }
    fun addTextFieldQuantity() {
        stateQuantity.add("")
        quantityErrMsg.add("*")
        listQuantityValidate.add(false)
    }

    fun removeTextFieldColor() {
        stateColor.removeLast()
        listColorValidate.removeLast()
        colorErrMsg.removeLast()
    }
    fun removeTextFieldLines() {
        stateLines.removeLast()
        listLinesValidate.removeLast()
        linesErrMsg.removeLast()
    }
    fun removeTextFieldQuantity() {
        stateQuantity.removeLast()
        listQuantityValidate.removeLast()
        quantityErrMsg.removeLast()
    }

    fun addInputButtonRow(){
        addTextFieldColor()
        addTextFieldLines()
        addTextFieldQuantity()
        stateButtonInBBDD.add("0")
        stateGrandButtonInBBDD.add("0")
    }

    fun removeInputButtonRow(){
        removeTextFieldColor()
        removeTextFieldLines()
        removeTextFieldQuantity()
        stateButtonInBBDD.removeLast()
        stateGrandButtonInBBDD.removeLast()
    }

    fun validateLines(index: Int){
        if(stateLines[index].isNotEmpty()){
            listLinesValidate[index] =  true
            linesErrMsg[index] = ""
        }else{
            listLinesValidate[index] =  false
            linesErrMsg[index] = "*"
        }
        enabledInputButtonButton()
    }

    fun validateAllLines(){
        isLinesValid = listLinesValidate.all{it}
        enabledInputButtonButton()
    }

    fun validateColor(index: Int){
        if(stateColor[index].isNotEmpty()){
            listColorValidate[index] =  true
            colorErrMsg[index] = ""
        }else{
            listColorValidate[index] =  false
            colorErrMsg[index] = "*"
        }
        enabledInputButtonButton()
    }

    fun validateAllColor(){
        isColorValid = listColorValidate.all{it}
        enabledInputButtonButton()
    }

    fun validateQuantity(index: Int){
        if(stateQuantity[index].isNotEmpty()){
            listQuantityValidate[index] =  true
            quantityErrMsg[index] = ""
        }else{
            listQuantityValidate[index] =  false
            quantityErrMsg[index] = "*"
        }
        enabledInputButtonButton()
    }

    fun validateAllQuantity(){
        isQuantityValid = listQuantityValidate.all{it}
        enabledInputButtonButton()
    }

}