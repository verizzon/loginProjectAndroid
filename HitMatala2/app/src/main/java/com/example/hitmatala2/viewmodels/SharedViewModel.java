package com.example.hitmatala2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> username = new MutableLiveData<>();

    public void setUsername(String name) {
        username.setValue(name);
    }

    public LiveData<String> getUsername() {
        return username;
    }
}
