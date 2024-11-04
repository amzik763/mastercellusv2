package com.amzi.mastercellusv2.models

data class Folder(
    val created_at: String,
    val id: Int,
    val name: String,
    val parent_id: Int,
    val user: Int
)