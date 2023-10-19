package com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Packing
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.RollWadding
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.PackingUseCases
import com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen.components.InputPackingState
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputPackingViewModel @Inject constructor(
    private val packingUseCases: PackingUseCases
) : ViewModel(){

    // STATE FORM
    var state by mutableStateOf(InputPackingState())

    //PAPER BOARD
    var isPaperBoardValid by mutableStateOf(false)
        private set

    //BAG
    var isBagValid by mutableStateOf(false)
        private set

    //BIG PIN
    var isBigPinValid by mutableStateOf(false)
        private set

    //SMALL PIN
    var isSmallPinValid by mutableStateOf(false)
        private set

    //BUTTON
    var isEnabledInputPackingButton = false

    private val idPaperBoard = "Carton"
    private val idBag = "Bolsa"
    private val idBigPin = "Alfiler Grande"
    private val idSmallPin = "Alfiler Pequeño "

    //INPUT BBDD
    var createPackingResponse by mutableStateOf<Response<Boolean>?>(null)
    var updatePackingResponse by mutableStateOf<Response<Boolean>?>(null)
    var addDatePackingResponse by mutableStateOf<Response<Boolean>?>(null)
    var addTotalPackingDayResponse by mutableStateOf<Response<Boolean>?>(null)
    var listTotalInBBDD = mutableStateListOf("","","","")

    var packing = Packing()
    var date = ActualDate()

    init {
        state = state.copy(inputDate = date.actualDate)
    }

    private fun createPacking(packing: Packing) = viewModelScope.launch {
        createPackingResponse = Response.Loading
        val result = packingUseCases.createPacking(packing)
        createPackingResponse = result
    }
    private fun updatePacking(packing: Packing) = viewModelScope.launch {
        updatePackingResponse = Response.Loading
        val result = packingUseCases.updatePacking(packing)
        updatePackingResponse = result
    }
    private fun addDatePacking(newDate: String, packing: Packing) = viewModelScope.launch {
        addDatePackingResponse = Response.Loading
        val result = packingUseCases.addDatePacking(newDate,packing)
        addDatePackingResponse = result
    }
    private fun addTotalPackingDay(totalPacking: String, packing: Packing) = viewModelScope.launch {
        addTotalPackingDayResponse = Response.Loading
        val result = packingUseCases.addTotalPackingDay(totalPacking,packing)
        addTotalPackingDayResponse = result
    }
    private fun getTotalPacking(id: String, index: Int) = viewModelScope.launch{
        listTotalInBBDD[index] = packingUseCases.getTotalPacking(id)
    }

    fun onInputToBBDD(){
        listTotalInBBDD.forEachIndexed { index, s ->
            when(index){
                0 ->{
                    if(state.paperBoard != ""){
                        if(listTotalInBBDD[0] != "0" && listTotalInBBDD[0] != ""){
                            packing.id = idPaperBoard
                            packing.totalPacking = (state.paperBoard.toInt() + listTotalInBBDD[0].toInt()).toString()
                            updatePacking(packing)
                            addDatePacking(state.inputDate,packing)
                            addTotalPackingDay("${state.paperBoard},${date.actualHour}",packing)
                        }else{
                            packing.id = idPaperBoard
                            packing.totalPacking = state.paperBoard
                            createPacking(packing)
                            addDatePacking(state.inputDate,packing)
                            addTotalPackingDay("${state.paperBoard},${date.actualHour}",packing)
                        }
                    }
                }
                1 ->{
                    if(state.bag != ""){
                        if(listTotalInBBDD[1] != "0" && listTotalInBBDD[1] != ""){
                            packing.id = idBag
                            packing.totalPacking = (state.bag.toInt() + listTotalInBBDD[1].toInt()).toString()
                            updatePacking(packing)
                            addDatePacking(state.inputDate,packing)
                            addTotalPackingDay("${state.bag},${date.actualHour}",packing)
                        }else{
                            packing.id = idBag
                            packing.totalPacking= state.bag
                            createPacking(packing)
                            addDatePacking(state.inputDate,packing)
                            addTotalPackingDay("${state.bag},${date.actualHour}",packing)
                        }
                    }
                }
                2 ->{
                    if(state.bigPin != ""){
                        if(listTotalInBBDD[2] != "0" && listTotalInBBDD[2] != ""){
                            packing.id = idBigPin
                            packing.totalPacking = (state.bigPin.toInt() + listTotalInBBDD[2].toInt()).toString()
                            updatePacking(packing)
                            addDatePacking(state.inputDate,packing)
                            addTotalPackingDay("${state.bigPin},${date.actualHour}",packing)
                        }else{
                            packing.id = idBigPin
                            packing.totalPacking = state.bigPin
                            createPacking(packing)
                            addDatePacking(state.inputDate,packing)
                            addTotalPackingDay("${state.bigPin},${date.actualHour}",packing)
                        }
                    }

                }
                3 ->{
                    if(state.smallPin != ""){
                        if(listTotalInBBDD[3] != "0" && listTotalInBBDD[3] != ""){
                            packing.id = idSmallPin
                            packing.totalPacking = (state.smallPin.toInt() + listTotalInBBDD[3].toInt()).toString()
                            updatePacking(packing)
                            addDatePacking(state.inputDate,packing)
                            addTotalPackingDay("${state.smallPin},${date.actualHour}",packing)
                        }else{
                            packing.id = idSmallPin
                            packing.totalPacking = state.smallPin
                            createPacking(packing)
                            addDatePacking(state.inputDate,packing)
                            addTotalPackingDay("${state.smallPin},${date.actualHour}",packing)
                        }
                    }
                }
            }

        }

    }

    fun onPaperBoardInput(paperBoard : String){
        state = state.copy(paperBoard = paperBoard)
        if(state.paperBoard != ""){
            getTotalPacking(idPaperBoard,0)
        }
    }
    fun onBagInput(bag : String){
        state = state.copy(bag = bag)
        if(state.bag != ""){
            getTotalPacking(idBag,1)
        }
    }
    fun onBigPinInput(bigPin : String){
        state = state.copy(bigPin = bigPin)
        if(state.bigPin != ""){
            getTotalPacking(idBigPin,2)
        }
    }
    fun onSmallPinInput(smallPin : String){
        state = state.copy(smallPin = smallPin)
        if(state.smallPin != ""){
            getTotalPacking(idSmallPin,3)
        }
    }

    fun enabledPackingButton(){
        isEnabledInputPackingButton =
            isPaperBoardValid ||
            isBagValid ||
            isBigPinValid ||
            isSmallPinValid
    }

    fun validatePaperBoard(){
        isPaperBoardValid = state.paperBoard.isNotEmpty()
        enabledPackingButton()
    }
    fun validateBag(){
        isBagValid = state.bag.isNotEmpty()
        enabledPackingButton()
    }
    fun validateBigPin(){
        isBigPinValid = state.bigPin.isNotEmpty()
        enabledPackingButton()
    }
    fun validateSmallPin(){
        isSmallPinValid = state.smallPin.isNotEmpty()
        enabledPackingButton()
    }

}