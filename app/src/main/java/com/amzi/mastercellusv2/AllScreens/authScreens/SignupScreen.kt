package com.amzi.mastercellusv2.AllScreens.authScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.AllViewModels.RegisterViewModel
import com.amzi.mastercellusv2.R
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.utility.showLogs
import com.amzi.mastercellusv2.components.InputText
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel

@Composable
fun SignupScreen() {

    //temporary
    showLogs("signup","called")
    var mobNum by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }

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
            text = "Register",
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
        InputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp
            ),
            text = name,
            color = Color.Black,
            label = "Full name",
            maxLine = 1,
            iconResId = R.drawable.user,
            onTextChange = { name = it },
            maxLength = 20,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Text(
                text = "Min 3 characters required*",
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 12.sp
                )
            )
            Text(
                text = " ",
                modifier = Modifier.padding(start = 113.dp),
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 13.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        InputText(
            modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp
            ),
            text = dob,
            color = Color.Black,
            label = "Date of Birth",
            iconResId = R.drawable.calender,
            maxLine = 1,
            onTextChange = { dob = it },
            maxLength = 20,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Text(
                text = "Optional",
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 12.sp
                )
            )
            Text(
                text = "  ",
                modifier = Modifier.padding(start = 210.dp),
                style = TextStyle(
                    color = Color(0xFF7E8385),
                    fontSize = 13.sp
                )
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
                text = AnnotatedString("Register Now"),
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
                    registerViewModel.registerUser(mobNum,name,dob)

                }
            )
        }

        Row {
            Text(
                modifier = Modifier.padding(top = 60.dp),
                text = "Already have an account?",
                style = TextStyle(
                    fontSize = 12.sp,
                ),
                color = Color(0xFF7E8385)
            )
            Text(
                modifier = Modifier.padding(top = 60.dp)
                    .clickable { mNavigator.navigateTo(Screens.Login.route)},
                text = "Login now",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF7E8385)
            )
        }
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SignupScreenPreview(){

}