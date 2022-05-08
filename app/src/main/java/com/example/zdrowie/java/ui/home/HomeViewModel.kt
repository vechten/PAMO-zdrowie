package com.example.zdrowie.java.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class HomeViewModel : ViewModel() {
    private val text: MutableLiveData<String> = MutableLiveData()
    fun getText(): LiveData<String> {
        return text
    }

}