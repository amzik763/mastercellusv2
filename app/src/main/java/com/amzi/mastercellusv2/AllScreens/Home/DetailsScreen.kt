package com.amzi.mastercellusv2.AllScreens.Home

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
import com.amzi.mastercellusv2.ui.theme.Purple40


@Composable
fun DetailsScreen(toString: String) {
    Log.d("AMZI: ","hello b + $toString")

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SKAIO - DETAILS",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = Purple40,
            modifier = Modifier.clickable {
//                mNavigator.navigateTo(Screens.Splash.passID())
//                navHostController.navigate(Screens.Auth.passID())
//                {
//                    popUpTo(Screens.Home.route){
//                        inclusive = true
//                    }
//                }
            }

        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DetailsScreenPreview(){

    DetailsScreen("bcv")

}