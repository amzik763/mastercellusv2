package com.amzi.mastercellusv2.allScreens.authScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.R
import com.amzi.mastercellusv2.components.CustomButton
import com.amzi.mastercellusv2.components.Header
import com.amzi.mastercellusv2.components.InputText
import com.amzi.mastercellusv2.components.PasswordTextFiled
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.ui.theme.lightBlack
import com.amzi.mastercellusv2.ui.theme.orange
import com.amzi.mastercellusv2.utility.mFont
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel
import com.example.demo.ui.theme.Grey


/*@Composable
fun LoginScreen(viewModel: RegisterViewModel) {

    val ct = LocalContext.current
    val registerViewModel = viewModel

    showLogs("login: ", registerViewModel.mobNum.toString())

    var mobNum by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            text = "Login",
            style = TextStyle(
                fontSize = 39.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        InputText(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = mobNum,
            color = Color.Black,
            label = "Mobile Number",
            iconResId = R.drawable.phone,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text),
            onTextChange = { mobNum = it },
            maxLength = 30
        )
        Text(
            text = "${mobNum.length}/30",
            modifier = Modifier.padding(start = 200.dp),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 15.sp
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        InputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp
            ),
            text = password,
            color = Color.Black,
            label = "Password",
            iconResId = R.drawable.lock,
            onTextChange = { password = it },
            maxLength = 20,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            )
        )
        Text(
            text = "${password.length}/20",
            modifier = Modifier.padding(start = 200.dp),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 15.sp
            )
        )
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
                text = AnnotatedString("LOGIN"),
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
                    registerViewModel.login(mobNum, password)
                }
            )
        }

        Row(modifier = Modifier.padding(top = 15.dp, start = 100.dp)){
            Text(
                text = "Forgot your password? ",
                style = TextStyle(fontSize = 12.sp),
                color = Color(0xFF7E8385)
            )
            Text(
                text = "Reset Now",
                modifier = Modifier.clickable {
                    showLogs("PROTECTEDDDDDDDDDDD", "PROTECTED API CALLED")
                    CoroutineScope(Dispatchers.IO).launch{

                        try {
                            // Retrieve the access token
                            val accessToken = TokenStorage.getToken(context = ct)

                            // Log the raw access token for debugging
                            showLogs("PROTECTED", "Raw Access Token: ${accessToken?.first ?: "No Token Found"}")

                            // Ensure the access token is not null, remove all whitespace, and trim
                            val token = accessToken?.first?.replace("\\s".toRegex(), "")?.trim()

                            // Log the formatted token
                            showLogs("DEBUG", "Formatted Token: '$token'")

                            if (token.isNullOrBlank()) {
                                showLogs("ERROR", "Access token is missing or empty.")
                                return@launch
                            }

                            // Append "Bearer " before the token with only one space
                            val authorizationHeader = "Bearer $token"

                            // Log the complete Authorization header
                            showLogs("DEBUG", "Authorization Header: '$authorizationHeader'")

                            // Make the API call with the Authorization header
                            val response = myComponents.otherAPI.protected(authorizationHeader)

                            if (response.isSuccessful) {
                                // Handle success
                                val result = response.body()
                                showLogs("SUCCESS", "Protected API response: $result")
                            } else {
                                // Handle failure and log the error body
                                val error = response.errorBody()?.string()
                                showLogs("ERROR", "Protected API error: $error")
                            }
                        } catch (e: Exception) {
                            showLogs("EXCEPTION", "Exception occurred: ${e.message}")
                            e.printStackTrace()
                        }


                    }
//                    navController.popBackStack()
//                    mNavigator.navigateTo(Screens.forgotPassword.route)
                },
                style = TextStyle(fontSize = 12.sp,
                    fontWeight = FontWeight.Bold),
                color = Color(0xFF7E8385)
            )
        }
        Row(modifier = Modifier.padding(top = 105.dp)){
            Text(
                text = "Don't have an account? ",
                style = TextStyle(fontSize = 12.sp),
                color = Color(0xFF7E8385)
            )
            Text(
                text = "Register Now",
                style = TextStyle(fontSize = 12.sp,
                    fontWeight = FontWeight.Bold),
                color = Color(0xFF7E8385)
            )

        }
    }
}*/

//@Preview
@Composable
fun LoginScreen(){
    var username_mobNum by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 12.dp, top = 16.dp, end = 12.dp)
    ) {

        Header(
            text1 = "Login",
            text2 = "Welcome back to  our community and continue experiencing"
        )

        Spacer(modifier = Modifier.height(26.dp))

        InputText(
            modifier = Modifier
                .padding(top = 9.dp, bottom = 8.dp),
            text = username_mobNum,
            color = Color.Black,
            label = "Username",
            maxLine = 1,
            iconResId = R.drawable.person,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text),
            onTextChange = { username_mobNum = it },
            maxLength = 30
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
        Spacer(modifier = Modifier.height(28.dp))

        Text(text = "Click Login to proceed",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Grey,
                fontFamily = mFont.outfitRegular
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton("Login"){

            registerViewModel.login(username_mobNum, password)

        }

        Row  (modifier = Modifier.padding(top = 18.dp)){
            Text(

                text = "Do not have an account?",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = mFont.outfitRegular,
                    fontWeight = FontWeight.SemiBold
                ),
                color = lightBlack
            )
            Text(
                modifier = Modifier
                    .clickable { mNavigator.navigateTo(Screens.Register.route) },
                text = " Register",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = mFont.outfitRegular,
                    fontWeight = FontWeight.SemiBold
                ),
                color = orange
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(

            text = "Forgot Password?",
            style = TextStyle(
                fontSize = 13.sp,
                fontFamily = mFont.outfitRegular,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.fillMaxWidth()
                .clickable {},
            textAlign = TextAlign.Center,
            color = orange
        )
    }

}
