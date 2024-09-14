package com.amzi.mastercellusv2.networks

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val encryptedAccessToken = sharedPreferences.getString("ACCESS_TOKEN", null)

        val aesKey = EncryptionUtil.generateAESKey() // Retrieve the stored AES key from secure storage

        // Decrypt access token
        val accessToken = encryptedAccessToken?.let { EncryptionUtil.decrypt(it, aesKey) }

        // Proceed with the request
        val requestBuilder = chain.request().newBuilder()

        // Add Authorization header if access token exists
        accessToken?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        // Proceed with the request
        return chain.proceed(requestBuilder.build())
    }
}
