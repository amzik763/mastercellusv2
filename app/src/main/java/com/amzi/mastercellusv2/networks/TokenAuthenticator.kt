package com.amzi.mastercellusv2.networks

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(private val context: Context) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val encryptedRefreshToken = sharedPreferences.getString("REFRESH_TOKEN", null)

        val aesKey = EncryptionUtil.generateAESKey() // Retrieve stored AES key

        // Decrypt refresh token
        val refreshToken = encryptedRefreshToken?.let { EncryptionUtil.decrypt(it, aesKey) }

        if (refreshToken != null) {
            // Call API to refresh the access token using the refresh token
            val newAccessToken = refreshAccessToken(refreshToken) // You need to implement this

            // Update SharedPreferences with new access token
            val editor = sharedPreferences.edit()
            val encryptedNewAccessToken = EncryptionUtil.encrypt(newAccessToken, aesKey)
            editor.putString("ACCESS_TOKEN", encryptedNewAccessToken)
            editor.apply()

            // Retry the request with the new access token
            return response.request.newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()
        }
        return null
    }

    private fun refreshAccessToken(refreshToken: String): String {
        // Make a request to your API to get a new access token
        // This is just a placeholder, you'll need to implement the actual API call.
        return "newAccessToken"
    }
}
