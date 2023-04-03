package com.example.simplebeerapp.data.network.network_model

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object Network_layer {
     const val base_URL = "https://mr-beers-backend.onrender.com/api/"
  /*  private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
     val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(base_URL)
        .build()
    private val retrofitService: APIservice by lazy { retrofit.create(APIservice::class.java)}
    val apiClient: ApiClient = ApiClient(retrofitService)*/
}
