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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.AllViewModels.RegisterViewModel
import com.amzi.mastercellusv2.R
import com.amzi.mastercellusv2.utility.showLogs
import com.android.updatedsoftstarter.components.LoginInputText
@Composable
fun forgotPasswordScreen(viewModel: RegisterViewModel) {

    val registerViewModel = viewModel

    showLogs("forgotPassword: ", registerViewModel.mobNum)

    var mobNum by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
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
           text = "Verify",
            style = TextStyle(
                fontSize = 39.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        LoginInputText(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = mobNum,
            color = Color.Black,
            label = "Mobile Number",
            iconResId = R.drawable.phone,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onTextChange = { mobNum = it },
            maxLength = 10
        )
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = "Required*",
                style = TextStyle(fontSize = 14.sp,
                    fontWeight = FontWeight.Bold),
                color = Color(0xFF7E8385)
            )
            Text(
                text = "0/10",
                modifier = Modifier.padding(start = 170.dp),
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 15.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(35.dp))

        Surface(
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp)
                .align(Alignment.CenterHorizontally),
//            color = Color(0xFF0E86BD),
            color = Color.Blue,
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ) {
            ClickableText(
                text = AnnotatedString("GET OTP"),
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
                    registerViewModel.forgotPassword(mobNum)
                }
            )
        }

        Row(modifier = Modifier.padding(top = 185.dp)){
            Text(
                text = "Cancel",
                style = TextStyle(fontSize = 12.sp,
                fontWeight = FontWeight.Bold),
                color = Color(0xFF7E8385)
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ForgotPasswordScreenPreview(){
//    forgotPasswordScreen(
////        navHostController = rememberNavController()
// )
}