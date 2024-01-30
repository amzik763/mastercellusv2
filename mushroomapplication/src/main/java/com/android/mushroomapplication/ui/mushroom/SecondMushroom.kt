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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.mushroomapplication.R
import com.example.demo.viewmodels.SecondViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Title2(navController: NavHostController? = null,secondViewModel: SecondViewModel) {

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
//                                navController?.navigate("FirstMushroom")
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
//                            navController?.popBackStack()
                            navController?.navigate("energyMushroom"){
                                navController.popBackStack("secondmushroom", inclusive = true)
                            }

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
                                    text = "Set Data  ",
                                    fontSize = 26.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(start = 20.dp, top = 8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider(color = Color.LightGray)
                            Spacer(modifier = Modifier.height(10.dp))
                            //Max Temperature Row
                            val pattern = remember { Regex("^\\d+\$") }
                            Column{
                                Text(
                                    text = "Set Temperature",
                                    color = Color.Black,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.padding(start = 20.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(modifier = Modifier.padding(start = 20.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .size(130.dp, 55.dp)
                                            .shadow(
                                                6.dp,
                                                shape = RoundedCornerShape(10.dp)
                                            ) // Adjust the shadow parameters as needed
                                    ) {

                                        TextField(
                                            shape = RoundedCornerShape(10.dp),
                                            maxLines = 1,
                                            value = maxTemperature.value,
                                            onValueChange = { newText ->
                                                if (newText.matches(pattern) && newText.length <= 3) {
                                                    maxTemperature.value = newText
                                                } else if (newText.length == 0) {
                                                    maxTemperature.value = ""
                                                    //   maxTemperature.value = newText.take(3)
                                                }

                                            },
                                            placeholder = {
                                                Text(
                                                    text = secondViewModel.maxTemperature,
                                                    //textAlign = TextAlign.Center
                                                )
                                            },
                                            modifier = Modifier.size(130.dp, 55.dp),
                                            textStyle = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,

                                                ),
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(
                                        text = "°C",
                                        fontSize = 22.sp,
                                        modifier = Modifier.padding(top = 10.dp),
                                        color = Color.Gray,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            //Max Humidity Row
                            val pattern2 = remember { Regex("^\\d+\$") }
                            Column {
                                Text(text = "Set Humidity",
                                    color = Color.Black,
                                    style = TextStyle(fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold),
                                    modifier = Modifier.padding(start = 20.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(modifier = Modifier.padding(start = 20.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .size(130.dp, 55.dp)
                                            .shadow(
                                                6.dp,
                                                shape = RoundedCornerShape(10.dp)
                                            ) // Adjust the shadow parameters as needed
                                    ) {
                                        TextField(
                                            shape = RoundedCornerShape(10.dp),
                                            maxLines = 1,
                                            value = maxHumidity.value,
                                            onValueChange = { newText ->
                                                if (newText.matches(pattern2) && newText.length <= 3) {
                                                    maxHumidity.value = newText
                                                } else if (newText.length == 0) {
                                                    maxHumidity.value = ""
                                                    //   maxTemperature.value = newText.take(3)
                                                }
                                            },
                                            placeholder = { Text(text = secondViewModel.maxHumidity) },
                                            modifier = Modifier.size(130.dp, 55.dp),
                                            textStyle = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                                            )
                                    }
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(
                                        text = "%",
                                        fontSize = 30.sp,
                                        modifier = Modifier.padding(top = 10.dp),
                                        color = Color.Gray,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            //Max Co2 Row
                            val pattern3 = remember { Regex("^\\d+\$") }
                            Column{
                                Text(text = "Set Co2",
                                    color = Color.Black,
                                    style = TextStyle(fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold),
                                    modifier = Modifier.padding(start = 20.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Row(modifier = Modifier.padding(start = 20.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .size(130.dp, 55.dp)
                                            .shadow(6.dp, shape = RoundedCornerShape(10.dp)
                                            ) // Adjust the shadow parameters as needed
                                    ) {
                                        TextField(
                                            shape = RoundedCornerShape(10.dp),
                                            maxLines = 1,
                                            value = maxCo2.value,
                                            onValueChange = { newText ->
                                                if (newText.matches(pattern3) && newText.length <= 5) {
                                                    maxCo2.value = newText
                                                } else if (newText.length == 0) {
                                                    maxCo2.value = ""
                                                    //   maxTemperature.value = newText.take(3)
                                                }
                                            },
                                            placeholder = { Text(text = secondViewModel.maxCo2) },
                                            modifier = Modifier.size(130.dp, 55.dp),
                                            textStyle = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "PPM",
                                        fontSize = 22.sp,
                                        modifier = Modifier.padding(top = 10.dp),
                                        color = Color.Gray,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }
                            //SET BUTTON
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
                                        text = AnnotatedString("        SET"),
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
//                            secondViewModel.pubCo2(maxCo2.value)
//                            secondViewModel.pubHumidity(maxHumidity.value)
                                            secondViewModel.pubTempHumidCo2(
                                                maxTemperature.value,
                                                maxHumidity.value,
                                                maxCo2.value
                                            )
                                            navController?.popBackStack()
                                        }
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(7.dp))
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview2(){
    Surface(Modifier.fillMaxSize()) {
//        Title2()
    }

}