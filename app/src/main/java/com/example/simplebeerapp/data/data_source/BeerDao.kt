package com.example.simplebeerapp.data.data_source

import androidx.room.*
import com.example.simplebeerapp.data.model.Beer
import com.example.simplebeerapp.data.model.Snack

@Dao
interface BeerDao {

    //beer methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBeer(beer: Beer)

    @Query("SELECT * FROM BeerTable")
    suspend fun getBeers(): List<Beer>

    @Query("SELECT * FROM BeerTable WHERE isFavorite = true")
    suspend fun getFavBeers(): List<Beer>

    @Query("SELECT * FROM BeerTable WHERE beerType = :type")
    suspend fun getBeersByType(type: Int): List<Beer>

    @Query("SELECT * FROM BeerTable WHERE id = :id")
    suspend fun getBeerById(id: Int): Beer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBeer(beer: Beer)

    @Delete
    suspend fun deleteBeer(beer: Beer)

    //snack methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSnack(snack: Snack)

    @Query("SELECT * FROM SnackTable")
    suspend fun getSnacks(): List<Snack>

    @Query("DELETE FROM SnackTable")
    suspend fun deleteAllSnacks()
}