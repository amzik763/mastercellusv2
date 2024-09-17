package com.amzi.mastercellusv2.navgraphs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amzi.mastercellusv2.allScreens.starterScreens.StarterDetailScreen
import com.amzi.mastercellusv2.allScreens.starterScreens.StarterHomeScreen
import com.amzi.mastercellusv2.allScreens.starterScreens.StarterScreen
import com.amzi.mastercellusv2.utility.mGraph

class mStarterNavigator(){

    companion object{


        lateinit var navcontroller: NavHostController

        fun navigateTo(passID: String) {
            Log.d("Amzi: ","will do strt")
            navcontroller.navigate(passID)
        }
    }
}

@Composable
fun starterNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController,
        startDestination = BottomBarStarterScreens.Starter.route,
        route = mGraph.STARTER
    ){

        mStarterNavigator.navcontroller = navController

        composable(route = BottomBarStarterScreens.Starter.route){
            StarterScreen()
        }

        composable(route = BottomBarStarterScreens.Starter_Dashboard.route){
            StarterHomeScreen()
        }

        composable(route = BottomBarStarterScreens.Starter_Details.route){
            StarterDetailScreen()
        }

    }
}