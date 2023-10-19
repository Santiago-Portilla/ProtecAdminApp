package com.portosdeveloper.protecadminapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.portosdeveloper.protecadminapp.presentation.screens.InputMaterialScreen.InputMaterialScreen
import com.portosdeveloper.protecadminapp.presentation.screens.NotificationScreen.NotificationScreen
import com.portosdeveloper.protecadminapp.presentation.screens.PersonalScreen.PersonalScreen
import com.portosdeveloper.protecadminapp.presentation.screens.ProfileScreen.ProfileScreen
import com.portosdeveloper.protecadminapp.presentation.screens.ProgramingScreen.ProgramingScreen
import com.portosdeveloper.protecadminapp.presentation.screens.SearchScreen.SearchScreen
import com.portosdeveloper.protecadminapp.presentation.screens.StockScreen.StockScreen

@Composable
fun HomeBottomBarNavGraph(navController: NavHostController){
    
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.InputMaterial.route
    ){
        composable(route = HomeBottomBarScreen.InputMaterial.route){
            InputMaterialScreen(navController)
        }
        composable(route = HomeBottomBarScreen.Program.route){
            ProgramingScreen(navController)
        }
        composable(route = HomeBottomBarScreen.Stock.route){
            StockScreen(navController)
        }
        composable(route = HomeBottomBarScreen.Search.route){
            SearchScreen(navController)
        }
        composable(route = HomeBottomBarScreen.Personal.route){
            PersonalScreen(navController)
        }
        composable(route = HomeBottomBarScreen.Notification.route){
            NotificationScreen()
        }
        composable(route = HomeBottomBarScreen.Profile.route){
            ProfileScreen()
        }
        detailsNavGraph(navController)
    }
    
}

sealed class HomeBottomBarScreen(
    val route: String,
    val icon: ImageVector
){
    object InputMaterial: HomeBottomBarScreen(
        route = "input_material",
        icon = Icons.Default.ShoppingCart
    )
    object Program: HomeBottomBarScreen(
        route = "program",
        icon = Icons.Default.DateRange
    )
    object Stock: HomeBottomBarScreen(
        route = "stock",
        icon = Icons.Default.List
    )
    object Search: HomeBottomBarScreen(
        route = "search",
        icon = Icons.Default.Search
    )
    object Personal: HomeBottomBarScreen(
        route = "personal",
        icon = Icons.Default.Face
    )
    object Notification: HomeBottomBarScreen(
        route = "notification",
        icon = Icons.Default.Notifications
    )
    object Profile: HomeBottomBarScreen(
        route = "profile",
        icon = Icons.Default.Person
    )
}