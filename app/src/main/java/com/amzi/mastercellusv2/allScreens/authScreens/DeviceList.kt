package com.amzi.mastercellusv2.allScreens.authScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amzi.mastercellusv2.R
import com.amzi.mastercellusv2.components.CustomButton
import com.amzi.mastercellusv2.components.Header
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import com.amzi.mastercellusv2.ui.theme.lightBlack
import com.amzi.mastercellusv2.ui.theme.orange
import com.amzi.mastercellusv2.utility.mFont
import com.amzi.mastercellusv2.utility.myComponents
import com.amzi.mastercellusv2.utility.showLogs
import com.example.demo.ui.theme.darkestGrey
import com.example.homeapplication.ui.theme.Grey

@Preview
@Composable
fun DeviceList(){

    var username by remember { mutableStateOf(myComponents.registerViewModel.username.value) }
    var mobNum by remember { mutableStateOf(myComponents.registerViewModel.mobNum.value) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    val deviceList by myComponents.registerViewModel.deviceList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 12.dp, top = 16.dp, end = 12.dp)
    ) {

        Header(text1 = "My Devices",
            text2 = "If you have purchased any device, make sure to register it here"
        )
        Spacer(modifier = Modifier.height(36.dp))

        //UI if no device is registered
        if (deviceList.isEmpty()){
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image( modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                    painter = painterResource(R.drawable.signeddevice),
                    contentDescription = "",
                    contentScale = ContentScale.Inside
                )
            }

            showLogs("Device List", "Empty devices")
        }





        //UI If registered
        else{

            showLogs("Device List", "Contains devices")

            Row (modifier = Modifier.fillMaxWidth()){
                Text(text = "Home Automation",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = mFont.outfitRegular,
                        fontWeight = FontWeight.Bold,
                        color = Grey,
                        letterSpacing = TextUnit(1f, TextUnitType.Sp)
                    )
                )

                CustomButton(text = "Open") {

                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) {
                itemsIndexed(deviceList){
                        index,item ->
                    DeviceItem(index,item)
                }
            }
        }


        Spacer(modifier = Modifier.height(28.dp))
        CustomButton("Register New User"){
            mNavigator.navigateTo(Screens.DeviceRegister.route)
//      myComponents.registerViewModel.verify(mobNum, password, confirmPassword, otp)
        showLogs("REGISTRATION", "Go to actual registration")
        }
        Spacer(modifier = Modifier.height(36.dp))


        Text(
            modifier = Modifier
                .clickable { mNavigator.navigateTo(Screens.Login.route) },
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

@Composable
fun DeviceItem(index: Int, item: DeviceListResponse) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)) {
            Row {

            Image(
                modifier = Modifier.size(55.dp),
                contentScale = ContentScale.Inside,
                contentDescription = "",
                painter = if (
                    item.deviceName == "GreenHouse"
                ) {
                    painterResource(R.drawable.ic_homeautomation)

                } else {
                    painterResource(R.drawable.ic_mushroom_old)
                }
            )

                Spacer(modifier = Modifier.width(8.dp))

                Column{
                    Text(item.deviceMac, style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = mFont.outfitBold2,
                        fontWeight = FontWeight.SemiBold,
                        color = lightBlack,
                        letterSpacing = TextUnit(1f, TextUnitType.Sp)
                    )
                    )
                    Text(item.deviceName, style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = mFont.outfitRegular,
                        fontWeight = FontWeight.SemiBold,
                        color = darkestGrey
                    ))
                }
            }
        }
}

data class DeviceListResponse(
    val deviceName: String,
    val deviceMac: String
)