package com.planensas.myapplication.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "cliente_table")
public class Cliente {

    @PrimaryKey
    @NonNull
    private int customerId;
    @NonNull
    private String nombre;
    @NonNull
    private String apellido;
    private String direccion;
    private String telefono;
    @NonNull
    private int estado;

    public Cliente(int customerId, String nombre, String apellido, String direccion, String telefono, int estado)
    {
        this.customerId = customerId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getApellido() {
        return apellido;
    }

    public void setApellido(@NonNull String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @NonNull
    public int getEstado() {
        return estado;
    }

    public void setEstado(@NonNull int estado) {
        this.estado = estado;
    }
}
