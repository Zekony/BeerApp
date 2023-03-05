package com.example.simplebeerapp.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Проводятся технические работы, пожалуйста покиньте страничку"
    }
    val text: LiveData<String> = _text
}