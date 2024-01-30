package com.amzi.mastercellusv2.AllScreens.Home

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.ui.theme.Purple40
import com.amzi.mastercellusv2.utility.mGraph
import com.android.mushroomapplication.R
import com.example.homeapplication.navigation.Navigation

@Composable
fun HomeScreen(
//  navHostController: NavHostController
) {

    var ct = LocalContext.current
    Log.d("AMZI: ","hello a")

    Column(
        Modifier.fillMaxSize(),
    ) {

        Row (modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Image(
                modifier = Modifier
//                    .padding(8.dp)
                    .size(height = 64.dp, width = 64.dp),
                painter = painterResource(id = R.drawable.skaio),
                contentDescription = "My Icon",
            )

            Row {
                Image(
                    modifier = Modifier
//                    .padding(8.dp)
                        .size(height = 24.dp, width = 36.dp),
                    painter = painterResource(id = com.amzi.mastercellusv2.R.drawable.usericon),
                    contentDescription = "User",
                )

                Image(
                    modifier = Modifier
//                    .padding(8.dp)
                        .size(height = 24.dp, width = 36.dp),
                    painter = painterResource(id = com.amzi.mastercellusv2.R.drawable.usericon),
                    contentDescription = "User",
                )
            }
        }
        Text(
            text = "HOME AUTO",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Purple40,
            modifier = Modifier.clickable {
                mNavigator.navigateTo(Screens.HomeAutomation.route)
//                navHostController.navigate(route = Screens.Detail.passNameandID("abc","amzad"))
            }
        )
        Text(
            text = "Mushroom",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = Purple40,
            modifier = Modifier
                .padding(36.dp)
                .clickable {
                    var a = Intent(ct, com.android.mushroomapplication.MainActivity::class.java)
                    startActivity(ct, a, null)

                    mNavigator.navigateTo(Screens.Detail.passNameandID("abc", "amzad"))
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