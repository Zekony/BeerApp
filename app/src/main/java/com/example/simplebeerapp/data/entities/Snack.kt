package com.example.simplebeerapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SnackTable")
data class Snack(
    @PrimaryKey
    val UID: String,
    val price: Double,
    @ColumnInfo("image_path")
    val imagePath: String?,
    val name: String,
    val description: String,
    val weight: Int?,
    val type: String,
    val tags: String?,
)
