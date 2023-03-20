package com.example.simplebeerapp.data.network.responses

data class CreatedSnack(
    val UID: String = "",
    val createdAt: String = "",
    val description: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val type: String = "",
    val updatedAt: String = ""
)