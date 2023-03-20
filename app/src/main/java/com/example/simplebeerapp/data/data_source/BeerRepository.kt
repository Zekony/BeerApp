package com.example.simplebeerapp.data.data_source

import android.util.Log
import com.example.simplebeerapp.data.model.Beer
import com.example.simplebeerapp.data.network.bodies.SnacksAPIList
import com.example.simplebeerapp.data.network.bodies.GetSnackById
import com.example.simplebeerapp.data.network.network_model.Network_layer


class BeerRepository(private val db: BeerDB) {

    private val dao: BeerDao = db.beerDao()

    // DB functions

    suspend fun getBeers(): List<Beer> {
        return dao.getBeers()
    }

    suspend fun getBeersByType(type: Int): List<Beer> {
        return dao.getBeersByType(type)
    }

    suspend fun getFavBeers(): List<Beer> {
        return dao.getFavBeers()
    }

    suspend fun getBeerById(id: Int): Beer? {
        return dao.getBeerById(id)
    }

    suspend fun addBeer(beer: Beer) {
        dao.addBeer(beer)
    }

    suspend fun updateBeer(beer: Beer) {
        dao.updateBeer(beer)
    }

    suspend fun deleteBeer(beer: Beer) {
        dao.deleteBeer(beer)
    }

    // API functions

    suspend fun getAllSnacks(): SnacksAPIList? {
        val request = Network_layer.apiClient.getAllSnacks()
        if (request.failed) {
            Log.d("API", "Request has failed!")
            return null
        }
        if (!request.isSuccessful) {
            Log.d("API", "Request was not successful!")
            return null
        }
        if (request.isSuccessful) {
            Log.d("API", "Request was successful!")
            if (request.body.data.isEmpty()) {
                Log.d("API", "Request was successful, but list is empty!")
            }
            return request.body
        }
        Log.d("API", "Request was... i don't know man!")
        return null
    }

    suspend fun getSnackById(id: String): GetSnackById? {
        val request = Network_layer.apiClient.getSnackById(id)
        if (request.failed) {
            Log.d("API", "Request has failed!")
            return null
        }
        if (!request.isSuccessful) {
            Log.d("API", "Request was not successful!")
            return null
        }
        if (request.isSuccessful) {
            Log.d("API", "Request was successful!")
            return request.body
        }
        Log.d("API", "Request was... i don't know man!")
        return null
    }
}