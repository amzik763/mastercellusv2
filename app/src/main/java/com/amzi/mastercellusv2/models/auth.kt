package com.amzi.mastercellusv2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth")
data class auth(

    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
)
