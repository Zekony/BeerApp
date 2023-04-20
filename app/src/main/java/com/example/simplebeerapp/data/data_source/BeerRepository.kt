package com.example.simplebeerapp.data.data_source

import android.media.Image
import android.util.Log
import com.example.simplebeerapp.data.entities.Beer
import com.example.simplebeerapp.data.entities.Snack
import com.example.simplebeerapp.data.network.models.snack.GetSnackById
import com.example.simplebeerapp.data.network.models.snack.snackBodyToSnack
import com.example.simplebeerapp.data.network.service.ApiClient
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val dao: BeerDao,
    private val apiClient: ApiClient
) {

    suspend fun getBeers(): List<Beer> {
        return dao.getBeers()
    }

    suspend fun getBeersByType(type: String): List<Beer> {
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

    // DB snack functions

    suspend fun addSnack(snack: Snack) {
        dao.addSnack(snack)
    }

    suspend fun getSnacks(): List<Snack> {
        return dao.getSnacks()
    }

    suspend fun deleteAllSnacks() {
        dao.deleteAllSnacks()
    }

    // API functions

    suspend fun getAllSnacksAPI(): List<Snack>? {
        val request = apiClient.getAllSnacks()

        if (request.failed) {
            Log.d("Repository", "Request has failed! ${request.exception?.message}")
            return null
        }
        if (!request.isSuccessful) {
            Log.d("Repository", "Request was not successful!")
            return null
        }
        val snackList = mutableListOf<Snack>()
        for (i in request.body.data) {
            val snack = snackBodyToSnack(i)
            snackList.add(snack)
        }
        if (request.isSuccessful) {
            Log.d("Repository", "Request was successful!")
            if (request.body.data.isEmpty()) {
                Log.d("Repository", "Request was successful, but list is empty!")
            }
            return snackList
        }
        return null
    }

    suspend fun getSnackById(id: String): GetSnackById? {
        val request = apiClient.getSnackById(id)
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
        return null
    }
}