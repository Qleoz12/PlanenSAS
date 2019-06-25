package com.planensas.myapplication.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.planensas.myapplication.Entities.Cliente;

import java.util.List;


@Dao
public interface ClienteDAO
{
    @Insert
    void insert(Cliente cliente);

    @Update
    void update(Cliente cliente);

    @Delete
    void delete(Cliente cliente);

    @Query("SELECT * FROM cliente_table WHERE customerId = :id")
    LiveData<Cliente> getCliente(int id);

    @Query("SELECT * FROM cliente_table ORDER BY customerId")
    LiveData<List<Cliente>> getAllClientes();
}
