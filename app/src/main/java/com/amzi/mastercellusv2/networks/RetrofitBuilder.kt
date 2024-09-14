package com.amzi.mastercellusv2.networks

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

        Log.d("COOKIES", cookieStore.toString())

        Log.d("COOKIES", cookieStore[url.host].toString())


    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        // Return cookies for the domain
        return cookieStore[url.host] ?: ArrayList()
    }
}

object RetrofitBuilder {
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


}

