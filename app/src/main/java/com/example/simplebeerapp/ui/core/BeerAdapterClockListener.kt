package com.example.simplebeerapp.ui.core

interface BeerAdapterClockListener {
    fun checkBoxUpdate(id: Int, isFavorite: Boolean)
    fun navigateTo(id: Int, beerName: String)
}