package com.amzi.mastercellusv2.models

data class loginResponse(
    val Response: String,
    val date_of_birth: String,
    val mobile_no: String,
    val password: String,
    val token: String
)