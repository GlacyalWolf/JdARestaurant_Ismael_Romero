package com.example.jdarestaurant_mvvm.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.jdarestaurant_mvvm.Model.Reserva;

import java.util.List;

@Dao
public interface ReservaDao {
    @Insert
    void insertAll(Reserva... reservas);

    @Query("SELECT * FROM reserva")
    List<Reserva> getAll();

}
