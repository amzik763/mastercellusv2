package com.cti.softStarter.hiltModules


import android.content.Context
import android.net.ConnectivityManager
import com.cti.softStarter.networkMonitor.NetworkCallbackHandler
import com.cti.softStarter.networkMonitor.NetworkConnectivityHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {

    @Provides
    fun provideConnectivityManager(@ActivityContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun provideNetworkCallbackHandler(@ActivityContext context: Context): NetworkCallbackHandler {
        return NetworkCallbackHandler(context)
    }

    @Provides
    fun provideNetworkConnectivityHelper(
        connectivityManager: ConnectivityManager,
        networkCallbackHandler: NetworkCallbackHandler
    ): NetworkConnectivityHelper {
        return NetworkConnectivityHelper(connectivityManager, networkCallbackHandler)
    }
}