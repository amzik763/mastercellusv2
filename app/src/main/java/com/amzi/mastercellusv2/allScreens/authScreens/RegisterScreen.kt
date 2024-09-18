package com.amzi.mastercellusv2.allScreens.authScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.R
import com.amzi.mastercellusv2.components.CustomButton
import com.amzi.mastercellusv2.components.Header
import com.amzi.mastercellusv2.components.InputText
import com.amzi.mastercellusv2.components.SmallInputText
import com.amzi.mastercellusv2.components.SmallInputText2
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.ui.theme.blue
import com.amzi.mastercellusv2.ui.theme.lightBlack
import com.amzi.mastercellusv2.ui.theme.orange
import com.amzi.mastercellusv2.utility.mFont
import com.amzi.mastercellusv2.utility.myComponents
import com.amzi.mastercellusv2.utility.showLogs
import com.example.demo.ui.theme.Grey
import com.example.demo.ui.theme.pureWhite

@Preview
@Composable
fun RegisterScreen() {

    var username by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobNum by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 12.dp, top = 16.dp, end = 12.dp)
    ) {

        Header(text1 = "Register",
            text2 = "Join our community and experience seamless integration of our devices"
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(text = "Basic",
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Grey,
                fontFamily = mFont.outfitRegular
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputText(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = username,
            color = Color.Black,
            label = "Username",
            maxLine = 1,
            iconResId = R.drawable.person,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text),
            onTextChange = { username = it },
            maxLength = 30
        )

        Spacer(modifier = Modifier.height(10.dp))
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)){
            SmallInputText(
                text = firstName,
                color = Color.Black,
                label = "First Name",
                maxLine = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text),
                onTextChange = { firstName = it },
                maxLength = 15
            )

            SmallInputText2(
                text = lastName,
                color = Color.Black,
                label = "Last Name",
                maxLine = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text),
                onTextChange = { lastName = it },
                maxLength = 15
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(text = "Account",
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Grey,
                fontFamily = mFont.outfitRegular
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputText(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = email,
            color = Color.Black,
            label = "Email Id",
            maxLine = 1,
            iconResId = R.drawable.email,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text),
            onTextChange = { email = it },
            maxLength = 30
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputText(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = mobNum,
            color = Color.Black,
            label = "Mobile Number",
            maxLine = 1,
            iconResId = R.drawable.phone,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number),
            onTextChange = { mobNum = it },
            maxLength = 10
        )


        Spacer(modifier = Modifier.height(28.dp))

        Text(text = "Click Register to proceed",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Grey,
                fontFamily = mFont.outfitRegular
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton("Register"){
            myComponents.registerViewModel.register(username, firstName, lastName, email, mobNum)
            showLogs("Register", "Registered User")
        }

        Row  (modifier = Modifier.padding(top = 32.dp)){
            Text(

                text = "Already have an account?",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = mFont.outfitRegular,
                    fontWeight = FontWeight.SemiBold

                ),
                color = lightBlack
            )
            Text(
                modifier = Modifier
                    .clickable { mNavigator.navigateTo(Screens.Login.route) },
                text = " Login",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = mFont.outfitRegular,
                    fontWeight = FontWeight.SemiBold
                ),
                color = orange
            )
        }
    }
}
