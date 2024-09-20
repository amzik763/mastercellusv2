package com.amzi.mastercellusv2.navgraphs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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
val context = LocalContext.current
    NavHost(navController = navController,
        startDestination = mGraph.AUTH,
        route = mGraph.ROOT){

        mNavigator.navcontroller = navController

       homeNavGraph(navController)
//        composable(route = Screens.Home.route){
//            HomeScreen()
//        }
        authNavGraph(navController, context )

    }
}