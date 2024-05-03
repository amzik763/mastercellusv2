package com.amzi.mastercellusv2.AllScreens.starterScreens

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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
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
import com.amzi.mastercellusv2.components.InputText

@Preview
@Composable
fun Header(){

    Column( modifier = Modifier

        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {

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
            .padding(top = 16.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween){

            DeviceCode()

            Spacer(modifier = Modifier.height(12.dp))

            GiveName()

            Spacer(modifier = Modifier.height(20.dp))

            Checkboxes()

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
                    text = "GET OTP",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
            }
            Row(modifier = Modifier.padding(top = 70.dp)){
                Text(
                    text = "Don't have it? ",
                    style = TextStyle(fontSize = 12.sp),
                    color = Color(0xFF7E8385)
                )
                Text(
                    text = "purchase here",
                    style = TextStyle(fontSize = 12.sp,
                        fontWeight = FontWeight.Bold),
                    color = Color(0xFF7E8385)
                )
            }
        }
    }
}


@Composable
fun DeviceCode(){
    var deviceCode by remember { mutableStateOf("") }

    InputText(
        text = deviceCode,
        label = "Device Code",
        onTextChange = { deviceCode = it} ,
        color = Color.Black,
        iconResId = R.drawable.ic_device,
        maxLength = 15,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    Message(message = "15 digit number printed on device",
        text = "0/15"
    )
}

@Composable
fun GiveName(){
    var giveName by remember { mutableStateOf("") }

    InputText(
        text = giveName,
        label = "Give a Name",
        onTextChange = { giveName = it} ,
        color = Color.Black,
        iconResId = R.drawable.ic_car,
        maxLength = 20,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    Message(message = "Example My Car optional",
        text = "0/20"
    )
}


@Composable
fun Message(message: String, text: String){
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


@Composable
fun Checkboxes(){

    val checkbox = remember {
        mutableStateOf(false)
    }

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(start = 48.dp),
        verticalAlignment = Alignment.CenterVertically
        ){
        Checkbox(checked = checkbox.value,
            onCheckedChange = {checkbox.value = it},
            modifier = Modifier.padding(5.dp),
            colors = CheckboxDefaults.colors(Color(64,224,208))
        )
        Text(text = "Set it Default?")
    }
}
