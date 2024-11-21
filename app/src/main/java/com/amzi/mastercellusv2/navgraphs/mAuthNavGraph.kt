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
import com.amzi.mastercellusv2.dialog.PropertiesScreen
import com.amzi.mastercellusv2.utility.mGraph
import com.amzi.mastercellusv2.homeScreen.HomeDashboard
import com.amzi.mastercellusv2.homeScreen.LedBulbScreen
import com.amzi.mastercellusv2.homeScreen.SubFolderScreen
import com.amzi.mastercellusv2.homeScreen.data.FoldersScreen
import com.android.homeapplication.viewModel.HomeAppViewModel
import com.android.homeapplication.screen.FanAcScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    context: Context
){

    val viewModel = HomeAppViewModel(context)

//    val authAPI: AuthAPIs by lazy {
//        RetrofitBuilder.instance.create(AuthAPIs::class.java)
//    }
       /* val authRepo:AuthRepo = AuthRepo(authAPI, context)
        val homeAutoRepo: HomeAutoRepo = HomeAutoRepo(homeAutoApi)
        val viewModelFactory = RegisterViewModelFactory(authRepo, homeAutoRepo)
        val mRegisterViewModel: RegisterViewModel = viewModelFactory.create(RegisterViewModel::class.java)*/


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
            LoginScreen()
        }

        composable(route = Screens.PropertiesScreen.route){
            PropertiesScreen()
        }

        composable(route = Screens.SubFolderScreen.route){
            SubFolderScreen()
        }

        composable(route = Screens.Folders.route){
            FoldersScreen()
        }

        composable(route = Screens.DeviceList.route){
            DeviceList()
        }

        composable(route = Screens.DeviceRegister.route){
            DeviceRegistration()
        }
        composable(route = Screens.HomeAuto.route){
            HomeDashboard()
        }
        composable(route = Screens.LedScreen.route){
            LedBulbScreen( viewModel = viewModel )
        }
        composable(route = Screens.FanScreen.route){
            FanAcScreen()
        }
    }
}