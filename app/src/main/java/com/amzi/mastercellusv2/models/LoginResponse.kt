package com.amzi.mastercellusv2.models

data class LoginResponse(
    val access: String,
    val devices: List<Device>,
    val message: String,
    val mobile_no: String,
    val refresh: String,
    val user_id: Int,
    val username: String
)