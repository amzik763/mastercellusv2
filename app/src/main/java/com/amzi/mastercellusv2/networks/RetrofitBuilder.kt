package com.amzi.mastercellusv2.networks

import android.content.Context
import android.util.Log
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyCookieJar : CookieJar {
    private val cookieStore: HashMap<String, List<Cookie>> = HashMap()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        // Save cookies for the domain
        cookieStore[url.host] = cookies

        // Extract access and refresh tokens from the cookies
        var accessToken: String? = null
        var refreshToken: String? = null

        cookies.forEach { cookie ->
            when {
                cookie.name == "access" -> accessToken = cookie.value
                cookie.name == "refresh" -> refreshToken = cookie.value
            }
        }
        // Log the access and refresh tokens
        Log.d("COOKIES", "Access Token: $accessToken")
        Log.d("COOKIES", "Refresh Token: $refreshToken")
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        // Return cookies for the domain
        return cookieStore[url.host] ?: ArrayList()
    }
}

/*object RetrofitBuilder {
    private const val BASE_URL = "http://192.168.1.5:8000"
    val instance: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cookieJar(MyCookieJar()) // Use custom CookieJar
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}*/

object RetrofitBuilder {
    private const val BASE_URL = "http://192.168.1.5:8000"

    fun create(context: Context): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // Add AuthInterceptor and TokenAuthenticator
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(context)) // Attach access token
            .authenticator(TokenAuthenticator(context)) // Automatically refresh token
            .cookieJar(MyCookieJar()) // Use custom CookieJar
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}




