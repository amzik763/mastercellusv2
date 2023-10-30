package com.amzi.mastercellusv2.Networks

import com.amzi.mastercellusv2.models.model_login
import com.amzi.mastercellusv2.models.model_reg_status
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
    suspend fun registerUser(@Field("mobile") mobile:String,
                             @Field("first_name") firstName:String,
                             @Field("last_name") lastName:String,
                             @Field("dob") dob:String
                             )
                             :Response<model_reg_status>

    @FormUrlEncoded
    @POST("/v1/auth/login")
    suspend fun login(
        @Field("mobile") mobileNumber: String,
        @Field("password") password: String,
        @Field("fcm_token") fcmToken: String?
    ): model_login
//    @GET("/user")
//    suspend fun getUser():Response<String>
//
//    @GET("/user")
//    suspend fun getUser():Response<String>
}