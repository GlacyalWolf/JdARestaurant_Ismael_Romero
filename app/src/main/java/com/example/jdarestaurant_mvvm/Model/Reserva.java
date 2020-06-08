package com.example.jdarestaurant_mvvm.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reserva")
public class Reserva {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo
    private int uid;


    @ColumnInfo(name="fecha")
    private String fecha;
    @ColumnInfo(name="personas")
    private int personas;
    @ColumnInfo(name="comentarios")
    private String comentarios;
    @ColumnInfo(name="nombre")
    private String nombre;
    @ColumnInfo(name="telefono")
    private String telefono;

    public Reserva() {
    }

    public Reserva(String fecha, int personas, String comentarios, String nombre, String telefono) {
        this.fecha = fecha;
        this.personas = personas;
        this.comentarios = comentarios;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
