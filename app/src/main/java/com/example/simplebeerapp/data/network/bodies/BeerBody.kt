package com.example.simplebeerapp.data.network.bodies

import com.squareup.moshi.Json

data class BeerBody(
    @Json(name = "UID")
    val id: String = "",
    val alcPercentage: Double = 0.0,
    val createdAt: String = "",
    val description: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val type: String = "",
    val updatedAt: String = "",
    val volume: Double = 0.0
)