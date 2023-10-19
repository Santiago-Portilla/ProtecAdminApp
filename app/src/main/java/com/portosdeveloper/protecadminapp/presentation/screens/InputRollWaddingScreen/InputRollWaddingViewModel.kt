package com.portosdeveloper.protecadminapp.presentation.screens.InputRollWaddingScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.RollWadding
import com.portosdeveloper.protecadminapp.domain.model.Wadding
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.RollWaddingUseCases
import com.portosdeveloper.protecadminapp.presentation.screens.InputRollWaddingScreen.components.InputRollWaddingState
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class InputRollWaddingViewModel @Inject constructor(
    private val rollWaddingUseCases: RollWaddingUseCases
) : ViewModel(){

    // STATE FORM
    var state by mutableStateOf(InputRollWaddingState())

    //2 CM
    var isTwoCmValid by mutableStateOf(false)
        private set

    //3 CM
    var isThreeCmValid by mutableStateOf(false)
        private set

    //3.5 CM
    var isThreeHalfCmValid by mutableStateOf(false)
        private set

    //3 CM
    var isOneHundredFiftyCmValid by mutableStateOf(false)
        private set

    //BUTTON
    var isEnabledInputRollWaddingButton = false

    private val id2CM = "2 CM"
    private val id3CM = "3 CM"
    private val id35CM = "3.5 CM"
    private val id150CM = "150 CM"

    //INPUT BBDD
    var createRollWaddingResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateRollWaddingResponse by mutableStateOf<Response<Boolean>?>(null)
    var addDateRollWaddingResponse by mutableStateOf<Response<Boolean>?>(null)
    var listTotalInBBDD = mutableStateListOf("","","","")
    var listTotalMetersInBBDD = mutableStateListOf("","","","")
    var list150Rolls = mutableStateListOf(0,0,0)

    var rollWadding = RollWadding()
    var date = ActualDate()

    init {
        state = state.copy(inputDate = date.actualDate)
        getTotalMetersRollWadding(id150CM,3)
        getTotalRollWadding(id150CM,3)
    }

    private fun createRollWadding(rollWadding: RollWadding) = viewModelScope.launch {
        createRollWaddingResponse = Response.Loading
        val result = rollWaddingUseCases.createRollWadding(rollWadding)
        createRollWaddingResponse = result
    }
    private fun updateRollWadding(rollWadding: RollWadding) = viewModelScope.launch {
        updateRollWaddingResponse = Response.Loading
        val result = rollWaddingUseCases.updateRollWadding(rollWadding)
        updateRollWaddingResponse = result
    }
    private fun addDateRollWadding(newDate: String, rollWadding: RollWadding) = viewModelScope.launch {
        addDateRollWaddingResponse = Response.Loading
        val result = rollWaddingUseCases.addDateRollWadding(newDate,rollWadding)
        addDateRollWaddingResponse = result
    }
    private fun getTotalRollWadding(id: String, index: Int) = viewModelScope.launch{
        listTotalInBBDD[index] = rollWaddingUseCases.getTotalRollWadding(id)

    }
    private fun getTotalMetersRollWadding(id: String, index: Int) = viewModelScope.launch{
        listTotalMetersInBBDD[index] = rollWaddingUseCases.getTotalMetersRollWadding(id)
    }

    fun onInputToBBDD(){
        listTotalInBBDD.forEachIndexed { index, s ->
            when(index){
                0 ->{
                    if(state.twoCm != ""){
                        if(listTotalInBBDD[0] != "0" && listTotalInBBDD[0] != ""){
                            rollWadding.id = id2CM
                            rollWadding.totalRollWadding = (state.twoCm.toDouble() + listTotalInBBDD[0].toDouble()).toString()
                            rollWadding.totalMetersRollWadding = ((state.twoCm.toDouble()*100.0) + listTotalMetersInBBDD[0].toDouble()).toString()
                            updateRollWadding(rollWadding)
                            addDateRollWadding("${state.twoCm},${state.inputDate},Input",rollWadding)
                        }else{
                            rollWadding.id = id2CM
                            rollWadding.totalRollWadding = state.twoCm.toDouble().toString()
                            rollWadding.totalMetersRollWadding = (state.twoCm.toDouble()*100.0).toString()
                            createRollWadding(rollWadding)
                            addDateRollWadding("${state.twoCm},${state.inputDate},Input",rollWadding)
                        }
                    }
                }
                1 ->{
                    if(state.threeCm != ""){
                        if(listTotalInBBDD[1] != "0" && listTotalInBBDD[1] != ""){
                            rollWadding.id = id3CM
                            rollWadding.totalRollWadding = (state.threeCm.toDouble() + listTotalInBBDD[1].toDouble()).toString()
                            rollWadding.totalMetersRollWadding = ((state.threeCm.toDouble()*100.0) + listTotalMetersInBBDD[1].toDouble()).toString()
                            updateRollWadding(rollWadding)
                            addDateRollWadding("${state.threeCm},${state.inputDate},Input",rollWadding)
                        }else{
                            rollWadding.id = id3CM
                            rollWadding.totalRollWadding = state.threeCm.toDouble().toString()
                            rollWadding.totalMetersRollWadding = (state.threeCm.toDouble()*100.0).toString()
                            createRollWadding(rollWadding)
                            addDateRollWadding("${state.threeCm},${state.inputDate},Input",rollWadding)
                        }
                    }
                }
                2 ->{
                    if(state.threeHalfCm != ""){
                        if(listTotalInBBDD[2] != "0" && listTotalInBBDD[2] != ""){
                            rollWadding.id = id35CM
                            rollWadding.totalRollWadding = (state.threeHalfCm.toDouble() + listTotalInBBDD[2].toDouble()).toString()
                            rollWadding.totalMetersRollWadding = (((state.threeHalfCm.toDouble()*100.0)) + listTotalMetersInBBDD[2].toDouble()).toString()
                            updateRollWadding(rollWadding)
                            addDateRollWadding("${state.threeHalfCm},${state.inputDate},Input",rollWadding)
                        }else{
                            rollWadding.id = id35CM
                            rollWadding.totalRollWadding = state.threeHalfCm.toDouble().toString()
                            rollWadding.totalMetersRollWadding = (state.threeHalfCm.toDouble()*100.0).toString()
                            createRollWadding(rollWadding)
                            addDateRollWadding(state.inputDate,rollWadding)
                            addDateRollWadding("${state.threeHalfCm},${state.inputDate},Input",rollWadding)
                        }
                    }

                }
                3 ->{
                    if(state.oneHundredFiftyCm != ""){
                        if(listTotalInBBDD[3] != "0" && listTotalInBBDD[3] != ""){
                            rollWadding.id = id150CM
                            rollWadding.totalRollWadding = (state.oneHundredFiftyCm.toDouble() + listTotalInBBDD[3].toDouble()).toString()
                            rollWadding.totalMetersRollWadding = ((state.oneHundredFiftyCm.toDouble()*100.0) + listTotalMetersInBBDD[3].toDouble()).toString()
                            updateRollWadding(rollWadding)
                            addDateRollWadding("${state.oneHundredFiftyCm},${state.inputDate},Input",rollWadding)
                        }else{
                            rollWadding.id = id150CM
                            rollWadding.totalRollWadding = state.oneHundredFiftyCm.toDouble().toString()
                            rollWadding.totalMetersRollWadding = (state.oneHundredFiftyCm.toDouble()*100.0).toString()
                            createRollWadding(rollWadding)
                            addDateRollWadding("${state.oneHundredFiftyCm},${state.inputDate},Input",rollWadding)
                        }
                    }
                }
            }

        }

    }
    fun onTwoCmInput(twoCm : String){
        state = state.copy(twoCm = twoCm)
        if(state.twoCm != ""){
            when(state.twoCm.toInt()){
                in 0..75 ->{
                    list150Rolls[0] = 1
                }
                in 80..150 ->{
                    list150Rolls[0] = 2
                }
                in 180..300 ->{
                    list150Rolls[0] = 3
                }
                in 400..600 ->{
                    list150Rolls[0] = 4
                }
            }
            getTotalRollWadding(id2CM,0)
            getTotalMetersRollWadding(id2CM,0)
        }
    }
    fun onThreeCmInput(threeCm : String){
        state = state.copy(threeCm = threeCm)
        if(state.threeCm != ""){
            when(state.threeCm.toInt()){
                in 0..50 ->{
                    list150Rolls[1] = 1
                }
                in 70..100 ->{
                    list150Rolls[1] = 2
                }
                in 150..200 ->{
                    list150Rolls[1] = 3
                }
                in 250..400 ->{
                    list150Rolls[1] = 4
                }
            }
            getTotalRollWadding(id3CM,1)
            getTotalMetersRollWadding(id3CM,1)
        }
    }
    fun onThreeHalfCmInput(threeHalfCm : String){
        state = state.copy(threeHalfCm = threeHalfCm)
        if(state.threeHalfCm != ""){
            when(state.threeHalfCm.toInt()){
                in 0..43 ->{
                    list150Rolls[2] = 1
                }
                in 60..86 ->{
                    list150Rolls[2] = 2
                }
                in 100..172 ->{
                    list150Rolls[2] = 3
                }
                in 200..244 ->{
                    list150Rolls[2] = 4
                }
            }
            getTotalRollWadding(id35CM,2)
            getTotalMetersRollWadding(id35CM,2)
        }
    }
    fun onOneHundredFiftyCmInput(oneHundredFiftyCm : String){
        state = state.copy(oneHundredFiftyCm = oneHundredFiftyCm)
    }
    fun updateRollWadding150Output(){
        rollWadding.id = id150CM
        rollWadding.totalRollWadding = "${listTotalInBBDD[3].toDouble() - list150Rolls.sum().toDouble()}"
        rollWadding.totalMetersRollWadding = "${(listTotalMetersInBBDD[3].toDouble()) - (list150Rolls.sum()*100.toDouble())}"
        updateRollWadding(rollWadding)
        addDateRollWadding("${list150Rolls.sum()},${state.inputDate},Output",rollWadding)
    }

    fun enabledRollsWaddingButton(){
        isEnabledInputRollWaddingButton =
            isTwoCmValid ||
            isThreeCmValid ||
            isThreeHalfCmValid ||
            isOneHundredFiftyCmValid
    }

    fun validateTwoCm(){
        isTwoCmValid = state.twoCm.isNotEmpty()
        enabledRollsWaddingButton()
    }
    fun validateThreeCm(){
        isThreeCmValid = state.threeCm.isNotEmpty()
        enabledRollsWaddingButton()
    }
    fun validateThreeHalfCm(){
        isThreeHalfCmValid = state.threeHalfCm.isNotEmpty()
        enabledRollsWaddingButton()
    }
    fun validateOneHundredFiftyCm(){
        isOneHundredFiftyCmValid = state.oneHundredFiftyCm.isNotEmpty()
        enabledRollsWaddingButton()
    }
}


