package com.amzi.mastercellusv2.models

data class RootFolder(
    val created_at: String,
    val id: Int,
    val name: String,
    val parent_id: Any,
    val user: Int
)