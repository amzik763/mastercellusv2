package com.amzi.mastercellusv2.navgraphs

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.amzi.mastercellusv2.utility.mGraph
import com.amzi.mastercellusv2.utility.myComponents.navController

class mNavigator(){

    companion object{


        lateinit var navcontroller: NavHostController

        fun navigateTo(passID: String) {
            Log.d("Amzi: ","will do")
            navcontroller.navigate(passID)
        }

    }


}


@Composable
fun setUpNavGraph(
//    navController: NavHostController,

){

    NavHost(navController = navController,
        startDestination = mGraph.AUTH,
        route = mGraph.ROOT){

        mNavigator.navcontroller = navController

       homeNavGraph(navController)
//        composable(route = Screens.Home.route){
//            HomeScreen()
//        }
        authNavGraph(navController )

    }
}