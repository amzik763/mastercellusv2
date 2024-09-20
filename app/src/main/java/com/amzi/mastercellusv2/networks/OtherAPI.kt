package com.amzi.mastercellusv2.networks

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface OtherAPIs {
    @GET("/master/api/protected")
    suspend fun protected(
        @Header("Authorization") accessToken: String
    ): Response<String>
}