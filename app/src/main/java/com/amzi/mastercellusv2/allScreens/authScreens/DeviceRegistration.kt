package com.amzi.mastercellusv2.allScreens.authScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.R
import com.amzi.mastercellusv2.components.CustomButton
import com.amzi.mastercellusv2.components.Header
import com.amzi.mastercellusv2.components.InputText
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.ui.theme.orange
import com.amzi.mastercellusv2.utility.mFont
import com.amzi.mastercellusv2.utility.myComponents

@Preview
@Composable
fun DeviceRegistration(){

    var username by remember { mutableStateOf(myComponents.registerViewModel.username.value) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var mobNum by remember { mutableStateOf("") }
    var macID by remember { mutableStateOf("") }
    var deviceName by remember { mutableStateOf("None") }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 12.dp, top = 16.dp, end =  12.dp)
    ) {

        Header(text1 = "Register User",
            text2 = "If you have purchased any device, make sure to register it here"
        )
        Spacer(modifier = Modifier.height(36.dp))

        InputText(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = macID,
            color = Color.Black,
            label = "Enter Mac ID",
            iconResId = R.drawable.ic_mac,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text),
            onTextChange = { macID = it },
            maxLength = 30
        )

        Spacer(modifier = Modifier.height(12.dp))

        InputText(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = mobNum,
            color = Color.Black,
            label = "Phone number",
            maxLine = 1,
            iconResId = R.drawable.phone,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number),
            onTextChange = { mobNum = it },
            maxLength = 10
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomButton("Register"){

            TODO() //RUN REGISTER API

        }

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            modifier = Modifier
                .clickable { mNavigator.navigateTo(Screens.DeviceList.route) },
            text = " Go Back",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = mFont.outfitRegular,
                fontWeight = FontWeight.SemiBold
            ),
            color = orange
        )

    }
}
