package com.example.simplebeerapp.ui.snacks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.data.data_source.BeerRepository
import com.example.simplebeerapp.data.network.bodies.SnacksAPIList
import com.example.simplebeerapp.data.network.bodies.GetSnackById
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SnacksViewModel : ViewModel() {

    private lateinit var repository: BeerRepository

    private val _snacksLiveData = MutableLiveData<SnacksAPIList?>()
    val snacksLiveData: LiveData<SnacksAPIList?> = _snacksLiveData

    private val _snackLiveData = MutableLiveData<GetSnackById?>()
    val snackLiveData: LiveData<GetSnackById?> = _snackLiveData

    fun init(db: BeerDB) {
        repository = BeerRepository(db)
    }

    fun getSnacksFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllSnacks()
            _snacksLiveData.postValue(response)
        }
    }

    fun getSnackById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getSnackById(id)
            _snackLiveData.postValue(response)
        }
    }

}