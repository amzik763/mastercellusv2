package com.android.homeapplication.screen

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.homeapplication.screen.data.Category
import com.android.homeapplication.screen.data.categoryList3
import com.android.homeapplication.screen.data.categoryList4
import com.android.homeapplication.screen.data.categoryList5
import com.android.homeapplication.screen.data.categoryList6
import com.android.homeapplication.viewModel.HomeAppViewModel
import com.example.homeapplication.ui.theme.lightBlack
import com.example.homeapplication.ui.theme.lightOrange


@Composable
fun LedBulbScreen(context :Context,viewModel: HomeAppViewModel){

    // Lock the screen orientation to portrait mode
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
Column {

    LedText()


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
        //.padding(20.dp)
    )
    {
        item {
            Spacer(modifier = Modifier.height(35.dp))
            BulbRow(viewModel)
            Spacer(modifier = Modifier.height(25.dp))
            BulbRow22(viewModel)
            Spacer(modifier = Modifier.height(25.dp))
            BulbRow33(viewModel)
            Spacer(modifier = Modifier.height(25.dp))
            BulbRow44(viewModel)
            Spacer(modifier = Modifier.height(100.dp))
        }

        Log.w("led", "done ....... 1 ")
    }
}
}
@Composable
fun LedText(){
    Column () {
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
            text = "LED BULB",
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
    }
}

@Composable
fun BulbRow(viewModel: HomeAppViewModel){
    LazyRow {
        items(categoryList3) { category ->
            BulbRow1(category = category,viewModel)
        }
    }
}
@Composable
fun BulbRow22(viewModel: HomeAppViewModel){
    LazyRow {
        items(categoryList4) { category ->
            BulbRow1(category = category,viewModel)
        }
    }
}

@Composable
fun BulbRow33(viewModel: HomeAppViewModel){
    LazyRow {
        items(categoryList5) { category ->
            BulbRow1(category = category,viewModel)
        }
    }
}
@Composable
fun BulbRow44(viewModel: HomeAppViewModel){
    LazyRow {
        items(categoryList6) { category ->
            BulbRow1(category = category,viewModel)
        }
    }
}
@Composable
fun BulbRow1(
    category: Category,
    viewModel: HomeAppViewModel
){
    val clr = remember {
        mutableStateOf<Boolean>(viewModel.bulbsState[category.id])
    }
    Box(modifier = Modifier
        .padding(end = 24.dp, start = 24.dp)
        .clickable {
            viewModel.bulbSwitch(category.id)
            clr.value = !clr.value
        }
        .background(
            if (clr.value) Color(255, 255, 153) else category.color,
            RoundedCornerShape(8.dp)
        )
        .width(130.dp)
        .height(110.dp)
    ){
        Text(text = category.title, style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
        ),
            modifier = Modifier
                .padding(start = 5.dp)
                .align(Alignment.CenterStart)
        )
        Text(text = category.subTitle, style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            color = Color(0xFF625b71)
        ),
            modifier = Modifier
                .padding(start = 7.dp, top = 65.dp)
        )
        Image(painter = painterResource(id = category.image), contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 9.dp)
                .align(Alignment.BottomEnd)
        )
    }
}