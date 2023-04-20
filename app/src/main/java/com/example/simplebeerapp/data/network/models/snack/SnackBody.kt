package com.example.simplebeerapp.data.network.models.snack

import com.example.simplebeerapp.data.entities.Snack


data class Data(
    val UID: String = "",
    val createdAt: String = "",
    val description: String = "",
    val imagePath: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val tags: String? = null,
    val type: String = "",
    val updatedAt: String = "",
    val weight: Int? = null
)


fun snackBodyToSnack(snack: Data): Snack {
    return Snack(
        UID = snack.UID,
        name = snack.name,
        description = snack.description,
        price = snack.price,
        type = snack.type,
        tags = snack.tags,
        weight = snack.weight,
        imagePath = snack.imagePath
    )
}

