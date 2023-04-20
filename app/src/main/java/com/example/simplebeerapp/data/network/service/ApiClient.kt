package com.example.simplebeerapp.data.network.service

import android.media.Image
import com.example.simplebeerapp.data.network.SimpleResponse
import com.example.simplebeerapp.data.network.models.snack.SnacksAPIList
import com.example.simplebeerapp.data.network.models.snack.GetSnackById
import retrofit2.Response
import javax.inject.Inject

class ApiClient @Inject constructor(private val service: ApiService) {

    suspend fun getAllSnacks(): SimpleResponse<SnacksAPIList> {
        return safeApiCall { service.getAllSnacks() }
    }

    suspend fun getSnackById(id: String):SimpleResponse<GetSnackById> {
        return safeApiCall { service.getSnackById(id) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}
