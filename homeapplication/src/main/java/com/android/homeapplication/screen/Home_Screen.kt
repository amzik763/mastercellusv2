package com.android.homeapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.homeapplication.R
import com.android.homeapplication.components.Header
import com.example.homeapplication.ui.theme.lightBlack
import com.example.homeapplication.ui.theme.lightGrey
import com.example.homeapplication.ui.theme.lightOrange

//@Preview
@Composable
fun HomeScreen(/*navController: NavHostController*/){
  Column (
      Modifier
          .fillMaxSize()
          .background(Color.White),
    ) {
      Column {
          Text(
              modifier = Modifier.padding(start = 16.dp, top = 16.dp),
              text = "My Home",
              fontWeight = FontWeight.Bold,
              fontSize = 24.sp,
              color = lightBlack,
          )

          Spacer(modifier = Modifier.height(4.dp))
          Divider(
              modifier = Modifier
                  .padding(start = 16.dp)
                  .fillMaxWidth(0.2f)
                  .height(4.dp)
                  .background(color = lightOrange)
          )

          Spacer(modifier = Modifier.height(36.dp))

          Text(
              modifier = Modifier.padding(start = 16.dp),
              text = "Device Categories",
              fontWeight = FontWeight.SemiBold,
              fontSize = 18.sp,
              color = lightBlack,
          )

          Spacer(modifier = Modifier.height(4.dp))
          Divider(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(4.dp)
                  .background(color = lightOrange)
          )
          Text(
              modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 12.dp),
              text = "1. LED Bulb",
              fontWeight = FontWeight.SemiBold,
              fontSize = 16.sp,
              color = lightBlack,
          )

          Card(
              shape = RoundedCornerShape(8.dp),
              modifier = Modifier
                  .padding(start = 24.dp, end = 24.dp)
                  .border(1.dp, color = lightGrey, shape = RoundedCornerShape(8.dp))
                  .clickable {
//                      navController.popBackStack()
//                      navController.navigate(NavigationItem.LedBulbScreen.route)
                  }
          ){
              Image(
                  modifier = Modifier
                      .padding(24.dp)
                      .size(height = 56.dp, width = 56.dp)
                      .border(0.dp, lightGrey, shape = RoundedCornerShape(4.dp))
                      .clip(RoundedCornerShape(4.dp)),
                  painter = painterResource(id = R.drawable.img2),
                  contentDescription = "Home",
                  contentScale = ContentScale.Crop,
              )
          }

          Spacer(modifier = Modifier.height(4.dp))
          Text(
              modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 12.dp),
              text = "2. Fan",
              fontWeight = FontWeight.SemiBold,
              fontSize = 16.sp,
              color = lightBlack,
          )

          Card(
              shape = RoundedCornerShape(8.dp),
              modifier = Modifier
                  .padding(start = 24.dp, end = 24.dp)
                  .border(1.dp, color = lightGrey, shape = RoundedCornerShape(8.dp))
                  .clickable {
//                      navController.popBackStack()
//                      navController.navigate(NavigationItem.FanAcScreen.route)
                  }
          ){
              Image(
                  modifier = Modifier
                      .padding(24.dp)
                      .size(height = 56.dp, width = 56.dp)
                      .border(0.dp, lightGrey, shape = RoundedCornerShape(4.dp))
                      .clip(RoundedCornerShape(4.dp)),
                  painter = painterResource(id = R.drawable.img8),
                  contentDescription = "Home",
                  contentScale = ContentScale.Crop,
              )
          }

          Spacer(modifier = Modifier.height(4.dp))
          Text(
              modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 12.dp),
              text = "3. AC",
              fontWeight = FontWeight.SemiBold,
              fontSize = 16.sp,
              color = lightBlack,
          )

          Card(
              shape = RoundedCornerShape(8.dp),
              modifier = Modifier
                  .padding(start = 24.dp, end = 24.dp)
                  .border(1.dp, color = lightGrey, shape = RoundedCornerShape(8.dp))
                  .clickable {
//                      navController.popBackStack()
//                      navController.navigate(NavigationItem.FanAcScreen.route)
                  }
          ){
              Image(
                  modifier = Modifier
                      .padding(24.dp)
                      .size(height = 56.dp, width = 56.dp)
                      .border(0.dp, lightGrey, shape = RoundedCornerShape(4.dp))
                      .clip(RoundedCornerShape(4.dp)),
                  painter = painterResource(id = R.drawable.img4),
                  contentDescription = "Home",
                  contentScale = ContentScale.Crop,
              )


          }
      }
  }
}

@Preview
@Composable
fun Home(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 12.dp, top = 16.dp, end = 12.dp)
    ) {

        Header(
            text1 = "Controller",
            text2 = "Tap on buttons to control the particular product"
        )
    }
}

