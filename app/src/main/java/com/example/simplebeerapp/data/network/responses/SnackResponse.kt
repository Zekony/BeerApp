package com.example.simplebeerapp.data.network.responses

data class SnackResponse(
    val createdSnack: CreatedSnack = CreatedSnack(),
    val msg: String = ""
)