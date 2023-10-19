package com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.components


import com.portosdeveloper.protecadminapp.domain.model.Plotter
import com.portosdeveloper.protecadminapp.domain.model.TotalCloth
import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop

data class ProgramingLineState(
    var actualDate: String = "",
    var idPackageBBDD: String = "1",
    var newIdPackage: String = "",
    var plotter: String = "",
    var clothText1: String = "",
    var clothText2: String = "",
    var clothText3: String = "",
    var colorText1: String = "",
    var colorText2: String = "",
    var colorText3: String = "",
    var clothList: List<String> = listOf(),
    var colorList: List<String> = listOf(),
    var sizesList: List<String> = listOf(),
    var plotterSelected: Plotter = Plotter(),
    var buttonAppear: Boolean = false,
    var buttonSimulateAppear: Boolean = false,
    var selectClothAppear: Boolean = false,
    var finalProgramingAppear: Boolean = false,
    var totalCloth1: TotalCloth = TotalCloth(),
    var totalCloth2: TotalCloth = TotalCloth(),
    var totalCloth3: TotalCloth = TotalCloth(),
    var cutUserWorkShop: UserWorkShop = UserWorkShop(),
    var shirtInBBDD: List<String> = listOf()
)
