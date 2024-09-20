package com.amzi.mastercellusv2.networks

import com.amzi.mastercellusv2.models.LoginResponse
import com.amzi.mastercellusv2.models.RegisterRes
import com.amzi.mastercellusv2.models.SetPassword
import com.amzi.mastercellusv2.models.VerifyResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
interface AuthAPIs {

    @FormUrlEncoded
    @POST("/master/api/registeruser")
    suspend fun register(
        @Field("username") username: String,
        @Field("fName") fName: String,
        @Field("lName") lName: String,
        @Field("email") email: String,
        @Field("mobile_number") mobile_number: String
    ): Response<RegisterRes>

    @FormUrlEncoded
    @POST("/master/api/confirmuser")
    suspend fun verify(
        @Field("mobile_number") mobile_number: String,
        @Field("password") password: String,
        @Field("password_confirm") password_confirm: String,
        @Field("otp") otp: String
    ): Response<VerifyResponse>

    @FormUrlEncoded
    @POST("/master/api/login")
    suspend fun login(
        @Field("username_or_mobile") username_or_mobile: String,
        @Field("password") password: String
    ): Response<LoginResponse>

}

