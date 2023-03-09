package com.example.simplebeerapp.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplebeerapp.data.model.Beer

@Database(
    entities = [Beer::class],
    version = 5
)
abstract class BeerDB: RoomDatabase() {

    abstract fun beerDao(): BeerDao

    companion object {
        @Volatile
        private var INSTANCE: BeerDB? = null

        fun getDatabase(context: Context): BeerDB {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(

                    context.applicationContext,
                    BeerDB::class.java,
                    "BeerTable"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}