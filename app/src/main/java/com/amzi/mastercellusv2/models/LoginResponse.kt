package com.amzi.mastercellusv2.models

data class LoginResponse(
    val access: String,
    val devices: List<Device>,
    val message: String,
    val refresh: String
)