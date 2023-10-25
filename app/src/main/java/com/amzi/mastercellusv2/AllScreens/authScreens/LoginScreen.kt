package com.amzi.mastercellusv2.AllScreens.authScreens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.ui.theme.Purple40
import com.amzi.mastercellusv2.utility.mGraph

@Composable
fun LoginScreen(
//    navHostController: NavHostController
) {

    Log.d("AMZI: ","hello a")


    Column(
        Modifier.fillMaxSize(),
    ) {
        Text(
            text = "SKAIO - LOGIN",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = Purple40,
            modifier = Modifier.clickable {
                mNavigator.navigateTo(mGraph.HOME)
//                navHostController.navigate(route = Screens.Detail.passNameandID("abc","amzad"))
            }

        )

        Text(
            text = "Go to SignUP",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = Purple40,
            modifier = Modifier.clickable {
                mNavigator.navigateTo(Screens.Signup.route)
//                navHostController.navigate(route = Screens.Detail.passNameandID("abc","amzad"))
            }

        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginScreenPreview(){

    LoginScreen(
//        navHostController = rememberNavController()
    )

}