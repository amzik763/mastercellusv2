package com.amzi.mastercellusv2.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.amzi.mastercellusv2.AllScreens.authScreens.LoginScreen
import com.amzi.mastercellusv2.AllScreens.authScreens.SetPasswordScreen
import com.amzi.mastercellusv2.AllScreens.authScreens.SignupScreen
import com.amzi.mastercellusv2.AllScreens.authScreens.SplashScreen
import com.amzi.mastercellusv2.AllScreens.authScreens.forgotPasswordScreen
import com.amzi.mastercellusv2.AllViewModels.Factories.RegisterViewModelFactory
import com.amzi.mastercellusv2.AllViewModels.RegisterViewModel
import com.amzi.mastercellusv2.networks.AuthAPIs
import com.amzi.mastercellusv2.networks.RetrofitBuilder
import com.amzi.mastercellusv2.Repository.AuthRepo
import com.amzi.mastercellusv2.utility.mGraph
import com.amzi.mastercellusv2.utility.showLogs

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
        startDestination = Screens.Login.route,
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

            SignupScreen()
        }

//        composable(route = Screens.SetPassword.route){
//
//            SetPasswordScreen()
//        }

        composable(route = Screens.SetPassword.route + "/{mode}") { navBackStackEntry ->
            val mode = navBackStackEntry.arguments?.getString("mode") ?: "register"
            showLogs("my mode in compose:",mode)
            SetPasswordScreen(mode)
        }

        composable(route = Screens.forgotPassword.route){
            forgotPasswordScreen()
        }
    }
}