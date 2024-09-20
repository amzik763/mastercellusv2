package com.amzi.mastercellusv2.models

data class Device(
    val device_mac: String,
    val device_name: String,
    val fan: Int,
    val led_bulb: Int
)