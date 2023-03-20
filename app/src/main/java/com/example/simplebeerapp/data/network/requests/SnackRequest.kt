package com.example.simplebeerapp.data.network.requests

data class SnackRequest(
    val description: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val type: String = ""
)