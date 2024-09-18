package com.amzi.mastercellusv2.allScreens.starterScreens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.ui.theme.Purple40

@Composable
fun StarterScreen(
    navController: NavHostController = rememberNavController()
){
    Log.d("AMZI: ", "ics")

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SKAIO - STARTER MAIN HOME",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = Purple40,
            modifier = Modifier.clickable {
                mNavigator.navigateTo(Screens.Register.route)
//                navHostController.navigate(Screens.Auth.route)
//                {
//                    popUpTo(Screens.Home.route){
//                        inclusive = true
//                    }
//                }
            }

        )
    }
}

/*
{

    Log.d("AMZI: ","hello a")


    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SKAIO - Starter",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            color = Purple40,
            modifier = Modifier.clickable {
                mNavigator.navigateTo(Screens.Signup.route)
//                navHostController.navigate(route = Screens.Detail.passNameandID("abc","amzad"))
            }

        )
    }
}*/

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun StarterScreenPreview(){

    StarterScreen(
//        navHostController = rememberNavController()
    )

}