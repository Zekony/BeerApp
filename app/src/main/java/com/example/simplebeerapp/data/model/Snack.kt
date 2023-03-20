package com.example.simplebeerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SnackTable")
data class Snack(
    @PrimaryKey
    val UID: String,
    val name: String,
    val type: String,
    val price: Double,
    val description: String
)
