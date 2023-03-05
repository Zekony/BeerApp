package com.example.simplebeerapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplebeerapp.data.data_source.BeerRepository

class HomeViewModelFactory(
    private val repository: BeerRepository
): ViewModelProvider.NewInstanceFactory() {

/*    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }*/
}