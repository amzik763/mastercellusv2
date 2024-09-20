package com.amzi.mastercellusv2

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amzi.mastercellusv2.allViewModels.Factories.RegisterViewModelFactory
import com.amzi.mastercellusv2.allViewModels.RegisterViewModel
import com.amzi.mastercellusv2.networks.AuthAPIs
import com.amzi.mastercellusv2.networks.RetrofitBuilder
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.navgraphs.setUpNavGraph
import com.amzi.mastercellusv2.networks.OtherAPIs
import com.amzi.mastercellusv2.ui.theme.Mastercellusv2Theme
import com.amzi.mastercellusv2.utility.NetworkMonitor
import com.amzi.mastercellusv2.utility.myComponents.authAPI
import com.amzi.mastercellusv2.utility.myComponents.authRepo
import com.amzi.mastercellusv2.utility.myComponents.navController
import com.amzi.mastercellusv2.utility.myComponents.otherAPI
import com.amzi.mastercellusv2.utility.myComponents.registerViewModel
import com.amzi.mastercellusv2.utility.myComponents.registerViewModelFactory
import com.amzi.mastercellusv2.utility.showLogs
import com.amzi.mastercellusv2.utility.showSnackBarNow
import com.amzi.mastercellusv2.utility.snacks
import com.example.demo.dialogBox.CustomDialog
import com.example.demo.dialogBox.networkDialog
import com.example.demo.ui.mushroom.Energy
import com.example.demo.ui.mushroom.Title
import com.example.demo.ui.mushroom.Title2
import com.example.demo.viewmodels.SecondViewModel
import com.example.demo.wifi.StringList

class MainActivity : ComponentActivity() {

    lateinit var requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>

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
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
//    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Launching", "App 1")
        var thisActivity = this;

//        authAPI = RetrofitBuilder.instance.create(AuthAPIs::class.java)

        authAPI = RetrofitBuilder.create(this).create(AuthAPIs::class.java)
        otherAPI = RetrofitBuilder.create(this).create(OtherAPIs::class.java)
        authRepo = AuthRepo(authAPI, context = applicationContext)
        registerViewModelFactory = RegisterViewModelFactory(authRepo)
        registerViewModel = registerViewModelFactory.create(RegisterViewModel::class.java)
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

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun App(context: Context, secondViewModel: SecondViewModel) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "firstmushroom",
    )
    {
        composable("firstmushroom") {
            Title(navController, secondViewModel)
        }
        composable("secondmushroom") {
            Title2(navController, secondViewModel)
        }
        composable("wifi") {
//            val stringArrayList = arrayListOf("Red", "Green", "Blue", "Yellow", "Orange")
            StringList(navController, secondViewModel)
        }
        composable("energyMushroom") {
            Energy(navController, secondViewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun mDialog(secondViewModel: SecondViewModel, wifiManager: WifiManager, thisActivity: com.android.mushroomapplication.MainActivity) {

//        var mytext by remember { mutableStateOf("My first text") }

    if (secondViewModel.isDialogShown) {
        CustomDialog(thisActivity,wifiManager,
            onDismiss = {
                secondViewModel.onDismissDialog()
            },
            onConfirm = {

            }, secondViewModel.dialogText, secondViewModel
        )
    }

    if (secondViewModel.isNetworkDialogShown) {
        networkDialog(thisActivity,wifiManager,
            onDismiss = {
                secondViewModel.hideNetworkDialog()
            },
            onConfirm = {

            }, secondViewModel.NetworkDialogText, secondViewModel
        )
    }
}

fun checkAndRequestPermissions(context: Context, launcher: ActivityResultLauncher<String>) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // On Android 10 (Q) and higher, both ACCESS_FINE_LOCATION and ACCESS_BACKGROUND_LOCATION are required
        val fineLocationPermission =
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        val backgroundLocationPermission =
            context.checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        val changeWifiStatePermission =
            context.checkSelfPermission(Manifest.permission.CHANGE_WIFI_STATE) ==
                    PackageManager.PERMISSION_GRANTED

//        Log.d("Launching", "Wifi Permission: $changeWifiStatePermission")


        if (!fineLocationPermission || !backgroundLocationPermission) {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        }
        if (!changeWifiStatePermission) {
            Log.d("Launching", "Wifi Permission")
            launcher.launch(Manifest.permission.CHANGE_WIFI_STATE)

        }

    } else {
        // On versions below Android 10, only ACCESS_FINE_LOCATION is required
        val fineLocationPermission =
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED

        if (!fineLocationPermission) {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}

// Function to fetch Wi-Fi list when permissions are granted
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun fetchWifiList(
    wifiManager: WifiManager,
    context: Context,
    secondViewModel: SecondViewModel,
    thisActivity: MainActivity,
    locationManager: LocationManager
) {

    if (!wifiManager.isWifiEnabled) {
        Log.d("ABCD", "Please enable wifi")
        wifiManager.isWifiEnabled = true
        return
    } else {
        Log.d("ABCD", "wifi enabled")
    }

    wifiManager.startScan()
    if (ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        Log.d("ABCD", "Please Grant Permission")
        Toast.makeText(thisActivity,"Accept Location Permission", Toast.LENGTH_LONG).show()

        return
    }else{
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            Log.d("ABC", "Location Enabled")
            Toast.makeText(thisActivity,"Getting Wifi details", Toast.LENGTH_LONG).show()

        }else{
            Log.d("ABC", "Location Disabled")
            Toast.makeText(thisActivity,"Please Enable Location", Toast.LENGTH_LONG).show()
        }
    }
    val sc = wifiManager.scanResults
    secondViewModel.wifiList.clear() // Clear existing list
    for (result in sc) {
        val frequency = result.frequency
        val is2_4GHz = frequency in 2400..2500
        val is5GHz = frequency in 5000..6000

        if(is2_4GHz && result.BSSID.toString().isNotEmpty() && result.BSSID.toString().length!=0)
        {
            secondViewModel.wifiList.add(result.SSID)
        }
    }

    try {
        Log.d("wifi name: ", sc.get(0).BSSID.toString())
        Log.d("wifi name: ", sc.get(0).BSSID.toString())
        Log.d("wifi name: ", sc.get(1).BSSID.toString())
        Log.d("wifi name: ", sc.get(1).level.toString())
        Log.d("wifi name: ", sc.get(1).SSID)
        Log.d("wifi name: ", sc.get(0).SSID)

    } catch (e: Exception) {

        e.printStackTrace()
        Log.d(
            "error", "error"
        )

    }

}

@Composable
fun DisplayContent(){
    snacks.scaffoldState  = rememberScaffoldState()
    snacks.coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = snacks.scaffoldState
    ){Log.d("abc",it.toString())
//        lateinit var navController: NavHostController

        navController = rememberNavController()
        setUpNavGraph(
//            navController = navController
        )
//        Navigation(LocalContext.current)
    }
}