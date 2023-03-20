package com.example.simplebeerapp.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.simplebeerapp.data.model.Beer
import com.example.simplebeerapp.data.model.Snack

@Database(
    entities = [Beer::class, Snack::class], version = 8
)
abstract class BeerDB : RoomDatabase() {

    abstract fun beerDao(): BeerDao

    companion object {
        @Volatile
        private var INSTANCE: BeerDB? = null

        fun getBeerDatabase(context: Context): BeerDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            val migration7to8 = object : Migration(7, 8) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL(
                        "CREATE TABLE IF NOT EXISTS SnackTable(name TEXT NOT NULL, UID TEXT NOT NULL PRIMARY KEY, description TEXT NOT NULL, type TEXT NOT NULL, price REAL NOT NULL)"
                    )
                }
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, BeerDB::class.java, "BeerTable"
                ).addMigrations(migration7to8).build()

                INSTANCE = instance
                return instance
            }
        }
/*        fun getSnackDatabase(context: Context): BeerDB {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(

                    context.applicationContext,
                    BeerDB::class.java,
                    "SnackTable"
                ).build()

                INSTANCE = instance
                return instance
            }
        }*/
    }
}