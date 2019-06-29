package com.planensas.myapplication.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.planensas.myapplication.Entities.Estado;

import java.util.List;

@Dao
public interface EstadosDAO
{
        @Insert
        void insert(Estado estado);

        @Update
        void update(Estado estado);

        @Delete
        void delete(Estado estado);

        @Query("SELECT * FROM estados_table WHERE stateId = :id")
        LiveData<Estado> getestado(int id);

        @Query("SELECT * FROM estados_table WHERE estado = :name")
        LiveData<Estado> getestadoByName(String name);

        @Query("SELECT * FROM estados_table ORDER BY stateId")
        LiveData<List<Estado>> getAllEstados();

}
