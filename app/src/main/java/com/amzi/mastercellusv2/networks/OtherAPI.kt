package com.amzi.mastercellusv2.networks

import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface OtherAPIs {
    @FormUrlEncoded
    @GET("/master/api/protected")
    suspend fun protected(
    ): Response<String>
}