package com.example.simplebeerapp.data.data_source

import android.util.Log
import com.example.simplebeerapp.data.network.bodies.SnacksAPIList
import com.example.simplebeerapp.data.network.network_model.Network_layer

class RetrofitRepository {

    suspend fun getAllSnacks(): SnacksAPIList? {
        val request = Network_layer.apiClient.getAllSnacks()

        if (request.isSuccessful) {
            Log.d("API", "Request was successful!")
            return request.body
        }
        Log.d("API", "Request was not successful!")
        return null
    }
}