package com.amzi.mastercellusv2.AllScreens.authScreens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amzi.mastercellusv2.R
import com.amzi.mastercellusv2.navgraphs.Screens
import com.amzi.mastercellusv2.navgraphs.mNavigator
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
//    navHostController: NavHostController
) {

    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        mNavigator.navigateTo(Screens.Signup.route)
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.background(color = Color(0xFFFFFFFF)).fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.skaio),
            contentDescription = "Logo",
//            colorFilter = ColorFilter.tint(Color(0xFFFFFFFF)),
            modifier = Modifier.scale(scale.value)
                .size(width = 190.dp, height = 100.dp))
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AuthScreenPreview(){

    SplashScreen(
//   rememberNavController()
    )
}

