package com.amzi.mastercellusv2.allScreens.starterScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.R
import com.cti.softStarter.components.WIInputText

@Preview
@Composable
fun Verify(){

    Column( modifier = Modifier

        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                painter = painterResource(id = R.drawable.skaio),
                contentDescription = "Skaio Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(width = 220.dp, height = 55.dp)
            )
            Text(
                text = "Register Your Starter",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(28.dp)
            )
        }

        Column (modifier = Modifier
            .padding(top = 36.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween){

            Otp()

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 56.dp, end = 56.dp, top = 30.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(3.dp, Color.Blue),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Blue
                ),
            ) {
                Text(
                    text = "VERIFY",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
            }
            Row(modifier = Modifier.padding(top = 200.dp)){
                Text(
                    text = "Cancel",
                    style = TextStyle(fontSize = 12.sp),
                    color = Color(0xFF7E8385)
                )
            }
        }
    }
}


@Composable
fun Otp(){
    var otp by remember { mutableStateOf("") }

    WIInputText(
        text = otp,
        label = "Enter OTP",
        onTextChange = { otp = it} ,
        color = Color.Black,
        maxLength = 4,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    Messages(message = "4 digit OTP sent to your mobile no*",
        text = "0/4"
    )
}


@Composable
fun Messages(message: String, text: String){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(start = 64.dp, end = 64.dp, top = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        Text(
            text = message,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 12.sp
            )
        )

        Text(
            text = text,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 12.sp
            )
        )
    }
}
