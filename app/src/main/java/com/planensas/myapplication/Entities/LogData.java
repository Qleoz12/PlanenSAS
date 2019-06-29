package com.planensas.myapplication.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "logs_table")
public class LogData
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int logId;
    @NonNull
    private String operacion;

    @NonNull
    private String actividad;

    @NonNull
    private String Metodo;

    @NonNull
    private String usuario;

    @NonNull
    private String Fecha;


    public LogData(int logId, @NonNull String operacion, @NonNull String actividad, @NonNull String metodo, @NonNull String usuario, @NonNull String fecha) {
        this.logId = logId;
        this.operacion = operacion;
        this.actividad = actividad;
        Metodo = metodo;
        this.usuario = usuario;
        Fecha = fecha;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    @NonNull
    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(@NonNull String operacion) {
        this.operacion = operacion;
    }

    @NonNull
    public String getActividad() {
        return actividad;
    }

    public void setActividad(@NonNull String actividad) {
        this.actividad = actividad;
    }

    @NonNull
    public String getMetodo() {
        return Metodo;
    }

    public void setMetodo(@NonNull String metodo) {
        Metodo = metodo;
    }

    @NonNull
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(@NonNull String usuario) {
        this.usuario = usuario;
    }

    @NonNull
    public String getFecha() {
        return Fecha;
    }

    public void setFecha(@NonNull String fecha) {
        Fecha = fecha;
    }
}
