package com.android.mushroomapplication

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demo.wifi.StringList
import com.example.demo.dialogBox.CustomDialog
import com.example.demo.dialogBox.networkDialog
import com.example.demo.ui.mushroom.Energy
import com.example.demo.ui.mushroom.Title
import com.example.demo.ui.mushroom.Title2
import com.example.demo.viewmodels.SecondViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WifiList(
    wifiManager: WifiManager,
    wifiList: MutableList<String>,
    requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {

    val context = LocalContext.current

    LazyColumn {
        items(wifiList) { wifiNetwork ->
            WifiNetworkItem(wifiNetwork)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WifiNetworkItem(wifiNetwork: String) {
    ListItem(
        headlineText = {
            Text(wifiNetwork, modifier = Modifier.clickable {
                Log.d("greta", wifiNetwork)
            })
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Handle item click
            }
    )
}

class MainActivity : ComponentActivity() {

    lateinit var requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceSecondViewModel: Bundle?) {
        super.onCreate(savedInstanceSecondViewModel)
        Log.d("Launching", "App 1")
        var thisActivity = this;

        setContent {
            var wifiManager: WifiManager =
                applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            val secondViewModel = viewModel<SecondViewModel>(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return SecondViewModel(
                            context = applicationContext, wifiManager = wifiManager
                        ) as T
                    }
                }
            )

            requestPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission granted, fetch Wi-Fi list
                    Log.d("FETCHIGN", "List")
//                    fetchWifiList(wifiManager, wifiList, applicationContext)

                } else {

                    Log.d("FETCHIGN", "Not granted List")

                    // Handle permission denied
                    // You might want to show a message or take appropriate action
                }
            }

            LaunchedEffect(key1 = secondViewModel.checkPermission ) {
                Log.d("Perm: ", "Permission Launched")
                // Check and request permissions when the composable is first launched
                checkAndRequestPermissions(applicationContext, requestPermissionLauncher)
                Log.d("ABCd", wifiManager.isWifiEnabled.toString())
                if(wifiManager.isWifiEnabled){

                    fetchWifiList(wifiManager, applicationContext,secondViewModel,thisActivity,locationManager)


                }else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                        ActivityCompat.startActivityForResult(thisActivity, panelIntent, 3, null)
                    } else {

                    }
                }
            }

            App(this, secondViewModel)
            mDialog(secondViewModel,wifiManager,thisActivity)
            Log.d("Launching", "App 2")
//            WifiList(wifiManager,wifiList,requestPermissionLauncher)
            Spacer(modifier = Modifier.height(16.dp))
//            ConnectButton(a,applicationContext,wifiManager,"abc","amzad1234",requestPermissionLauncher)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 3) {
            // The result is from the Wi-Fi settings activity
            if (resultCode == Activity.RESULT_OK) {
                Log.d("Wifi Turned On", "App 2")
                // Wi-Fi was turned on successfully
                // Add your logic here
            } else {
                Log.d("Wifi Not Turned On", "App 2")

                // Wi-Fi was not turned on, or the user canceled the action
                // Add your logic here
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
fun mDialog(secondViewModel: SecondViewModel, wifiManager: WifiManager, thisActivity: MainActivity) {

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

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ConnectButton(
    activity: MainActivity,
    applicationContext: Context,
    wifiManager: WifiManager,
    s: String,
    s1: String,
    requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
    Button(modifier = Modifier.height(45.dp),
        onClick = {

            if (!wifiManager.isWifiEnabled) {

            }

            try {
                checkAndRequestPermissions(applicationContext, requestPermissionLauncher)
                //To Connect Later
              //  connectToWifi(applicationContext, wifiManager, s, s1)

            } catch (e: Exception) {
                requestPermissionLauncher.launch(Manifest.permission.CHANGE_NETWORK_STATE)
            }
        }) {
        Text("Connect to Wi-Fi please")
    }
}

// Function to check and request necessary permissions
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
        Toast.makeText(thisActivity,"Accept Location Permission",Toast.LENGTH_LONG).show()

//        checkAndRequestPermissions(context, )
        return
    }else{
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            Log.d("ABC", "Location Enabled")
            Toast.makeText(thisActivity,"Getting Wifi details",Toast.LENGTH_LONG).show()



        }else{
            Log.d("ABC", "Location Disabled")
            Toast.makeText(thisActivity,"Please Enable Location",Toast.LENGTH_LONG).show()
        }



//        secondViewModel.showToast()

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
//    val sc = wifiManager.scanResults
//    wifiListState.value = sc
    try {
//        Log.d("wifi name: ", sc.get(0).BSSID.toString())
//        Log.d("wifi name: ", sc.get(0).BSSID.toString())
//        Log.d("wifi name: ", sc.get(1).BSSID.toString())
//        Log.d("wifi name: ", sc.get(1).level.toString())
//        Log.d("wifi name: ", sc.get(1).SSID)
//        Log.d("wifi name: ", sc.get(0).SSID)

    } catch (e: Exception) {

        e.printStackTrace()
        Log.d(
            "error", "error"
        )

    }

}

@Composable
fun showToast() {
    val context = LocalContext.current
//    Toast.makeText()
}


