package com.example.demo.ui.mushroom

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.mushroomapplication.R
import com.example.demo.viewmodels.SecondViewModel

@Composable
fun Energy(
    navController: NavHostController? = null,
    secondViewModel: SecondViewModel ,
//    startDestination: String
) {

    // Lock the screen orientation to portrait mode
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    //for set presetValue
    val maxTemperature = remember { mutableStateOf("") }
    val maxHumidity = remember { mutableStateOf("") }
    val maxCo2 = remember { mutableStateOf("") }

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
                    , verticalAlignment = Alignment.CenterVertically){
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
                            navController?.popBackStack()

                                  },
                        modifier = Modifier
                            .padding(4.dp)
                            .size(36.dp)

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.chome),
                            contentDescription = "Setting button",
                            modifier = Modifier
                                .size(36.dp)


                        )
                    }
                }
                Row{
                    IconButton(
                        onClick = {
                            navController?.navigate("secondmushroom")
                            {
                                navController.popBackStack("energyMushroom", inclusive = true)
                            }

                        },
                        modifier = Modifier
                            .padding(4.dp)
                            .size(36.dp)

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.csetting),
                            contentDescription = "Energy button",
                            modifier = Modifier
                                .size(36.dp)


                        )
                    }
                }
              }
            }

            Spacer(modifier = Modifier.height(10.dp))
    //Set Values surface
                Surface(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp)
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.setmush),
                            contentDescription = null, // Provide a content description
                            modifier = Modifier
                                .fillMaxSize()
                                .height(420.dp),
                            contentScale = ContentScale.Crop

                        )
                        Column {
                            Row{
                                Text(
                                    text = "Energy Meter  ",
                                    fontSize = 26.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(start = 20.dp, top = 8.dp)
                                )
                                Image(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .padding(top = 8.dp),
                                    painter = painterResource(id = R.drawable.ic_wifi),
                                    contentDescription = "wifi", // Provide a content description
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider(color = Color.LightGray)

                            Spacer(modifier = Modifier.height(24.dp))


                      Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

                          // Energy 1
                          Row {
                              Text(
                                  text = "E1  :",
                                  fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                  color = Color.Black,
                                  fontWeight = FontWeight.Bold,
                                  modifier = Modifier.padding(start = 20.dp)
                              )
                              Text(
                                  text = "0",
                                  fontSize = 22.sp,
//                            color = Color(0xFF90EE90),
                                  color = Color(0xFF36923A),
                                  fontWeight = FontWeight.Bold,
                                  modifier = Modifier.padding(start = 22.dp)

                              )
                              Spacer(modifier = Modifier.width(49.dp))
                              Text(
                                  text = "kWh",
                                  fontSize = 25.sp,
//                                color = Color(0xFF90EE90),
                                  color = Color.Gray,
                                  fontWeight = FontWeight.Bold

                              )
                          }

                          Spacer(modifier = Modifier.height(16.dp))
                          //Energy 2
                          Row {
                              Text(
                                  text = "E2  :",
                                  fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                  color = Color.Black,
                                  fontWeight = FontWeight.Bold,
                                  modifier = Modifier.padding(start = 20.dp)
                              )
                              Text(
                                  text = "0",
                                  fontSize = 22.sp,
//                            color = Color(0xFF90EE90),
                                  color = Color(0xFF36923A),
                                  fontWeight = FontWeight.Bold,
                                  modifier = Modifier.padding(start = 22.dp)

                              )
                              Spacer(modifier = Modifier.width(49.dp))
                              Text(
                                  text = "kWh",
                                  fontSize = 25.sp,
//                                color = Color(0xFF90EE90),
                                  color = Color.Gray,
                                  fontWeight = FontWeight.Bold

                              )
                          }
                          Spacer(modifier = Modifier.height(16.dp))
                          //Energy 3
                          Row {
                              Text(
                                  text = "E3  :",
                                  fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                  color = Color.Black,
                                  fontWeight = FontWeight.Bold,
                                  modifier = Modifier.padding(start = 20.dp)
                              )
                              Text(
                                  text = "0",
                                  fontSize = 22.sp,
//                            color = Color(0xFF90EE90),
                                  color = Color(0xFF36923A),
                                  fontWeight = FontWeight.Bold,
                                  modifier = Modifier.padding(start = 22.dp)

                              )
                              Spacer(modifier = Modifier.width(49.dp))
                              Text(
                                  text = "kWh",
                                  fontSize = 25.sp,
//                                color = Color(0xFF90EE90),
                                  color = Color.Gray,
                                  fontWeight = FontWeight.Bold

                              )
                          }
                          Spacer(modifier = Modifier.height(16.dp))
                          //Energy 4
                          Row {
                              Text(
                                  text = "E4  :",
                                  fontSize = 22.sp,
//                                color = Color(0xFF90EE90),
                                  color = Color.Black,
                                  fontWeight = FontWeight.Bold,
                                  modifier = Modifier.padding(start = 20.dp)
                              )
                              Text(
                                  text = "0",
                                  fontSize = 22.sp,
//                            color = Color(0xFF90EE90),
                                  color = Color(0xFF36923A),
                                  fontWeight = FontWeight.Bold,
                                  modifier = Modifier.padding(start = 22.dp)

                              )
                              Spacer(modifier = Modifier.width(49.dp))
                              Text(
                                  text = "kWh",
                                  fontSize = 25.sp,
//                                color = Color(0xFF90EE90),
                                  color = Color.Gray,
                                  fontWeight = FontWeight.Bold

                              )
                          }


                          //RESET BUTTON
                          Row(
                              modifier = Modifier
                                  .padding(7.dp)
                                  .align(Alignment.End),
                          ) {
                              Surface(
                                  modifier = Modifier
                                      .padding(10.dp),
                                  color = Color(0xFF0F158F),
                                  shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                                  border = BorderStroke(width = 1.dp, color = Color.LightGray)
                              ) {
                                  ClickableText(
                                      text = AnnotatedString("       RESET"),
                                      style = TextStyle(
//                                        color = Color(0xFF90EE90),
                                          color = Color.White,
                                          fontSize = 20.sp,
                                          fontWeight = FontWeight.Bold,
                                      ),
                                      modifier = Modifier
                                          .padding(top = 15.dp)
                                          .size(120.dp, 37.dp),
                                      onClick = {


                                      }
                                  )
                              }
                          }
                          Spacer(modifier = Modifier.height(7.dp))
                      }
                        }
                    }
                }
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
