package com.example.simplebeerapp.ui.home

interface BeerAdapterClockListener {
    fun checkBoxUpdate(id: Int, isFavorite: Boolean)
    fun navigateTo(id: Int, beerName: String)
}