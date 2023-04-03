package com.example.simplebeerapp.data.entities

import com.example.simplebeerapp.data.network.bodies.Data

class Converters {
    fun snackBodyToSnack(snack: Data): Snack {
        return Snack(
            UID = snack.UID,
            name = snack.name,
            description = snack.description,
            price = snack.price,
            type = snack.type
        )
    }
}