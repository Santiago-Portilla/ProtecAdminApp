package com.portosdeveloper.protecadminapp.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.portosdeveloper.protecadminapp.presentation.screens.InputButtonScreen.InputButtonScreen
import com.portosdeveloper.protecadminapp.presentation.screens.InputClothScreen.InputClothScreen
import com.portosdeveloper.protecadminapp.presentation.screens.InputPackingScreen.InputPackingScreen
import com.portosdeveloper.protecadminapp.presentation.screens.InputPlotterScreen.InputPlotterScreen
import com.portosdeveloper.protecadminapp.presentation.screens.InputReflectiveScreen.InputReflectiveScreen
import com.portosdeveloper.protecadminapp.presentation.screens.InputRollWaddingScreen.InputRollWaddingScreen
import com.portosdeveloper.protecadminapp.presentation.screens.InputThreadScreen.InputThreadScreen
import com.portosdeveloper.protecadminapp.presentation.screens.InputWaddingScreen.InputWaddingScreen
import com.portosdeveloper.protecadminapp.presentation.screens.InputYarnScreens.InputYarnScreen
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingLineScreen.ProgramingLineScreen
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingScreen.ProgramingScreen
import com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber.SearchWithNumberScreen
import com.portosdeveloper.protecadminapp.presentation.screens.SearchWithQR.SearchWithQRScreen
import com.portosdeveloper.protecadminapp.presentation.screens.StockProgress.StockProgressScreen
import com.portosdeveloper.protecadminapp.presentation.screens.StockSuppliesScreen.StockSuppliesScreen
import com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen.UserWorkShopScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController){

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.InputCloth.route
    ){
        composable( route = DetailsScreen.InputCloth.route){
            InputClothScreen(navController)
        }
        composable( route = DetailsScreen.InputPlotter.route){
            InputPlotterScreen(navController)
        }
        composable( route = DetailsScreen.InputWadding.route){
            InputWaddingScreen(navController)
        }
        composable( route = DetailsScreen.InputRollWadding.route){
            InputRollWaddingScreen(navController)
        }
        composable( route = DetailsScreen.InputThread.route){
            InputThreadScreen(navController)
        }
        composable( route = DetailsScreen.InputYarn.route){
            InputYarnScreen(navController)
        }
        composable( route = DetailsScreen.InputReflective.route){
            InputReflectiveScreen(navController)
        }
        composable( route = DetailsScreen.InputButton.route){
            InputButtonScreen(navController)
        }
        composable( route = DetailsScreen.InputPacking.route){
            InputPackingScreen(navController)
        }
        composable( route = DetailsScreen.StockSupplies.route){
            StockSuppliesScreen(navController)
        }
        composable( route = DetailsScreen.StockProgress.route){
            StockProgressScreen(navController)
        }
        composable( route = DetailsScreen.ProgramingLine.route){
            ProgramingLineScreen(navController)
        }
        composable( route = DetailsScreen.SearchWithNumber.route){
            SearchWithNumberScreen(navController)
        }
        composable( route = DetailsScreen.SearchWithQR.route){
            SearchWithQRScreen(navController)
        }

        composable(
            route = DetailsScreen.UserWorkShop.route,
            arguments = listOf(navArgument("userWorkShop"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("userWorkShop")?.let{
                UserWorkShopScreen(navController)
            }
        }
    }

}

sealed class DetailsScreen(val route : String){

    object InputCloth : DetailsScreen("input_cloth")
    object InputPlotter : DetailsScreen("input_plotter")
    object InputWadding : DetailsScreen("input_wadding")
    object InputRollWadding : DetailsScreen("input_roll_wadding")
    object InputThread : DetailsScreen("input_thread")
    object InputYarn : DetailsScreen("input_yarn")
    object InputReflective : DetailsScreen("input_reflective")
    object InputButton : DetailsScreen("input_button")
    object InputPacking : DetailsScreen("input_packing")
    object StockSupplies : DetailsScreen("stock_supplies")
    object StockProgress : DetailsScreen("stock_progress")
    object ProgramingLine : DetailsScreen("programing_line")
    object UserWorkShop : DetailsScreen("personal/details/{userWorkShop}"){
        fun passUserWorkShop(userWorkShop: String) = "personal/details/$userWorkShop"
    }
    object SearchWithNumber : DetailsScreen("search_with_number")
    object SearchWithQR : DetailsScreen("search_with_qr")

}