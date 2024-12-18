package com.amzi.mastercellusv2.networks

import com.amzi.mastercellusv2.models.CreateFolderRes
import com.amzi.mastercellusv2.models.GetFolderRes
import com.amzi.mastercellusv2.models.GetFolderResV2
import com.amzi.mastercellusv2.models.RegisterUserDeviceRes
import com.amzi.mastercellusv2.models.fileCreatedRes
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface HomeAutoApi {
    @GET("/master/api/protected")
    suspend fun protected(
        @Header("Authorization") accessToken: String
    ): Response<String>

    @FormUrlEncoded
    @POST("/homeautomation/api/registeruserdevice")
    suspend fun registerUserDevice(
        @Header("Authorization") authorizationHeader: String,
        @Field("mobile_number") mobile_number: String,
        @Field("device_mac") device_mac: String
    ): Response<RegisterUserDeviceRes>

    @FormUrlEncoded
    @POST("/master/api/createfolder")
    suspend fun createFolder(
        @Header("Authorization") authorizationHeader: String,
        @Field("name") name: String,
        @Field("parent_id") parent_id: String,
        @Field("user") user: String
    ) : Response<CreateFolderRes>

    @FormUrlEncoded
    @POST("/master/api/getfolderandfile")
    suspend fun getFolderAndFile(
        @Header("Authorization") authorizationHeader: String,
        @Field("user_id") user: String,
        @Field("parent_id") parent_id: String
    ) : Response<GetFolderResV2>


    @FormUrlEncoded
    @POST("/master/api/createfile")
    suspend fun createFile(
        @Header("Authorization") authorizationHeader: String,
        @Field("name") name: String,
        @Field("device_mac") device_mac: String,
        @Field("channel_name") channel_name: String,
        @Field("folder") folder: String,
        @Field("user") user: String
    ) : Response<fileCreatedRes>


}