package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen

import androidx.compose.runtime.*
import com.portosdeveloper.protecadminapp.domain.model.Package
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.*
import com.portosdeveloper.protecadminapp.domain.use_cases._package.PackageUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.cloth.ClothUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.plotter.PlotterUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.shirt.ShirtUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.user_work_shop.UserWorkShopUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.utils.UtilsUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.WaddingUseCases
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.components.ProgramingLineState
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramingLineViewModel @Inject constructor(
    private val clothUseCases: ClothUseCases,
    private val plotterUseCases: PlotterUseCases,
    private val utilsUseCases: UtilsUseCases,
    private val packageUseCases: PackageUseCases,
    private val waddingUseCases: WaddingUseCases,
    private val userWorkShopUseCases: UserWorkShopUseCases,
    private val shirtUseCases: ShirtUseCases
): ViewModel() {
    var state by mutableStateOf(ProgramingLineState())

    var createPackageResponse by mutableStateOf<Response<Boolean>?>(null)
    var clothInBBDDOne = mutableStateListOf<Cloth>()
    var clothInBBDDTwo = mutableStateListOf<Cloth>()
    var clothInBBDDThree = mutableStateListOf<Cloth>()
    var clothSelected = mutableStateListOf<Cloth>()
    var clothMeasureSelectedOne = mutableStateListOf<Double>()
    var clothMeasureSelectedTwo = mutableStateListOf<Double>()
    var clothMeasureSelectedThree = mutableStateListOf<Double>()
    var appearColorOne by mutableStateOf<Boolean>(false)
    var appearColorTwo by mutableStateOf<Boolean>(false)
    var appearColorThree by mutableStateOf<Boolean>(false)
    var tableSimulate by mutableStateOf<Boolean>(false)
    var updateConfirm1 by mutableStateOf<Boolean>(false)
    var updateConfirm2 by mutableStateOf<Boolean>(false)
    var updateConfirm3 by mutableStateOf<Boolean>(false)
    var isValidSimulate by mutableStateOf<Boolean>(false)
    var isValidCutUserSelected by mutableStateOf<Boolean>(false)
    var isValidClothSelected1 by mutableStateOf<Boolean>(false)
    var isValidClothSelected2 by mutableStateOf<Boolean>(false)
    var isValidClothSelected3 by mutableStateOf<Boolean>(false)
    var simulateList = mutableListOf<Package>()
    val actualDate = ActualDate()
    var totalCloth = TotalCloth()

    //USER WORK SHOP
    var getUserWorkShopResponse by mutableStateOf<Response<List<UserWorkShop>>?>(null)
    var updateWorkProgressListResponse by mutableStateOf<Response<Boolean>?>(null)

    //CLOTH
    var clothListOneResponse by mutableStateOf<Response<List<Cloth>>?>(null)
    var clothListTwoResponse by mutableStateOf<Response<List<Cloth>>?>(null)
    var clothListThreeResponse by mutableStateOf<Response<List<Cloth>>?>(null)
    var updateClothResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateTotalClothResponse by mutableStateOf<Response<Boolean>?>(null)


    //PLOTTER
    var plotterListResponse by mutableStateOf<Response<List<Plotter>>?>(null)

    var updatePlotterResponse by mutableStateOf<Response<Boolean>?>(null)

    //WADDING
    var updateWaddingResponse by mutableStateOf<Response<Boolean>?>(null)
    var addDateResponse by mutableStateOf<Response<Boolean>?>(null)
    var stateWadding = mutableStateListOf<Int>()
    var stateFistsWaddingInBBDD = mutableStateListOf<Int>()
    var stateNecksWaddingInBBDD = mutableStateListOf<Int>()

    //SHIRT
    var createShirtResponse by mutableStateOf<Response<Boolean>?>(null)
    var updateShirtResponse by mutableStateOf<Response<Boolean>?>(null)
    var getShirtByIdResponse by mutableStateOf<Response<Shirt>?>(null)
    var shirtInBBDD = mutableStateListOf<String>()


    private val waddingNecks = Wadding()
    private val waddingFists = Wadding()
    var date = ActualDate()



    init{
        state = state.copy(buttonAppear = true)
        state = state.copy(buttonSimulateAppear = true)
        state = state.copy(actualDate = actualDate.actualYear.toString())
        getClothList()
        getColorList()
        getIdPackage()
        getCutUserWorkShop()
    }
    private fun getCutUserWorkShop() = viewModelScope.launch {
        getUserWorkShopResponse = Response.Loading
        userWorkShopUseCases.getCutUserWorkShop().collect{userWorkShop ->
            getUserWorkShopResponse = userWorkShop
        }
    }

    private fun enabledSimulateButton(){
        if(state.colorText1 != "") isValidSimulate = isValidCutUserSelected && isValidClothSelected1
        else if(state.colorText2 != "") isValidSimulate = isValidCutUserSelected && isValidClothSelected2
        else if(state.colorText3 != "") isValidSimulate = isValidCutUserSelected && isValidClothSelected3
        else if(state.colorText1 != "" && state.colorText2 != "") isValidSimulate = isValidCutUserSelected && isValidClothSelected1 && isValidClothSelected2
        else if(state.colorText1 != "" && state.colorText3 != "") isValidSimulate = isValidCutUserSelected && isValidClothSelected1 && isValidClothSelected3
        else if(state.colorText1 != "" && state.colorText2 != "" && state.colorText3 != "") isValidSimulate = isValidCutUserSelected && isValidClothSelected1 && isValidClothSelected2 && isValidClothSelected3
    }

    fun createShirt(shirt: Shirt) = viewModelScope.launch {
        createShirtResponse = Response.Loading
        val result = shirtUseCases.createShirt(shirt)
        createShirtResponse = result
    }

    fun updateShirt(shirt: Shirt) = viewModelScope.launch {
        updateShirtResponse = Response.Loading
        val result = shirtUseCases.updateShirt(shirt)
        updateShirtResponse = result
    }

    fun getShirtById(id: String) = viewModelScope.launch {
        getShirtByIdResponse = Response.Loading
        shirtUseCases.getShirtById(id).collect(){
            getShirtByIdResponse = it
        }
    }

    fun isValidCutUserSelected(){
        isValidCutUserSelected = state.cutUserWorkShop.id != ""
        enabledSimulateButton()
    }
    fun isValidClothSelected1(){
        isValidClothSelected1 = clothInBBDDOne.isNotEmpty()
        enabledSimulateButton()
    }
    fun isValidClothSelected2(){
        isValidClothSelected2 = clothInBBDDOne.isNotEmpty()
        enabledSimulateButton()
    }
    fun isValidClothSelected3(){
        isValidClothSelected3 = clothInBBDDOne.isNotEmpty()
        enabledSimulateButton()
    }

    fun cutUserWorkShopInput(cutUserWorkShop: UserWorkShop){
        state = state.copy(cutUserWorkShop = cutUserWorkShop)
    }

    private fun getClothList() = viewModelScope.launch {
        utilsUseCases.getList("Cloth","cloth").collect(){
            state = state.copy(clothList = it)
        }
    }
    private fun getColorList() = viewModelScope.launch {
        utilsUseCases.getList("Color","colorLine").collect(){
            state = state.copy(colorList = it)
        }
    }
    private fun getIdPackage() = viewModelScope.launch {
        packageUseCases.getIdPackage().collect(){
            if(it.isNotEmpty()){
                state = state.copy(idPackageBBDD = "${it.toInt()+1}")
            }else{
                state.idPackageBBDD
            }
        }
    }
    private fun getPlotterList(idPlotter : String) = viewModelScope.launch{
        plotterListResponse = Response.Loading
        plotterUseCases.getPlotterList(idPlotter).collect(){ responsePlotter ->
            plotterListResponse = responsePlotter
        }
    }
    private fun getSpecificClothListOne(cloth: String, color: String) = viewModelScope.launch{
        clothListOneResponse = Response.Loading
        clothUseCases.getClothList(cloth,color).collect(){ responseCloth ->
            clothListOneResponse = responseCloth
        }
    }
    private fun getSpecificClothListTwo(cloth: String, color: String) = viewModelScope.launch{
        clothListTwoResponse = Response.Loading
        clothUseCases.getClothList(cloth,color).collect(){ responseCloth ->
            clothListTwoResponse = responseCloth
        }
    }
    private fun getSpecificClothListThree(cloth: String, color: String) = viewModelScope.launch{
        clothListThreeResponse = Response.Loading
        clothUseCases.getClothList(cloth,color).collect(){ responseCloth ->
            clothListThreeResponse = responseCloth
        }
    }
    fun createPackage(packageShirt: Package) = viewModelScope.launch {
        createPackageResponse = Response.Loading
        val result = packageUseCases.createPackage(packageShirt)
        createPackageResponse = result
    }

    //MATERIAL OUTPUT

    //CLOTH
    fun getTotalCloth1(id: String) = viewModelScope.launch {
        clothUseCases.getTotalMeasureById(id).collect(){
            state = state.copy(totalCloth1 = it)
        }
    }
    fun getTotalCloth2(id: String) = viewModelScope.launch {
        clothUseCases.getTotalMeasureById(id).collect(){
            state = state.copy(totalCloth2 = it)
        }
    }
    fun getTotalCloth3(id: String) = viewModelScope.launch {
        clothUseCases.getTotalMeasureById(id).collect(){
            state = state.copy(totalCloth3 = it)
        }
    }
    fun updateCloth(cloth: Cloth) = viewModelScope.launch {
        updateClothResponse = Response.Loading
        val result = clothUseCases.updateCloth(cloth)
        updateClothResponse = result
    }
    fun updateTotalCloth(totalCloth: TotalCloth) = viewModelScope.launch {
        updateTotalClothResponse = Response.Loading
        val result = clothUseCases.updateTotalCloth(totalCloth)
        updateTotalClothResponse = result
    }


    //PLOTTER
    fun onPlotterInput(plotter : String){
        state = state.copy(plotter = plotter)
    }
    fun onSpecificPlotterList(){
        getPlotterList(state.plotter)
    }
    fun onPlotterSelectedInput(plotter: Plotter){
        state = state.copy(plotterSelected = plotter)
    }
    fun onSizesListInput(sizes: String){
        state = state.copy(sizesList = sizes.split(",").map { it.trim() })
    }
    fun updatePlotter() = viewModelScope.launch {
        updatePlotterResponse = Response.Loading
        val result = plotterUseCases.updatePlotter(state.plotterSelected)
        updatePlotterResponse = result
    }

    //WADDING
    private fun getTotalWaddingNecks(id: String) = viewModelScope.launch {
        waddingUseCases.getTotalWadding("Cuellos $id").collect(){
            stateNecksWaddingInBBDD.add(it.toInt())
        }
    }
    private fun getTotalWaddingFists(id: String) = viewModelScope.launch {
        waddingUseCases.getTotalWadding("Puños $id").collect(){
            stateFistsWaddingInBBDD.add(it.toInt())
        }
    }
    private fun updateWadding(wadding: Wadding) = viewModelScope.launch {
        updateWaddingResponse = Response.Loading
        val result = waddingUseCases.updateWadding(wadding)
        updateWaddingResponse = result
    }
    private fun addDateWadding(newDate: String, wadding: Wadding) = viewModelScope.launch {
        addDateResponse = Response.Loading
        val result = waddingUseCases.addDateWadding(newDate,wadding)
        addDateResponse = result
    }


    // Text/Color One
    fun onClothText1Input(clothText1: String){
        state = state.copy(clothText1 = clothText1)
    }
    fun onColorText1Input(colorText1: String){
        state = state.copy(colorText1 = colorText1)
    }
    fun onSpecificClothListOne(){
        getSpecificClothListOne(state.clothText1,state.colorText1)
        getTotalCloth1("${state.clothText1} ${state.colorText1}")
        updateConfirm1 = true
    }
    fun onClothInBBDDSizeOne(size : Int, cloth: Cloth){
        repeat(size){ clothInBBDDOne.add(cloth) }
    }
    fun containsClothInBBDDOne(index: Int, option : Cloth, cloth : Cloth){
        if(!clothInBBDDOne.contains(option)){
            onClothInBBDDInputOne(index, option)
        }else{
            onClothInBBDDInputOne(index, cloth)
        }
    }
    private fun onClothInBBDDInputOne(index: Int, cloth: Cloth){
        clothInBBDDOne[index] = cloth
    }
    fun containsClothSelectedOne(cloth : Cloth, measureCloth: Double){
        if(!clothSelected.contains(cloth)){
            clothSelected.add(cloth)
            clothMeasureSelectedOne.add(measureCloth)
        }else{
            clothSelected.remove(cloth)
            clothMeasureSelectedOne.remove(measureCloth)
        }
    }
    fun totalMeasureOneSelectedOne():String{
        return clothMeasureSelectedOne.sum().toString()
    }

    // Text/Color Two
    fun onClothText2Input(clothText2: String){
        state = state.copy(clothText2 = clothText2)
    }
    fun onColorText2Input(colorText2: String){
        state = state.copy(colorText2 = colorText2)
    }
    fun onSpecificClothListTwo(){
        getSpecificClothListTwo(state.clothText2,state.colorText2)
        getTotalCloth2("${state.clothText2} ${state.colorText2}")
        updateConfirm2 = true
    }
    fun onClothInBBDDSizeTwo(size : Int, cloth: Cloth){
        repeat(size){ clothInBBDDTwo.add(cloth) }
    }
    fun containsClothInBBDDTwo(index: Int, option : Cloth, cloth : Cloth){
        if(!clothInBBDDTwo.contains(option)){
            onClothInBBDDInputTwo(index, option)
        }else{
            onClothInBBDDInputTwo(index, cloth)
        }
    }
    private fun onClothInBBDDInputTwo(index: Int, cloth: Cloth){
        clothInBBDDTwo[index] = cloth
    }
    fun containsClothSelectedTwo(cloth : Cloth, measureCloth: Double){
        if(!clothSelected.contains(cloth)){
            clothSelected.add(cloth)
            clothMeasureSelectedTwo.add(measureCloth)
        }else{
            clothSelected.remove(cloth)
            clothMeasureSelectedTwo.remove(measureCloth)
        }
    }
    fun totalMeasureOneSelectedTwo():String{
        return clothMeasureSelectedTwo.sum().toString()
    }


    // Text/Color Three
    fun onClothText3Input(clothText3: String){
        state = state.copy(clothText3 = clothText3)
    }
    fun onColorText3Input(colorText3: String){
        state = state.copy(colorText3 = colorText3)
    }
    fun onSpecificClothListThree(){
        getSpecificClothListThree(state.clothText3,state.colorText3)
        getTotalCloth3("${state.clothText3} ${state.colorText3}")
        updateConfirm3 = true
    }
    fun onClothInBBDDSizeThree(size : Int, cloth: Cloth){
        repeat(size){ clothInBBDDThree.add(cloth) }
    }
    fun containsClothInBBDDThree(index: Int, option : Cloth, cloth : Cloth){
        if(!clothInBBDDThree.contains(option)){
            onClothInBBDDInputThree(index, option)
        }else{
            onClothInBBDDInputThree(index, cloth)
        }
    }
    private fun onClothInBBDDInputThree(index: Int, cloth: Cloth){
        clothInBBDDThree[index] = cloth
    }
    fun containsClothSelectedThree(cloth : Cloth, measureCloth: Double){
        if(!clothSelected.contains(cloth)){
            clothSelected.add(cloth)
            clothMeasureSelectedThree.add(measureCloth)
        }else{
            clothSelected.remove(cloth)
            clothMeasureSelectedThree.remove(measureCloth)
        }
    }
    fun totalMeasureOneSelectedThree():String{
        return clothMeasureSelectedThree.sum().toString()
    }

    fun onSimulateListInput(numberId: String, color: String, sizes: String, measure: String){
        val newPackage = Package(
            id = "$numberId${state.actualDate}",
            name = "${sizes[0]}${color[0]}${sizes.substring(1)}",
            gender = "${sizes[0]}",
            color = color,
            size = sizes.substring(1),
            quantity = "${(measure.toDouble()/state.plotterSelected.height.toDouble()).toInt()}",
            lote = state.plotter,
            dateCut = actualDate.actualDate,
            cutJob = state.cutUserWorkShop.id,
            ubication = "Corte"
        )
        simulateList.add(newPackage)
    }

    fun updateWorkProgressList(id: String) = viewModelScope.launch{
        updateWorkProgressListResponse = Response.Loading
        val result = userWorkShopUseCases.updateWorkProgressList(state.cutUserWorkShop,"${id},${actualDate.actualDate}")
        updateWorkProgressListResponse = result
    }

    fun onUpdateTotalCloth(){
        onUpdateTotalCloth1()
        onUpdateTotalCloth2()
        onUpdateTotalCloth3()
    }
    private fun onUpdateTotalCloth1(){
        if(updateConfirm1){
            totalCloth.id = "${state.clothText1} ${state.colorText1}"
            totalCloth.totalMeasure = "${state.totalCloth1.totalMeasure.toDouble()-clothMeasureSelectedOne.sum()}"
            updateTotalCloth(totalCloth)
        }
    }
    private fun onUpdateTotalCloth2(){
        if(updateConfirm2){
            totalCloth.id = "${state.clothText2} ${state.colorText2}"
            totalCloth.totalMeasure = "${state.totalCloth2.totalMeasure.toInt()-clothMeasureSelectedTwo.sum()}"
            updateTotalCloth(totalCloth)
        }
    }
    private fun onUpdateTotalCloth3(){
        if(updateConfirm3){
            totalCloth.id = "${state.clothText3} ${state.colorText3}"
            totalCloth.totalMeasure = "${state.totalCloth3.totalMeasure.toInt()-clothMeasureSelectedThree.sum()}"
            updateTotalCloth(totalCloth)
        }
    }
    fun onGetWaddingInput(index: Int){
        var id = ""
        when(state.sizesList[index].firstOrNull()){
            'B'->{
                if(state.sizesList[index].substring(1) == "S" || state.sizesList[index].substring(1) == "M" ){
                    id = "S-M"
                    getTotalWaddingNecks(id)
                    getTotalWaddingFists(id)
                }else{
                    id = "L-XL"
                    getTotalWaddingNecks(id)
                    getTotalWaddingFists(id)
                }
            }
            'C'->{
                id = state.sizesList[index].substring(1)
                getTotalWaddingNecks(id)
                getTotalWaddingFists(id)            }
        }
    }

    fun onUpdateWadding(index: Int){
         stateWadding.add(simulateList.filter{
             when(state.sizesList[index].firstOrNull()){
                 'B' ->{
                     when(state.sizesList[index].last()) {
                         'L' -> {
                             it.name.firstOrNull() == state.sizesList[index].firstOrNull() && it.name.last() == state.sizesList[index].last()
                         }
                         else -> {
                             it.name.firstOrNull() == state.sizesList[index].firstOrNull() && it.name.substring(2) == "S" || it.name.substring(2) == "M"
                         }

                     }
                 }
                 else ->{
                     it.name.firstOrNull() == state.sizesList[index].firstOrNull() && it.name.substring(
                         2
                     ) == state.sizesList[index].substring(2)
                 }

             }
         }.map { it.quantity.toInt() }.sum())



         waddingNecks.totalWadding = "${stateNecksWaddingInBBDD[index]-stateWadding[index]}"
         waddingFists.totalWadding = "${stateFistsWaddingInBBDD[index]-stateWadding[index]}"

         when(state.sizesList[index].firstOrNull()){
             'B'->{
                 if(state.sizesList[index].substring(1) == "S" || state.sizesList[index].substring(1) == "M" ){
                     waddingNecks.id = "Cuellos S-M"
                     waddingFists.id = "Puños S-M"
                     updateWadding(waddingNecks)
                     updateWadding(waddingFists)
                     addDateWadding("${stateWadding[index]},${date.actualDate},Output",waddingNecks)
                     addDateWadding("${stateWadding[index]},${date.actualDate},Output",waddingFists)

                 }else{
                     waddingNecks.id = "Cuellos L-XL"
                     waddingFists.id = "Puños L-XL"
                     updateWadding(waddingNecks)
                     updateWadding(waddingFists)
                     addDateWadding("${stateWadding[index]},${date.actualDate},Output",waddingNecks)
                     addDateWadding("${stateWadding[index]},${date.actualDate},Output",waddingFists)
                 }
             }
             'C'->{
                 waddingNecks.id = "Cuellos ${state.sizesList[index].substring(1)}"
                 waddingFists.id = "Puños ${state.sizesList[index].substring(1)}"
                 updateWadding(waddingNecks)
                 updateWadding(waddingFists)
                 addDateWadding("${stateWadding[index]},${date.actualDate},Output",waddingNecks)
                 addDateWadding("${stateWadding[index]},${date.actualDate},Output",waddingFists)
             }
         }
    }

    private fun onCreateShirt(id: String, quantity : String){
        val shirt = Shirt(
            id = id,
            inProgress = quantity
        )
        createShirt(shirt)
    }

    private fun onUpdateShirt(id: String, quantity: String){
        val shirt = Shirt(
            id = id,
            inProgress = quantity
        )
        updateShirt(shirt)
    }

    fun onUpdateOrCreateShirt(id: String, quantity: String, index: Int){
        if(quantity != ""){
            onUpdateShirt(id,(quantity.toInt()+shirtInBBDD[index].toInt()).toString())
        }else{
            onCreateShirt(id, quantity)
        }
    }


}