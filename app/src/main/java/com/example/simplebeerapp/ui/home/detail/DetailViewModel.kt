package com.example.simplebeerapp.ui.home.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.data.data_source.BeerRepository
import com.example.simplebeerapp.data.model.Beer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private lateinit var repository: BeerRepository

    private val _beer = MutableLiveData<Beer>()
    var beer: LiveData<Beer> = _beer

    fun init(db: BeerDB) {
        repository = BeerRepository(db)

    }

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