package com.amzi.mastercellusv2.navgraphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.amzi.mastercellusv2.utility.HOMEAUTOMATION_ROUTE
import com.amzi.mastercellusv2.utility.HOME_ROUTE

const val DETAILS_ARGUMENT_KEY = "id"
const val DETAILS_ARGUMENT_NAME = "name"
const val AUTH_ARGUMENT_ID = "id"

sealed class Screens(val route:String) {
    object Home: Screens(HOME_ROUTE)
    object HomeAutomation: Screens(HOMEAUTOMATION_ROUTE)
    object Starter: Screens("starter2")

    //AUTH SCREENS
    object Splash: Screens("splash?id={id}"){
        fun passID(id:Int = 0):String{
            return "splash?id=$id"
        }
    }
    object Register : Screens("register")
    object Verification : Screens("Verify")
    object DeviceList : Screens("devicelist")
    object DeviceRegister : Screens("deviceRegister")
    object Login: Screens("login")
    object HomeAuto: Screens("HomeAuto")
    object LedScreen: Screens("LedScreen")
    object FanScreen: Screens("FanScreen")
    object AcScreen: Screens("AcScreen")

    object SetPassword: Screens("setPassword")
    object forgotPassword: Screens("forgotPassword")
    object Detail: Screens("ds/{$DETAILS_ARGUMENT_KEY}/{$DETAILS_ARGUMENT_NAME}"){
        fun passStringID(id:String):String{
//            return "ds/$id"
            return this.route.replace(oldValue = DETAILS_ARGUMENT_KEY, newValue = id.toString())
        }
        fun passNameandID(id:String,name:String):String{
            return "ds/$DETAILS_ARGUMENT_KEY/$DETAILS_ARGUMENT_NAME"
        }
    }
}
    sealed class BottomBarStarterScreens(val route:String, val title:String, val icon:ImageVector ){
    object Starter: BottomBarStarterScreens("starter_home","starter", Icons.Default.Home)
    object Starter_Details: BottomBarStarterScreens("starter_details","starter details", Icons.Default.Menu)
    object Starter_Dashboard: BottomBarStarterScreens("starter_dashboard","starter home", Icons.Default.AccountCircle)
}