package com.cti.softStarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cti.softStarter.networkMonitor.NetworkConnectivityHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkConnectivityHelper: NetworkConnectivityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkConnectivityHelper.registerNetworkCallback()

        setContent {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        networkConnectivityHelper.unregisterNetworkCallback()
    }
}
