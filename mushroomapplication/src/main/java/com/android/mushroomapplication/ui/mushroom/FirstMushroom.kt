package com.example.demo.ui.mushroom

import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.mushroomapplication.R
import com.example.demo.ui.mqtt.MqttClientHelper.Companion.TAG
import com.example.demo.viewmodels.SecondViewModel

@Composable
fun Title(navController: NavHostController? = null, secondViewModel: SecondViewModel) {

    // Lock the screen orientation to portrait mode
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    Log.w(TAG, "checking..........................")

    val wname by secondViewModel.wname.collectAsState()

    //new variable
    val temp2 by secondViewModel.temp2.collectAsState()
    val hum2 by secondViewModel.hum2.collectAsState()
    val co22 by secondViewModel.co22.collectAsState()

    val maxtemp2 by secondViewModel.maxtemp2.collectAsState()
    val maxhum2 by secondViewModel.maxhum2.collectAsState()
    val maxco22 by secondViewModel.maxco22.collectAsState()

    var temp = secondViewModel.temperature
    val humid = secondViewModel.humidity
    val co2 = secondViewModel.co2

    val presetTemp = secondViewModel.maxTemperature
    val presetHumid = secondViewModel.maxHumidity
    val presetCo2 = secondViewModel.maxCo2


    Log.w(TAG, "checking")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.bg),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){

                Row(modifier = Modifier
                    .background(color = Color.White)
                    ,verticalAlignment = Alignment.CenterVertically){
            //Cti Logo
                        Image(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(height = 36.dp, width = 36.dp),
                            painter = painterResource(id = R.drawable.ctilogo),
                            contentDescription = "My Icon",
                        )

                    Text(modifier = Modifier.padding(top = 5.dp),
                        text = "SKIAO",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 26.sp)
                }

                Row(modifier = Modifier.padding(end = 12.dp)) {

                    Row{
                        IconButton(
                            onClick = {
                                navController?.navigate("secondmushroom")

//
                            },
                            modifier = Modifier
                                .padding(4.dp)
                                .size(36.dp)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.csetting),
                                contentDescription = "Setting button",
                                modifier = Modifier
                                    .size(36.dp)


                            )
                        }
                    }
                    Row{
                        IconButton(
                            onClick = {
                            navController?.navigate("energyMushroom")
//
                            },
                            modifier = Modifier
                                .padding(4.dp)
                                .size(36.dp)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_electrical),
                                contentDescription = "Energy button",
                                modifier = Modifier
                                    .size(36.dp)


                            )
                        }
                    }
                }


            }
