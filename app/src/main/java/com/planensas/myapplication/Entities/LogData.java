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
    private String metodo;

    @NonNull
    private String usuario;

    @NonNull
    private String fecha;


    public LogData(@NonNull String operacion, @NonNull String actividad, @NonNull String metodo, @NonNull String usuario, @NonNull String fecha) {
        this.operacion = operacion;
        this.actividad = actividad;
        this.metodo = metodo;
        this.usuario = usuario;
        this.fecha = fecha;
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
        return metodo;
    }

    public void setMetodo(@NonNull String metodo) {
        this.metodo = metodo;
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
        return fecha;
    }

    public void setFecha(@NonNull String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return    logId +"\n"+
                "operacion='" + operacion +"\n"+
                "actividad='" + actividad +"->"+metodo + '\'' +"\n"+
                "usuario='" + usuario + '\'' +"\n"+
                "fecha='" + fecha + '\'';
    }
}
