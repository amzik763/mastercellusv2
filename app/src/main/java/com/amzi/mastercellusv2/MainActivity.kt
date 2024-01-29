package com.amzi.mastercellusv2

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.amzi.mastercellusv2.navgraphs.setUpNavGraph
import com.amzi.mastercellusv2.ui.theme.Mastercellusv2Theme
import com.amzi.mastercellusv2.utility.NetworkMonitor
import com.amzi.mastercellusv2.utility.showLogs
import com.amzi.mastercellusv2.utility.showSnackBarNow
import com.amzi.mastercellusv2.utility.snacks

class MainActivity : ComponentActivity() {

//    lateinit var navController: NavHostController


    val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            // Called when a network is available
            showLogs("MAIN: ","Connected")
            showSnackBarNow("Connected",applicationContext)
        }

        override fun onLost(network: Network) {
            // Called when a network is lost
            showLogs("MAIN: ","Disconnected")
            showSnackBarNow("No Network",applicationContext)

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mastercellusv2Theme {
                showLogs("MAIN: ","Initialized")
                DisplayContent()

                val networkMonitor = NetworkMonitor(applicationContext)
                showLogs("temp",networkMonitor.checkNowForInternet().toString())
                networkMonitor.registerNetworkCallback(networkCallback)


            }
        }
    }
}
@Composable
fun DisplayContent(){
//    val scaffoldState:ScaffoldState = rememberScaffoldState()
//    val coroutineScope:CoroutineScope = rememberCoroutineScope()
    snacks.scaffoldState  = rememberScaffoldState()
    snacks.coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = snacks.scaffoldState
    ){Log.d("abc",it.toString())
        lateinit var navController: NavHostController

        navController = rememberNavController()
        setUpNavGraph(navController = navController)
//        Button(onClick = {
//         showSnackBarNow("Connected", )
//        }) {
//            Text(text = "Click me")
//        }
    }
}
