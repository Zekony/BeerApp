package com.example.simplebeerapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplebeerapp.data.entities.Beer
import com.example.simplebeerapp.data.entities.Snack

@Database(
    entities = [Beer::class, Snack::class], version = 11
)
abstract class BeerDB : RoomDatabase() {

    abstract fun beerDao(): BeerDao

    /* companion object {
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
                 val migration9to10 = object : Migration(9, 10) {
                 override fun migrate(database: SupportSQLiteDatabase) {
             database.execSQL(
                 "DROP TABLE BeerTable"
             )
             database.execSQL(
                 "CREATE TABLE IF NOT EXISTS BeerTable(id INTEGER NOT NULL PRIMARY KEY," +
                         " name TEXT NOT NULL, info INTEGER NOT NULL, " +
                         "imageResource INTEGER NOT NULL, beerType TEXT NOT NULL, " +
                         "cost INTEGER NOT NULL, isFavorite INTEGER NOT NULL)"
             )
         }
     }
         val migration10to11 = object : Migration(10, 11) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE SnackTable " +
                        "ADD COLUMN tags TEXT"
            )
            database.execSQL(
                "ALTER TABLE SnackTable " +
                        " ADD COLUMN weight INTEGER"
            )
            database.execSQL(
                "ALTER TABLE SnackTable " +
                        " ADD COLUMN image_path TEXT"
            )
        }
    }
             synchronized(this) {
                 val instance = Room.databaseBuilder(
                     context.applicationContext, BeerDB::class.java, "BeerTable"
                 ).build()

                 INSTANCE = instance
                 return instance
             }
         }
     }*/
}