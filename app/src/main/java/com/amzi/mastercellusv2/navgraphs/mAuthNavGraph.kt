package com.amzi.mastercellusv2.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.amzi.mastercellusv2.AllScreens.authScreens.LoginScreen
import com.amzi.mastercellusv2.AllScreens.authScreens.SignupScreen
import com.amzi.mastercellusv2.AllScreens.authScreens.SplashScreen
import com.amzi.mastercellusv2.utility.mGraph

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screens.Splash.route,
        route = mGraph.AUTH
    ){

        composable(route = Screens.Splash.route, arguments = listOf(
            navArgument("id"){
                type = NavType.IntType
            }
        )){
            SplashScreen()
        }

        composable(route = Screens.Login.route){
            LoginScreen()
        }

        composable(route = Screens.Signup.route){
            SignupScreen()
        }
    }
}