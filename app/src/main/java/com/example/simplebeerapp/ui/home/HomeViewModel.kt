package com.example.simplebeerapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.data.data_source.BeerRepository
import com.example.simplebeerapp.data.model.Beer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private lateinit var repository: BeerRepository

    private val _beerList = MutableLiveData<List<Beer>>()
    var beerList: LiveData<List<Beer>> = _beerList

    fun init(db: BeerDB) {
        repository = BeerRepository(db)

    }

    fun filterBeers(list: List<Beer>, beerType: Int){
        if (beerType == 0) return
        _beerList.value =  list.filter { it.beerType == beerType }
    }

    fun getAllBeer() = viewModelScope.launch(Dispatchers.IO) {
        _beerList.postValue(repository.getBeers())
    }

    fun addBeerCor(beer: Beer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBeer(beer)
        }
    }

    fun updateDB(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _beerList.value?.first { it.id == id }?.let { repository.updateBeer(it)}
        }
    }

    fun deleteBeer(beer: Beer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBeer(beer)
        }
    }
}