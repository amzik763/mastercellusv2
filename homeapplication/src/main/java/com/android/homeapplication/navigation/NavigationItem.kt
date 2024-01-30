package com.example.homeapplication.navigation

import com.android.homeapplication.R

sealed class NavigationItem(val route: String, val label: String, val icons: Int){

    object HomeScreen : NavigationItem("home", "Home", R.drawable.img6)

    object LedBulbScreen : NavigationItem("bulb","Bulb", R.drawable.img7)

    object FanAcScreen : NavigationItem("fanAc","FanAc", R.drawable.img8)

}