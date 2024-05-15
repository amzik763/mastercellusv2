package com.amzi.mastercellusv2.AllScreens.authScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
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
import com.amzi.mastercellusv2.utility.showLogs
import com.amzi.mastercellusv2.components.InputText
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel


@Composable
fun SetPasswordScreen() {


    showLogs("SetPassword: ", registerViewModel.mobNum)

    var mobNum by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
//            .padding(10.dp)
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Icon(
            painter = painterResource(id = R.drawable.skaio),
            contentDescription = "Skaio Icon",
            tint = Color.Unspecified,

            modifier = Modifier.size(width = 220.dp, height = 55.dp)
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = "Set Password",
            style = TextStyle(
                fontSize = 39.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

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
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Text(
                text = "Required*",
                modifier = Modifier.padding(start = 9.dp),
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 12.sp)
            )
            Text(
                text = "0/10",
                modifier = Modifier.padding(start = 179.dp),
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 13.sp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text(text = "Enter OTP") },
            maxLines = 1,

            )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Text(
                text = "4 digit OTP sent to your mobile no*",
                modifier = Modifier.padding(start = 9.dp),
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 12.sp)
            )
            Text(
                text = "0/4",
                modifier = Modifier.padding(start = 44.dp),
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 13.sp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        InputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp
            ),
            text = password,
            color = Color.Black,
            label = "New Password",
            iconResId = R.drawable.lock,
            onTextChange = { password = it },
            maxLength = 20,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Text(
                text = "Min 8 characters are required*",
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 12.sp)
            )
            Text(
                text = "0/20",
                modifier = Modifier.padding(start = 70.dp),
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 13.sp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        InputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp
            ),
            text = confirmPassword,
            color = Color.Black,
            label = "Confirm Password",
            iconResId = R.drawable.lock,
            onTextChange = { confirmPassword = it },
            maxLength = 20,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Text(
                text = "Required*",
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 12.sp)
            )
            Text(
                text = "0/20",
                modifier = Modifier.padding(start = 179.dp),
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 13.sp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Surface(
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp),
//            color = Color(0xFF0E86BD),
            color = Color.Blue,
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ) {
            ClickableText(
                text = AnnotatedString("SET"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(9.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    registerViewModel.setPassword("register",mobNum, otp, password, confirmPassword)
                }
            )
        }
        Row(modifier = Modifier.padding(top = 15.dp, start = 155.dp)) {
            Text(
                text = "Resend OTP in 30 sec",
                style = TextStyle(fontSize = 12.sp),
                color = Color(0xFF7E8385)
            )

        }

        Text(
            modifier = Modifier.padding(top = 60.dp),
            text = "Cancel",
            style = TextStyle(
                fontSize = 12.sp,
            ),
            color = Color(0xFF7E8385)
        )


    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SetPasswordScreenPreview(){

//    setPasswordScreen(
////        navHostController = rememberNavController()
//    )

}