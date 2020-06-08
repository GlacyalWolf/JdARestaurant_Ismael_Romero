package com.example.jdarestaurant_mvvm.ui.carta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jdarestaurant_mvvm.Repository.Repository;

public class CartaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    Repository repo;

    public CartaViewModel() {

        repo= Repository.getRepository();
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }



    public void getPlatos() {
        repo.getPlatos();
    }
}