package com.amzi.mastercellusv2.allScreens.authScreens

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.amzi.mastercellusv2.components.PasswordTextFiled
import com.amzi.mastercellusv2.components.SmallInputText
import com.amzi.mastercellusv2.components.SmallInputText2
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.ui.theme.lightBlack
import com.amzi.mastercellusv2.ui.theme.orange
import com.amzi.mastercellusv2.utility.mFont
import com.amzi.mastercellusv2.utility.myComponents
import com.amzi.mastercellusv2.utility.showLogs
import com.example.demo.ui.theme.Grey
import com.example.demo.ui.theme.red

@Preview
@Composable
fun Verification(){

    var username by remember { mutableStateOf(myComponents.registerViewModel.username.value) }
    var mobNum by remember { mutableStateOf(myComponents.registerViewModel.mobNum.value) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 12.dp, top = 16.dp, end = 12.dp)
    ) {

        Header(text1 = "Verification",
            text2 = "Join our community and experience seamless integration of our devices"
        )

        Spacer(modifier = Modifier.height(36.dp))

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

        InputText(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = mobNum,
            color = Color.Black,
            label = "Enter phone number",
            maxLine = 1,
            iconResId = R.drawable.phone,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number),
            onTextChange = { mobNum = it },
            maxLength = 10
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(text = "Password",
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Grey,
                fontFamily = mFont.outfitRegular
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordTextFiled(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = password,
            color = Color.Black,
            label = "Password",
            maxLine = 1,
            iconResId = R.drawable.email,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text),
            onTextChange = { password = it },
            maxLength = 30
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordTextFiled(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = confirmPassword,
            color = Color.Black,
            label = "Confirm Password",
            maxLine = 1,
            iconResId = R.drawable.email,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text),
            onTextChange = { confirmPassword = it },
            maxLength = 30
        )

        Spacer(modifier = Modifier.height(4.dp))

        if (password != confirmPassword) {
            Text(
                text = "Passwords do not match",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = mFont.outfitRegular,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                textAlign = TextAlign.End,
                color = red
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        SmallInputText(
                text = otp,
                color = Color.Black,
                label = "Enter OTP",
                maxLine = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text),
                onTextChange = { otp = it },
                maxLength = 15
            )

        Spacer(modifier = Modifier.height(28.dp))

        Text(text = "Click Verify to proceed",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Grey,
                fontFamily = mFont.outfitRegular
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton("Verify"){

            myComponents.registerViewModel.verify(mobNum, password, confirmPassword, otp)

            showLogs("VERIFICATION", "Screen verified")

        }

        Row  (modifier = Modifier.padding(top = 32.dp)){
            Text(

                text = "Already have an account?",
                style = TextStyle(
                    fontSize = 13.sp,
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