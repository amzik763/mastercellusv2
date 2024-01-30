package com.example.demo.wifi

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.mushroomapplication.R
import com.example.demo.ui.theme.cblue
import com.example.demo.viewmodels.SecondViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun StringList(navController: NavHostController? = null, secondViewModel: SecondViewModel) {
    val ct = LocalContext.current

    Column{
    Row(modifier = Modifier
        .background(color = Color.White)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){

        Row(modifier = Modifier
            .background(color = Color.White)
            ,verticalAlignment = Alignment.CenterVertically){

            //Cti Logo
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .size(height = 36.dp, width = 36.dp),
                painter = painterResource(id = R.drawable.ctilogo),
                contentDescription = "My Icon",
            )

            Text(modifier = Modifier.padding(top = 5.dp),
                text = "SKIAO",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 26.sp)
        }

       /* Row(){
            IconButton(
                onClick = { navController?.navigate("SecondMushroom") },
                modifier = Modifier
                    .padding(16.dp)
                    .size(36.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.csetting),
                    contentDescription = "Setting button",
                    modifier = Modifier
                        //.padding(8.dp)
                        .size(36.dp)


                )
            }
        }*/

    }
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .weight(0.85f)
//        .verticalScroll(rememberScrollState())
    )
    {
        items(secondViewModel.wifiList) { item ->
            Text(
                text = item,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        Log.d("JJ", "ITEM$item")
                        secondViewModel.currentSSID = item
                        secondViewModel.setMyDialogText(item)
//                        secondViewModel.dialogText.value = item
                        secondViewModel.onByClick()
                        showToast(ct, "Clicked on: $item")
                    }
            )
        }
    }

        ConnectButton(
            secondViewModel
//            activity = ,
//            applicationContext = ,
//            wifiManager = ,
//            s = ,
//            s1 = ,
//            requestPermissionLauncher =
        )


        }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ConnectButton(secondViewModel: SecondViewModel) {
    Button(
        onClick = {
            // Handle button click here
//abc
            secondViewModel.checkPermission++
            Log.d("great",secondViewModel.checkPermission.toString())
        },
        modifier = Modifier
            .padding(top = 6.dp, bottom = 6.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(cblue)
    ) {
        Text(
            text = "Refresh",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
