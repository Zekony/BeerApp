package com.example.simplebeerapp.data.network.network_model

import com.example.simplebeerapp.data.network.*
import com.example.simplebeerapp.data.network.bodies.*
import com.example.simplebeerapp.data.network.bodies.GetSnackById
import com.example.simplebeerapp.data.network.requests.SnackRequest
import com.example.simplebeerapp.data.network.responses.SnackResponse
import retrofit2.Response
import retrofit2.http.*

interface API_service {

    @GET ("beverages")
    suspend fun getAllBeer(): Response<BeerApiList>

    @GET ("snacks")
    suspend fun getAllSnacks(): Response<SnacksAPIList>

    @GET ("beverages/{beerId}")
    suspend fun getBeerById(@Path ("beerId") beerId: String): Response<BeerApi>

    @GET ("snacks/{snackId}")
    suspend fun getSnackById(@Path ("snackId") snackId: String): Response<GetSnackById>

    @POST("snacks/add-snack")
    suspend fun addSnack(@Body snackRequest: SnackRequest): Response<SnackResponse>

    @DELETE("snacks/{snackId}")
    suspend fun deleteSnackById(@Path ("snackId") snackId: String ): Response<GetSnackById>
}

