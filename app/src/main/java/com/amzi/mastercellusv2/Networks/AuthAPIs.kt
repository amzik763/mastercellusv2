package com.amzi.mastercellusv2.Networks

import com.amzi.mastercellusv2.models.RegisterResponse
import com.amzi.mastercellusv2.models.SetPassword
import com.amzi.mastercellusv2.models.VerifyRegisterRes
import com.amzi.mastercellusv2.models.loginResponse
import com.amzi.mastercellusv2.models.verifyRes
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPIs {

    @FormUrlEncoded
    @POST("/starter_operator/Register")
    suspend fun registerUser(
        @Field("mobile_no") mobile_no: String,
        @Field("username") username: String,
        @Field("date_of_birth") date_of_birth: String
    ): Response<RegisterResponse>

    @FormUrlEncoded
    @POST("/starter_operator/verify_otp_and_set_password")
    suspend fun setPassword(
        @Field("mobile_no") mobile_no: String,
        @Field("otp") otp: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String
    ): Response<SetPassword>

    @FormUrlEncoded
    @POST("/starter_operator/verify_otp_and_set_password")
    suspend fun verifyOtpSetPassword(
        @Field("mode") mode: String,
        @Field("mobile_no") mobile_no: String,
        @Field("otp") otp: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String

    ): Response<VerifyRegisterRes>


    @FormUrlEncoded
    @POST("/starter_operator/login")
    suspend fun loginUser(
        @Field("mobile_no") mobile_no: String,
        @Field("password") password: String
    ): Response<loginResponse>

    @FormUrlEncoded
    @POST("/starter_operator/forgot_password")
    suspend fun verify(
        @Field("mobile_no") mobile_no: String
    ): Response<verifyRes>



}
