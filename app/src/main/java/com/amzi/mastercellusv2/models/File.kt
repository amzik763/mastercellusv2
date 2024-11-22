package com.amzi.mastercellusv2.models

data class File(
    val channel_name: String,
    val created_at: String,
    val device_mac: String,
    val folder: Int,
    val id: Int,
    val name: String,
    val user: Int
)