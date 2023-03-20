package com.example.simplebeerapp.ui.snacks

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplebeerapp.data.data_source.BeerDB
import com.example.simplebeerapp.data.data_source.BeerRepository
import com.example.simplebeerapp.data.model.Snack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SnacksViewModel : ViewModel() {

    private lateinit var repository: BeerRepository

    private val _snacksLiveData = MutableLiveData<List<Snack>?>()
    val snacksLiveData: LiveData<List<Snack>?> = _snacksLiveData

    fun init(db: BeerDB) {
        repository = BeerRepository(db)
    }

    fun getSnacksFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            var snackFromDB = repository.getSnacks()
            if (snackFromDB.isEmpty()) {
                val response = repository.getAllSnacksAPI()
                if (response != null) {
                    for (i in response)
                        repository.addSnack(i)
                }
                snackFromDB = repository.getSnacks()
                // uncomment method to clear Snack DB to check how cashing works
                // repository.deleteAllSnacks()
            }
            _snacksLiveData.postValue(snackFromDB)
        }
    }
}