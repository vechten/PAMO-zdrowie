package com.example.zdrowie.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> text;

    public HomeViewModel() {
        text = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return text;
    }
}