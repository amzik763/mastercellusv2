package com.example.homeapplication.screen

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.homeapplication.R
import com.example.homeapplication.navigation.NavigationItem
import com.example.homeapplication.ui.theme.lightBlack
import com.example.homeapplication.ui.theme.lightGrey
import com.example.homeapplication.ui.theme.lightOrange

//@Preview
@Composable
fun HomeScreen(navController: NavHostController){
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
                      navController.navigate(NavigationItem.LedBulbScreen.route)
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
                      navController.navigate(NavigationItem.FanAcScreen.route)
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
                      navController.navigate(NavigationItem.FanAcScreen.route)
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
/*

@Composable
fun HomeScreen(
//    viewModel: HomeAppViewModel
){
    // Lock the screen orientation to portrait mode
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            //.padding(5.dp)
            .background(Color(0, 0, 51))
    ){

        item {
            Header()
            Spacer(modifier = Modifier.height(15.dp))
            Heading()
            Spacer(modifier = Modifier.height(60.dp))
            DeviceRow()
        }
    }
}
@Composable 
fun Heading(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 400.dp, height = 100.dp)
            .background(Color(51, 153, 225), RoundedCornerShape(2.dp))
            .padding(18.dp),

        ){
       Box(
           contentAlignment = Center,
           modifier = Modifier.fillMaxWidth(1/2f)
       ){
           Icon(painter = painterResource(id = R.drawable.img1),
               contentDescription = "My Icon",
               tint = Color.Unspecified,
               modifier = Modifier
                   .size(60.dp)
           )
       }

        Box(contentAlignment = Center,
            modifier = Modifier.fillMaxWidth(1f)){
            Column{
                Text(
                    text = stringResource(id = R.string.total_device),
                   style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White
                    )
                )
                Text(
                    text = stringResource(id = R.string.Num_device),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(224,224,224)
                    )
                )

            }
        }
    }
}

@Composable
fun DeviceRow(){
    Column{
        CommonTitle(title = stringResource(id = R.string.categories))
        Spacer(Modifier.height(30.dp))
        LazyRow{
            items(categoryList, key = { it.id }) {
                DeviceEachRow1(category = it)
            }
        }
        Spacer(Modifier.height(20.dp))
        LazyRow {
            items(categoryList2, key = { it.id }) {
                DeviceEachRow2(category = it)
            }
        }
    }
}
@Composable
fun DeviceEachRow1(
    category: Category
){
    Box(modifier = Modifier
        .padding(end = 15.dp, start = 15.dp)
        .background(category.color, RoundedCornerShape(8.dp))
        .width(168.dp)
        .height(110.dp)
    ){
        Text(text = category.title, style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
        ),
        modifier = Modifier
            .padding(start = 5.dp)
            .align(CenterStart)
        )
        Image(painter = painterResource(id = category.image), contentDescription = "",
        modifier = Modifier
            .size(60.dp)
            .padding(end = 9.dp)
            .align(BottomEnd))
    }
}

@Composable
fun DeviceEachRow2(
    category: Category
){
    Box(modifier = Modifier
        .padding(end = 15.dp, start = 15.dp)
        .background(category.color, RoundedCornerShape(8.dp))
        .width(168.dp)
        .height(110.dp)

    ){
        Text(text = category.title, style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
        ),
            modifier = Modifier
                .padding(start = 5.dp)
                .align(CenterStart)
        )
        Image(painter = painterResource(id = category.image), contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 9.dp)
                .align(BottomEnd))
    }
}

@Composable
fun CommonTitle(
    title: String,
    //onClick: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color(204,225,225)
            )
        )
    }
}

@Composable
fun Header(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp)
    ) {
     Text(
         text = stringResource(id = R.string.heading_text),
         style = TextStyle(
             fontSize = 35.sp,
             fontWeight = FontWeight.Bold,
             fontFamily = FontFamily.Serif,
             color = Color.White,
         )
     )
    }
}


*/
