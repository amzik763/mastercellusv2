package com.amzi.mastercellusv2.navgraphs

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.amzi.mastercellusv2.AllScreens.Home.DetailsScreen
import com.amzi.mastercellusv2.AllScreens.Home.HomeScreen
import com.amzi.mastercellusv2.AllScreens.starterScreens.MainStarterScreen
import com.amzi.mastercellusv2.AllViewModels.UiViewmodel
import com.amzi.mastercellusv2.utility.mGraph
import com.example.homeapplication.navigation.Navigation

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screens.Home.route,
        route = mGraph.HOME
    ){

        composable(route = mGraph.STARTER){
            MainStarterScreen()
        }

        composable(route = Screens.Home.route){
            HomeScreen(UiViewmodel(LocalContext.current))
        }

        composable(route = Screens.HomeAutomation.route){
            Navigation(LocalContext.current)

        }
        composable(route = Screens.Detail.route,
            arguments = listOf(
                navArgument(DETAILS_ARGUMENT_KEY){
                    type = NavType.StringType
                },
                navArgument(DETAILS_ARGUMENT_NAME){
                    type = NavType.StringType
                }

            )){

            Log.d("AMZI: ", it.arguments?.getString(DETAILS_ARGUMENT_KEY).toString())
            Log.d("AMZI: ", it.arguments?.getString(DETAILS_ARGUMENT_NAME).toString())
            DetailsScreen(it.arguments?.getString(DETAILS_ARGUMENT_NAME).toString())
        }
    }


}
