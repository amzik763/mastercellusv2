package com.cti.softStarter.networkMonitor

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import android.widget.Toast

class NetworkCallbackHandler(private val context: Context) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        Log.d("Network: ", "Connected")
        Toast.makeText(context, "Network Connected", Toast.LENGTH_LONG).show()
    }

    override fun onLost(network: Network) {
        Log.d("Network: ", "Disconnected")
        Toast.makeText(context, "Network Disconnected", Toast.LENGTH_LONG).show()
    }
}