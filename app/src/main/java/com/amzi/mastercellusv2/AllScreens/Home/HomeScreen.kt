package com.amzi.mastercellusv2.AllScreens.Home

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.ui.theme.Grey
import com.amzi.mastercellusv2.ui.theme.Purple40
import com.amzi.mastercellusv2.ui.theme.extraLightGrey
import com.amzi.mastercellusv2.ui.theme.lightBlack
import com.amzi.mastercellusv2.ui.theme.lightBlue
import com.amzi.mastercellusv2.ui.theme.lightGrey
import com.amzi.mastercellusv2.ui.theme.lightOrange
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
        Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
    ) {
        Row (modifier = Modifier
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

            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    modifier = Modifier
//                    .padding(8.dp)
                        .size(height = 22.dp, width = 36.dp),
                    painter = painterResource(id = com.amzi.mastercellusv2.R.drawable.usericon),
                    contentDescription = "User",
                )

                Image(
                    modifier = Modifier
//                    .padding(8.dp)
                        .size(height = 27.dp, width = 32.dp),
                    painter = painterResource(id = com.amzi.mastercellusv2.R.drawable.ic_logout),
                    contentDescription = "User",
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "App List",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = lightBlack,
        )

        Spacer(modifier = Modifier.height(12.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(color = lightOrange)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier
            .padding(start = 24.dp, end = 16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    modifier = Modifier
//                    .padding(8.dp)
                        .size(height = 56.dp, width = 56.dp)
                        .border(0.dp, lightGrey, shape = RoundedCornerShape(4.dp))
                        .clip(RoundedCornerShape(4.dp)),
                    painter = painterResource(id = com.amzi.mastercellusv2.R.drawable.homeicon),
                    contentDescription = "Home",
                    contentScale = ContentScale.Crop,
                )

                Column (modifier = Modifier.padding(start = 12.dp)){
                    Text(
                        text = "Home Automation",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = lightBlack,
                    )
                    Row{
                        Text(
                            text = "Not Registered",
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = lightBlue,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "   Edit",
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = Color.Gray,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.clickable {
//                navHostController.navigate(route = Screens.Detail.passNameandID("abc","amzad"))
                            })
                    }
                }
            }
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(width = 64.dp, height = 24.dp),
                color = lightBlue,
                shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                border = BorderStroke(width = 4.dp, color = lightBlue),
            ) {
                ClickableText(
                    text = AnnotatedString("Open"),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    onClick = {
                        mNavigator.navigateTo(Screens.HomeAutomation.route)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier
            .padding(start = 24.dp, end = 16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    modifier = Modifier
//                    .padding(8.dp)
                        .size(height = 56.dp, width = 56.dp)
                        .border(0.dp, lightGrey, shape = RoundedCornerShape(4.dp))
                        .clip(RoundedCornerShape(4.dp)),
                    painter = painterResource(id = com.amzi.mastercellusv2.R.drawable.homeicon),
                    contentDescription = "Home",
                    contentScale = ContentScale.Crop,
                )

                Column (modifier = Modifier.padding(start = 12.dp)){
                    Text(
                        text = "Home Automation",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = lightBlack,
                    )
                    Row{
                        Text(
                            text = "Not Registered",
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = lightBlue,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "   Edit",
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = Color.Gray,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.clickable {
//                navHostController.navigate(route = Screens.Detail.passNameandID("abc","amzad"))
                            })
                    }
                }
            }
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(width = 64.dp, height = 24.dp),
                color = lightBlue,
                shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                border = BorderStroke(width = 4.dp, color = lightBlue),
            ) {
                ClickableText(
                    text = AnnotatedString("Open"),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    onClick = {
                        mNavigator.navigateTo(Screens.HomeAutomation.route)
                    }
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