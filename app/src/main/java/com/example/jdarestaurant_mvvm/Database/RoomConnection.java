package com.example.jdarestaurant_mvvm.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.jdarestaurant_mvvm.Dao.ReservaDao;
import com.example.jdarestaurant_mvvm.Model.Reserva;

@Database(entities = {Reserva.class},version = 1)
public abstract class RoomConnection extends RoomDatabase {
    private static RoomConnection INSTANCE;

    public abstract ReservaDao ciudDao();

    public static RoomConnection getRoomConnection(Context c){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(c,RoomConnection.class,"reserva.db").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }

    public static void destroyRoomConnection() {
        INSTANCE = null;
    }
}
