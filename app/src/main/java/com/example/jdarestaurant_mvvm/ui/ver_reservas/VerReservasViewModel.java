package com.example.jdarestaurant_mvvm.ui.ver_reservas;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.jdarestaurant_mvvm.Model.Reserva;
import com.example.jdarestaurant_mvvm.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class VerReservasViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Reserva>> mText;
    ArrayList<Reserva> listres= new ArrayList<>();
    Repository repo;


    public VerReservasViewModel() {
        mText = new MutableLiveData<ArrayList<Reserva>>();


        repo= Repository.getRepository();
        listres= new ArrayList<>();
    }




    public void getReservas(Context context) {
        repo.getReservas(context);
        repo.getlivereservas().observeForever(new Observer<ArrayList<Reserva>>() {
            @Override
            public void onChanged(ArrayList<Reserva> reservas) {
                listres=reservas;
                mText.postValue(listres);
            }
        });
    }

    public MutableLiveData<ArrayList<Reserva>> getlivereserva(){
        return mText;
    }

    public Reserva getPosicio(int posicio){
        return listres.get(posicio);
    }
}