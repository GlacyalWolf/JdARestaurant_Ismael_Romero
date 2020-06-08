package com.example.jdarestaurant_mvvm;

import android.app.Application;

import com.example.jdarestaurant_mvvm.Repository.Repository;

public class DirectoryApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Repository.get(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();


    }
}
