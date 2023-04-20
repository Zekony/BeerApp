package com.example.simplebeerapp.data.network.service

import android.media.Image
import com.example.simplebeerapp.data.network.*
import com.example.simplebeerapp.data.network.models.*
import com.example.simplebeerapp.data.network.models.snack.GetSnackById
import com.example.simplebeerapp.data.network.models.beer.BeerApi
import com.example.simplebeerapp.data.network.models.beer.BeerApiList
import com.example.simplebeerapp.data.network.models.snack.SnacksAPIList
import com.example.simplebeerapp.data.network.requests.SnackRequest
import com.example.simplebeerapp.data.network.responses.SnackResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET ("api/beverages")
    suspend fun getAllBeer(): Response<BeerApiList>

    @GET ("api/snacks")
    suspend fun getAllSnacks(): Response<SnacksAPIList>

    @GET ("api/beverages/{beerId}")
    suspend fun getBeerById(@Path ("beerId") beerId: String): Response<BeerApi>

    @GET ("api/snacks/{snackId}")
    suspend fun getSnackById(@Path ("snackId") snackId: String): Response<GetSnackById>

    @POST("api/snacks/add-snack")
    suspend fun addSnack(@Body snackRequest: SnackRequest): Response<SnackResponse>

    @DELETE("api/snacks/{snackId}")
    suspend fun deleteSnackById(@Path ("snackId") snackId: String ): Response<GetSnackById>
}

