package com.example.demo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temperature")
data class Temperature(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val temperature: String
)
