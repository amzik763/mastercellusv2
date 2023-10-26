package com.amzi.mastercellusv2.Networks

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthAPIs {

    @GET("/user")
    suspend fun getUser():Response<String>

    @FormUrlEncoded
    @POST("/v1/auth/register")
    suspend fun registerUser(@Field("first_name") firstName:String,
                             @Field("lastName") lastName:String,
                             @Field("mobile") mobile:String)
                             :Response<String>
//
//    @GET("/user")
//    suspend fun getUser():Response<String>
//
//    @GET("/user")
//    suspend fun getUser():Response<String>
}