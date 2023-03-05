package com.example.simplebeerapp.data.data_source

import androidx.lifecycle.LiveData
import com.example.simplebeerapp.data.model.Beer


class BeerRepository(private val db: BeerDB) {

    private val  dao: BeerDao = db.beerDao()

    suspend fun getBeers(): List<Beer> {
        return dao.getBeers()
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


}