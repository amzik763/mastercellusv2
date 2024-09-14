package com.amzi.mastercellusv2.networks

import android.util.Log
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/*fun storeTokens(context: Context, accessToken: String, refreshToken: String) {

    // Generate or retrieve a master key
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    // Initialize EncryptedSharedPreferences
    val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_prefs", // file name
        masterKeyAlias, // master key
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // Store the tokens
    with(sharedPreferences.edit()) {
        putString("access_token", accessToken)
        putString("refresh_token", refreshToken)
        apply()
    }
}*/

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

/*
fun createRetrofitClient(context: Context): Retrofit {
    // Retrieve the access token from EncryptedSharedPreferences
    val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    val accessToken = sharedPreferences.getString("access_token", null)

    // Build OkHttp client with cookieJar and an interceptor that adds the Authorization header
    val client = OkHttpClient.Builder()
        .addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
            chain.proceed(request)
        }
        .cookieJar(MyCookieJar()) // Add custom CookieJar here
        .build()

    // Build and return the Retrofit instance
    return Retrofit.Builder()
        .baseUrl("http://192.168.1.5:8000") // Replace with your actual API base URL
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
*/

