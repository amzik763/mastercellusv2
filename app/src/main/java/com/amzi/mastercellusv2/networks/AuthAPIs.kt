package com.amzi.mastercellusv2.networks

import com.amzi.mastercellusv2.models.LoginResponse
import com.amzi.mastercellusv2.models.RegisterRes
import com.amzi.mastercellusv2.models.RegisterResponse
import com.amzi.mastercellusv2.models.SetPassword
import com.amzi.mastercellusv2.models.VerifyRegisterRes
import com.amzi.mastercellusv2.models.loginResponsePre
import com.amzi.mastercellusv2.models.verifyRes
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPIs {

    @FormUrlEncoded
    @POST("/master/api/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("/master/api/registeruser")
    suspend fun register(
        @Field("mobile_number") mobile_number: String,
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("fName") fName: String,
        @Field("lName") lName: String
    ): Response<RegisterRes>

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
    ): Response<loginResponsePre>

    @FormUrlEncoded
    @POST("/starter_operator/forgot_password")
    suspend fun verify(
        @Field("mobile_no") mobile_no: String
    ): Response<verifyRes>
}
