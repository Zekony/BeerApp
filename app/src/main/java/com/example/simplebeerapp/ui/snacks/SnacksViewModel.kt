package com.example.simplebeerapp.ui.snacks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebeerapp.data.data_source.BeerRepository
import com.example.simplebeerapp.data.entities.Snack
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SnacksViewModel @Inject constructor(private val repository: BeerRepository) : ViewModel() {


    private val _snacksLiveData = MutableLiveData<List<Snack>?>()
    val snacksLiveData: LiveData<List<Snack>?> = _snacksLiveData


    fun getSnacksFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            var snackFromDB = repository.getSnacks()
            if (snackFromDB.isNotEmpty()) {
                Log.d("SnacksViewModel", "SnackDB is not empty, getting snack list from it")
            }
            if (snackFromDB.isEmpty()) {
                val response = repository.getAllSnacksAPI()
                Log.d("SnacksViewModel", "SnackDB is empty, making a request")
                if (response != null) {
                    for (i in response)
                        repository.addSnack(i)
                }
                snackFromDB = repository.getSnacks()
            }
            // uncomment method to clear Snack DB to check how cashing works
            // repository.deleteAllSnacks()
            _snacksLiveData.postValue(snackFromDB)
        }
    }
}