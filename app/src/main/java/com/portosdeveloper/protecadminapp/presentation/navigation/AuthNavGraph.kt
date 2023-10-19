package com.portosdeveloper.protecadminapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.portosdeveloper.protecadminapp.presentation.screens.LoginScreen
import com.portosdeveloper.protecadminapp.presentation.screens.SignUpScreen.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(navController)
        }
        composable(route = AuthScreen.SignUp.route){
            SignUpScreen(navController)
        }
    }
}

sealed class AuthScreen(val route : String){
    object Login: AuthScreen("login")
    object SignUp: AuthScreen("sign_up")
}