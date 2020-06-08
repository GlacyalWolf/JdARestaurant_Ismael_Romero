package com.example.jdarestaurant_mvvm.ui.nueva_reserva;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.jdarestaurant_mvvm.Model.Reserva;
import com.example.jdarestaurant_mvvm.Repository.Repository;

public class NuevaReservaViewModel extends ViewModel {
    Repository repo;

    public NuevaReservaViewModel() {
        repo= Repository.getRepository();
    }

    public void insertReserva(Reserva reserva, Context c) {
        repo.insertReserva(reserva,c);
    }
    // TODO: Implement the ViewModel
}
