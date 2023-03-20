package com.example.simplebeerapp.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.data.data_source.BeerRepository
import com.example.simplebeerapp.data.model.Beer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel : ViewModel() {

    private lateinit var repository: BeerRepository

    private val _beerList = MutableLiveData<List<Beer>>()
    var beerList: LiveData<List<Beer>> = _beerList

    fun init(db: BeerDB) {
        repository = BeerRepository(db)
    }

    fun getFavBeer() {
        viewModelScope.launch(Dispatchers.IO) {
            val beers = repository.getFavBeers()
            _beerList.postValue(beers)
        }
    }

    fun updateDB(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _beerList.value?.first { it.id == id
            }?.let {
                it.isFavorite = isFavorite
                repository.updateBeer(it)
            }
            getFavBeer()
        }
    }
}