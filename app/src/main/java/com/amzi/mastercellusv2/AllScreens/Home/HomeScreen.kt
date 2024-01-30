package com.amzi.mastercellusv2.AllScreens.Home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.ui.theme.Purple40
import com.amzi.mastercellusv2.utility.mGraph
import com.example.homeapplication.navigation.Navigation

@Composable
fun HomeScreen(
//  navHostController: NavHostController
) {

    var ct = LocalContext.current
    Log.d("AMZI: ","hello a")

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "HOME AUTO",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = Purple40,
            modifier = Modifier.clickable {
                mNavigator.navigateTo(Screens.HomeAutomation.route)
//                navHostController.navigate(route = Screens.Detail.passNameandID("abc","amzad"))
            }
        )
        Text(
            text = "Login/SignUp",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = Purple40,
            modifier = Modifier
                .padding(36.dp)
                .clickable {
                    mNavigator.navigateTo(Screens.Detail.passNameandID("abc", "amzad"))
//                navHostController.navigate(route = Screens.Detail.passNameandID("abc","amzad"))
                }

        )


        Text(
            text = "STARTER",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = Purple40,
            modifier = Modifier
                .padding(36.dp)
                .clickable {

                    mNavigator.navigateTo(mGraph.STARTER)
//                navHostController.navigate(route = Screens.Detail.passNameandID("abc","amzad"))
                }

        )


    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview(){

    HomeScreen(
//        navHostController = rememberNavController()
    )

}