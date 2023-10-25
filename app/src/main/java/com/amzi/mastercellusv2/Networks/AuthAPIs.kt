package com.amzi.mastercellusv2.Networks

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthAPIs {

    @GET("/user")
    suspend fun getUser():Response<String>

//    @POST("/user")
//    suspend fun registerUser(@Field username:String):Response<String>
//
//    @GET("/user")
//    suspend fun getUser():Response<String>
//
//    @GET("/user")
//    suspend fun getUser():Response<String>
}