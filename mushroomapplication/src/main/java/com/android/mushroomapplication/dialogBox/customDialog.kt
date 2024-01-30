package com.example.demo.dialogBox

import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.android.mushroomapplication.MainActivity
import com.example.demo.Utility.connectToWifi
import com.example.demo.viewmodels.SecondViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    thisActivity: MainActivity,
    wifiManager: WifiManager,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    mytext: String,
    viewModel: SecondViewModel
){
    var textFieldValue by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
        onDismiss()
    },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) /*{
        Card (
//           elevation = 5.dp,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxSize(0.5f)
                .border(1.dp, color = yellow, shape = RoundedCornerShape(8.dp))
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Text(text = "Find me if you can....",
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(onClick = {
                    onDismiss()
                },
                    colors = ButtonDefaults.buttonColors(),
                    modifier = Modifier
//                        .background(orange)
                        .fillMaxWidth()
                        .weight(0.7f),
                    shape = CircleShape) {
                    Text(text = "Cancel",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = lightBlack,
                        )
                    )
                }
                Button(onClick = {
                    onConfirm()
                },
                    colors = ButtonDefaults.buttonColors(),
                    modifier = Modifier
//                        .background(orange)
                        .fillMaxWidth()
                        .weight(0.7f),
                    shape = CircleShape) {
                    Text(text = "Confirm",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = lightBlack
                        )
                    )
                }
            }
        }
    }*/{
        Card(
//           elevation = 5.dp,
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .fillMaxHeight(0.3f)
                    .border(1.dp, color = Color.White, shape = RoundedCornerShape(18.dp))
            ) {

            Column(
                modifier = Modifier
                    .align(alignment = CenterHorizontally)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Enter password",
                    style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                ),
                )
                TextField(value = textFieldValue,
                    onValueChange = {
                        textFieldValue = it
                        viewModel.updateTextFieldValue(it)})

                Surface(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .fillMaxWidth(0.8f)
                        .height(45.dp),

                            color = Color(0xFF0F158F),
                    shape = RoundedCornerShape(corner = CornerSize(8.dp)),
                    border = BorderStroke(width = 1.dp, color = Color.Blue)
                ) {
                    ClickableText(
                        text = AnnotatedString("Set Wifi"),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .padding(9.dp)
                            .align(CenterHorizontally),
                        onClick = {

                            viewModel.wifiCounter = 3
                            viewModel.isWifiPending = true
                            connectToWifi(thisActivity, wifiManager, viewModel.currentSSID, textFieldValue,viewModel)

//                        if(mytext.equals(textFieldValue)){
//
//                        }
                        }
                    )
                }
            }
        }
    }

}