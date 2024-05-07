package com.amzi.mastercellusv2.Networks

import com.amzi.mastercellusv2.models.RegisterResponse
import com.amzi.mastercellusv2.models.SetPassword
import com.amzi.mastercellusv2.models.model_forget_password
import com.amzi.mastercellusv2.models.model_login
import com.amzi.mastercellusv2.models.model_set_password
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
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

}
