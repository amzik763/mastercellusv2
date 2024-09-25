package com.amzi.mastercellusv2.navgraphs

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.amzi.mastercellusv2.allScreens.authScreens.DeviceList
import com.amzi.mastercellusv2.allScreens.authScreens.DeviceRegistration
import com.amzi.mastercellusv2.allScreens.authScreens.LoginScreen
import com.amzi.mastercellusv2.allScreens.authScreens.RegisterScreen
import com.amzi.mastercellusv2.allScreens.authScreens.SplashScreen
import com.amzi.mastercellusv2.allScreens.authScreens.Verification
import com.amzi.mastercellusv2.allViewModels.Factories.RegisterViewModelFactory
import com.amzi.mastercellusv2.allViewModels.RegisterViewModel
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.utility.mGraph
import com.amzi.mastercellusv2.utility.myComponents
import com.amzi.mastercellusv2.utility.myComponents.authAPI
import com.amzi.mastercellusv2.utility.showLogs

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    context: Context
){


//    val authAPI: AuthAPIs by lazy {
//        RetrofitBuilder.instance.create(AuthAPIs::class.java)
//    }
       val authRepo:AuthRepo = AuthRepo(authAPI, context)
    val viewModelFactory = RegisterViewModelFactory(authRepo)
    val mRegisterViewModel: RegisterViewModel = viewModelFactory.create(RegisterViewModel::class.java)


    navigation(
        startDestination = Screens.Register.route,
        route = mGraph.AUTH
    ){

        composable(route = Screens.Splash.route, arguments = listOf(
            navArgument("id"){
                type = NavType.IntType
            }
        )){
            SplashScreen()
        }

        composable(route = Screens.Register.route){
            RegisterScreen()
        }

        composable(route = Screens.Verification.route){
            Verification()
        }

        composable(route = Screens.Login.route){
            LoginScreen(viewModel = mRegisterViewModel)
        }

        composable(route = Screens.DeviceList.route){
            DeviceList(viewModel = mRegisterViewModel)
        }

        composable(route = Screens.DeviceRegister.route){
            DeviceRegistration(viewModel = mRegisterViewModel)
        }
    }
}