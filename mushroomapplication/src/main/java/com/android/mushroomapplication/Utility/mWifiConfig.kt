package com.example.demo.Utility
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.NetworkSpecifier
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiNetworkSpecifier
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.demo.viewmodels.SecondViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class WifiConnectStatus {
    CONNECTED,
    WRONG_PASSWORD,
    FAILED
}

@RequiresApi(Build.VERSION_CODES.Q)
fun connectToWifi(
    context: Context,
    wifiManager: WifiManager,
    ssid: String,
    password: String,
    viewModel: SecondViewModel
)
//: WifiConnectStatus
{
    viewModel.NetworkDialogTextHeader = "Updating Wifi"
    viewModel.NetworkDialogText = "Sending Command to Controller"
    viewModel.showNetworkDialog()
//    viewModel.updateWifiDetail(password)
    viewModel.onDismissDialog()
//    viewModel.isWifiPending = true
    GlobalScope.launch {
//        delay(1000) // 5000 milliseconds (5 seconds) delay
        viewModel.updateWifiDetail(password)
    }
    if (!wifiManager.isWifiEnabled) {
        Log.d("CONNECTING: ", "WIFI ONNING")

        wifiManager.isWifiEnabled = true

    }

//    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//        Log.d("CONNECT: ", "SPECIFIER")
//        connectToWifiWithSpecifier(context, wifiManager, ssid, password,viewModel)
//    } else {
//        Log.d("CONNECT: ", "NOT SPECIFIER")
//        connectToWifiWithConfiguration(context, wifiManager, ssid, password,viewModel)
//    }
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun connectToWifiWithSpecifier(
    context: Context,
    wifiManager: WifiManager,
    ssid: String,
    password: String,
    viewModel: SecondViewModel
): WifiConnectStatus {
    val specifier: NetworkSpecifier = WifiNetworkSpecifier.Builder()
        .setSsid(ssid)
        .setWpa2Passphrase(password)
        .build()

    val request = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .setNetworkSpecifier(specifier)
        .build()

    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d("WifiConnect", "Connected to $ssid")
            // Additional actions upon successful connection

        }

        override fun onUnavailable() {
            super.onUnavailable()
            Log.e("WifiConnect", "Failed to connect to $ssid")
            // Handle failure to connect
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d("WifiConnect", "Connection lost")
            // Handle connection loss
        }
    }

    connectivityManager.requestNetwork(request, networkCallback)

    // Cleanup callback when it's no longer needed
    // connectivityManager.unregisterNetworkCallback(networkCallback)

    return WifiConnectStatus.CONNECTED
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun connectToWifiWithConfiguration(
    context: Context,
    wifiManager: WifiManager,
    ssid: String,
    password: String,
    viewModel: SecondViewModel
): WifiConnectStatus {
    val wifiConfiguration = createWifiConfiguration(ssid, password)

    val networkId = wifiManager.addNetwork(wifiConfiguration)
    if (networkId == -1) {
        Log.e("WifiConnect", "Failed to add network configuration")
        return WifiConnectStatus.FAILED
    }

    val connected = wifiManager.enableNetwork(networkId, true)
    return if (connected) {
        Log.d("WifiConnect", "Connected to $ssid")
        WifiConnectStatus.CONNECTED
    } else {
        Log.e("WifiConnect", "Failed to connect to $ssid")
        WifiConnectStatus.WRONG_PASSWORD
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun createWifiConfiguration(ssid: String, password: String): WifiConfiguration {
    // For Android 10 and higher, this function is not used
    throw UnsupportedOperationException("createWifiConfiguration should not be called on Android 10 and higher.")
}