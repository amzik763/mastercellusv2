package com.amzi.mastercellusv2.navgraphs

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.amzi.mastercellusv2.AllScreens.authScreens.LoginScreen
import com.amzi.mastercellusv2.AllScreens.authScreens.SignupScreen
import com.amzi.mastercellusv2.AllScreens.authScreens.SplashScreen
import com.amzi.mastercellusv2.AllScreens.authScreens.forgotPasswordScreen
import com.amzi.mastercellusv2.AllScreens.authScreens.setPasswordScreen
import com.amzi.mastercellusv2.AllViewModels.Factories.RegisterViewModelFactory
import com.amzi.mastercellusv2.AllViewModels.RegisterViewModel
import com.amzi.mastercellusv2.Networks.AuthAPIs
import com.amzi.mastercellusv2.Networks.RetrofitBuilder
import com.amzi.mastercellusv2.Repository.AuthRepo
import com.amzi.mastercellusv2.utility.mGraph

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
){

    val authAPI: AuthAPIs by lazy {
        RetrofitBuilder.instance.create(AuthAPIs::class.java)
    }
       val authRepo:AuthRepo = AuthRepo(authAPI)
    val viewModelFactory = RegisterViewModelFactory(authRepo)
    val mRegisterViewModel: RegisterViewModel = viewModelFactory.create(RegisterViewModel::class.java)


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
            LoginScreen(viewModel = mRegisterViewModel)
        }

        composable(route = Screens.Signup.route){

            SignupScreen(viewModel = mRegisterViewModel)
        }

        composable(route = Screens.SetPassword.route){

            setPasswordScreen(viewModel = mRegisterViewModel)
        }

        composable(route = Screens.forgotPassword.route){

            forgotPasswordScreen(viewModel = mRegisterViewModel)
        }
    }
}