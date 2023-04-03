package com.example.simplebeerapp.ui.home

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
class HomeViewModel @Inject constructor(private val repository: BeerRepository) : ViewModel() {

    private val _beerList = MutableLiveData<List<Beer>>()
    var beerList: LiveData<List<Beer>> = _beerList

    fun filterBeers(beerType: String) {
        if (beerType == "") {
            viewModelScope.launch(Dispatchers.IO) {
                val beers = repository.getBeers()
                _beerList.postValue(beers)
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val beers = repository.getBeersByType(beerType)
                _beerList.postValue(beers)
            }
        }
    }

    fun getAllBeer() {
        viewModelScope.launch(Dispatchers.IO) {
            val beers = repository.getBeers()
            _beerList.postValue(beers)
        }
    }

    fun addBeerCor(beer: Beer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBeer(beer)
        }
    }

    fun updateDB(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _beerList.value?.first {
                it.id == id
            }?.let {
                it.isFavorite = isFavorite
                repository.updateBeer(it)
            }
        }
    }

}