//box for icon
            Spacer(modifier = Modifier.height(10.dp))


            Surface(
                modifier = Modifier
                    .padding(14.dp)
                    //.shadow(8.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                border = BorderStroke(width = 1.dp, color = Color.LightGray)
            ) {
                // Use a Box to layer the background image and other content
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    // Set the background image using the painterResource function
                    Image(
                        painter = painterResource(id = R.drawable.mushup),
                        contentDescription = null, // Provide a content description
                        modifier = Modifier
                            .fillMaxSize()
                            .height(200.dp),
                            contentScale = ContentScale.Crop
//                            .padding(1.dp)// Fill the entire Surface
                    )
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center

                    ) {
                        Row{
                            Text(
                                text = "Live Data  ",
                                fontSize = 26.sp,
//                        color = Color(0xFF90EE90),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp, top = 8.dp)
                            )
                            Image(
                                modifier = Modifier
                                    .size(42.dp)
                                    .padding(top = 8.dp)
                                    .clickable {
                                        navController?.navigate("wifi")
                                    },
                                painter = painterResource(id = R.drawable.ic_wifi),
                                contentDescription = "wifi", // Provide a content description

                            )
                            Text(
                                text = wname,
                                fontSize = 18.sp,
//                        color = Color(0xFF90EE90),
                                color = Color.Black,
                                modifier = Modifier.padding(start = 10.dp, top = 13.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(color = Color.LightGray)

                        Spacer(modifier = Modifier.height(10.dp))
                        //Live data Temperature
                        Row {
                            Text(
                                text = "Temp :",
                                fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                            Text(
                                text = temp2,
                                fontSize = 22.sp,
//                            color = Color(0xFF90EE90),
                                color = Color(0xFF36923A),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 22.dp)

                            )
                            Spacer(modifier = Modifier.width(45.dp))
                            Text(
                                text = "°C",
                                fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold

                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        //Live data humidity
                        Row {
                            Text(
                                text = "Humi  :",
                                fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                            Text(
                                text = hum2,
                                fontSize = 22.sp,
//                            color = Color(0xFF90EE90),
                                color = Color(0xFF36923A),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 22.dp)

                            )
                            Spacer(modifier = Modifier.width(49.dp))
                            Text(
                                text = "%",
                                fontSize = 25.sp,
//                                color = Color(0xFF90EE90),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold

                            )
                        }
                        Spacer(modifier = Modifier.height(7.dp))
                        //Live data Co2
                        Row {
                            Text(
                                text = "Co2     :",
                                fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                            Text(
                                text = co22,
                                fontSize = 22.sp,
//                            color = Color(0xFF90EE90),
                                color = Color(0xFF36923A),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                            Spacer(modifier = Modifier.width(44.dp))
                            Text(
                                text = "PPM",
                                fontSize = 22.sp,
//                                color = Color(0xFF8B8B8B),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
            //Preset Data Surface
            Surface(
                modifier = Modifier
                    .padding(14.dp)
                    //.shadow(8.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                border = BorderStroke(width = 1.dp, color = Color.LightGray)
            ) {
                // Use a Box to layer the background image and other content
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    // Set the background image using the painterResource function
                    Image(
                        painter = painterResource(id = R.drawable.mushd),
                        contentDescription = null, // Provide a content description
                        modifier = Modifier
                            .fillMaxSize()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
//                            .padding(1.dp)// Fill the entire Surface
                    )
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center

                    ) {
                        Row{
                            Text(
                                text = "Preset Data  ",
                                fontSize = 26.sp,
//                        color = Color(0xFF90EE90),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp, top = 8.dp)
                            )
//                            Image(
//                                modifier = Modifier
//                                    .size(42.dp)
//                                    .padding(top = 8.dp),
//                                painter = painterResource(id = R.drawable.ic_wifi),
//                                contentDescription = "wifi", // Provide a content description
//
//                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(color = Color.LightGray)

                        Spacer(modifier = Modifier.height(10.dp))
                        //Preset data Temperature
                        Row {
                            Text(
                                text = "Temp :",
                                fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                            Text(
                                text = maxtemp2,
                                fontSize = 22.sp,
//                            color = Color(0xFF90EE90),
                                color = Color(0xFF36923A),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 22.dp)

                            )
                            Spacer(modifier = Modifier.width(45.dp))
                            Text(
                                text = "°C",
                                fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold

                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        //Preset data humidity
                        Row {
                            Text(
                                text = "Humi  :",
                                fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                            Text(
                                text = maxhum2,
                                fontSize = 22.sp,
//                            color = Color(0xFF90EE90),
                                color = Color(0xFF36923A),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 22.dp)

                            )
                            Spacer(modifier = Modifier.width(49.dp))
                            Text(
                                text = "%",
                                fontSize = 25.sp,
//                                color = Color(0xFF90EE90),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold

                            )
                        }
                        Spacer(modifier = Modifier.height(7.dp))
                        //Preset data Co2
                        Row {
                            Text(
                                text = "Co2     :",
                                fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                            Text(
                                text = maxco22,
                                fontSize = 22.sp,
//                             color = Color(0xFF90EE90),
                                color = Color(0xFF36923A),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                            Spacer(modifier = Modifier.width(44.dp))
                            Text(
                                text = "PPM",
                                fontSize = 22.sp,
//                                color = Color(0xFF8B8B8B),
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            //contact info
            Surface(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                border = BorderStroke(width = 1.dp, color = Color.LightGray)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically){

                        IconButton(
                            onClick = {
//                                navController?.navigate("SecondMushroom")
                                      },
                            modifier = Modifier
                                .padding(10.dp)
                                .size(24.dp)


                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.phone),
                                contentDescription = "Setting button",
                                modifier = Modifier
                                    .padding(start = 3.dp)
                                   // .size(24.dp)


                            )

                    }
                    Column(modifier = Modifier.padding(10.dp)){

                        Text(
                            text = "Contact us",
                            fontSize = 14.sp,
                            // color = Color(0xFF90EE90),
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 0.dp)
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 0.dp, start = 0.dp)
                        ) {
                            Text(
                                text = "Z-Engineering: ",
                                fontSize = 17.sp,
                                //color = Color(0xFF90EE90),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
//                    modifier = Modifier
                                //    .padding(top = 5.dp,start = 5.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "+91 9812130714",
                                fontSize = 17.sp,
                                //  color = Color(0xFF90EE90),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,

                                )
                        }
                    }
                }
            }
        }
        }
    }
@Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Surface(Modifier.fillMaxSize()) {
            // Title(null,)
    }
}

