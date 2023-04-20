package com.example.simplebeerapp.ui.home.models

enum class FilterBeerType {
    DARK, LIGHT, IPA, NONE;

    fun toRawString(): String {
        return when (this){
            DARK -> "Dark"
            LIGHT -> "Light"
            IPA -> "IPA"
            NONE -> ""
        }
    }
}
