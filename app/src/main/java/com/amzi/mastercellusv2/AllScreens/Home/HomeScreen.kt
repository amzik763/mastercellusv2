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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.amzi.mastercellusv2.AllViewModels.UiViewmodel
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
import com.example.homeapplication.ui.theme.DarkOrange

@Composable
fun HomeScreen(
//  navHostController: NavHostController

    uiViewModel: UiViewmodel
) {

    var ct = LocalContext.current
    Log.d("AMZI: ","hello a")

    Column(
        Modifier
            .fillMaxSize()

    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
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
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
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
            .padding(start = 36.dp, end = 28.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Row (
                verticalAlignment = Alignment.CenterVertically){
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

                Column (modifier = Modifier
                    .padding(start = 12.dp)
                    .clickable {
                        // Toggle showMacId when clicked
                        uiViewModel.toggleHomeMacIdVisibility()

                    }){
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
                            color = Gray,
                            fontStyle = FontStyle.Italic)
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
        Spacer(modifier = Modifier.height(10.dp))
        if (uiViewModel.showHomeMacId) {
            MacId()
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier
            .padding(start = 36.dp, end = 28.dp)
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
                    painter = painterResource(id = com.amzi.mastercellusv2.R.drawable.ic_mushroom),
                    contentDescription = "Mushroom",
                    contentScale = ContentScale.Crop,
                )

                Column (modifier = Modifier.padding(start = 12.dp)
                    .clickable {
                        uiViewModel.toggleMushMacIdVisibility()
                    }){
                    Text(
                        text = "Mushroom",
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
                            color = Gray,
                            fontStyle = FontStyle.Italic
                        )
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
                        var a = Intent(ct, com.android.mushroomapplication.MainActivity::class.java)
                        startActivity(ct, a, null)

                        mNavigator.navigateTo(Screens.Detail.passNameandID("abc", "amzad"))
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (uiViewModel.showMushMacId) {
            MacId()
        }

/*        Text(
            text = "HOME AUTO",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Purple40,
            modifier = Modifier.clickable {
                mNavigator.navigateTo(Screens.HomeAutomation.route)
//                navHostController.navigate(route = Screens.Detail.passNameandID("abc","amzad"))
            }
        )*/

/*
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
*/

/*        Text(
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

        )*/


/*        Text(
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

        )*/


    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview(){

//   HomeScreen()
}


@Composable
fun MacId(){

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)
            .border(1.dp, color = lightGrey, shape = RoundedCornerShape(8.dp))
    ) {
        Column (modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Enter Device ID:",
                    fontSize = 14.sp,
                    color = lightBlack)

                Text(text = "HIDE",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Gray
                )
            }
            var macId by remember { mutableStateOf("") }

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 4.dp, end = 24.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){

                OutlinedTextField(colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = lightBlack,
                    unfocusedBorderColor = Grey,
                    focusedLabelColor = lightBlack,
                    textColor = lightBlack
                ),value = macId,
                    onValueChange ={ newText -> macId = newText },
                    shape = RoundedCornerShape(8.dp),
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(56.dp),
                    label = {
                        Text(text = "Enter MacId",
                            style = TextStyle(
                                fontSize = 10.sp),
                            color = lightBlack,
                            modifier = Modifier.padding(0.dp))
                    },
                )
                Surface(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(width = 64.dp, height = 36.dp),
                    color = lightBlue,
                    shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                    border = BorderStroke(width = 4.dp, color = lightBlue),
                ) {
                    ClickableText(
                        text = AnnotatedString("SET"),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(11.dp),
                        onClick = {

                        }
                    )
                }
            }
        }
    }
}

