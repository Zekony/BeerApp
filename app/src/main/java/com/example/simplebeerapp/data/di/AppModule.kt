package com.example.simplebeerapp.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.data.data_source.BeerDao
import com.example.simplebeerapp.data.network.network_model.APIservice
import com.example.simplebeerapp.data.network.network_model.ApiClient
import com.example.simplebeerapp.data.network.network_model.Network_layer
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIservice {
        return retrofit.create(APIservice::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Network_layer.base_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }



    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): BeerDB {
        return Room.databaseBuilder(context, BeerDB::class.java, "BeerTable").build()
    }

    @Provides
    @Singleton
    fun providesBeerDao(database: BeerDB): BeerDao {
        return database.beerDao()
    }

    @Provides
    @Singleton
    fun providesApiClient(apiService: APIservice): ApiClient {
        return ApiClient(apiService)
    }
}