package com.example.simplebeerapp.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.simplebeerapp.data.model.Beer

@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBeer(beer: Beer)

    @Query("SELECT * FROM BeerTable")
    fun getBeers(): List<Beer>

    @Query("SELECT * FROM BeerTable WHERE isFavorite = :isFavorite")
    fun getFavBeers(isFavorite: Boolean = true): List<Beer>

    @Query("SELECT * FROM BeerTable WHERE id = :id")
    suspend fun getBeerById(id: Int): Beer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBeer(beer: Beer)

    @Delete
    suspend fun deleteBeer(beer: Beer)

}