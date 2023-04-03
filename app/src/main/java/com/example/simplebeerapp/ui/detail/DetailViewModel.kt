package com.example.simplebeerapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebeerapp.data.data_source.BeerRepository
import com.example.simplebeerapp.data.entities.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: BeerRepository) : ViewModel() {



    private val _beer = MutableLiveData<Beer>()
    val beer: LiveData<Beer> = _beer

    fun getBeerById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val beer = repository.getBeerById(id)
            _beer.postValue(beer!!)
            if (_beer.value == null){
                Log.d("DetailViewModel", "_beer.value is null")
            }
        }
    }
}