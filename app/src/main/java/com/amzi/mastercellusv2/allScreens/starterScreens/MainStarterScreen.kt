package com.amzi.mastercellusv2.allScreens.starterScreens

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.amzi.mastercellusv2.navgraphs.BottomBarStarterScreens
import com.amzi.mastercellusv2.navgraphs.starterNavGraph


@Composable
fun MainStarterScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Log.d("abc",it.toString())
        starterNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    val myScreens = listOf(
        BottomBarStarterScreens.Starter,
                BottomBarStarterScreens.Starter_Dashboard,
                BottomBarStarterScreens.Starter_Details
    )
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    BottomNavigation() {
        myScreens.forEach { screen ->
            addItem(
                screens = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }

}

@Composable
fun RowScope.addItem(
    screens: BottomBarStarterScreens,
    currentDestination:NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = {
            Text(text = screens.title)
        },
        icon = {
            Icon(imageVector = screens.icon, contentDescription = "NAV ICON")
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screens.route
        }== true,
        onClick = {
            navController.navigate(screens.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}