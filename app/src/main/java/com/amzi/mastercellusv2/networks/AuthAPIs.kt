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
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>


    @FormUrlEncoded
    @POST("/starter_operator/verify_otp_and_set_password")
    suspend fun setPassword(
        @Field("mobile_no") mobile_no: String,
        @Field("otp") otp: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String
    ): Response<SetPassword>

}

