package com.planensas.myapplication.DAOs;

import com.planensas.myapplication.Entities.Cliente;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ClienteDAO
{
    @Insert
    void insert(Cliente cliente);

    @Update
    void update(Cliente cliente);

    @Delete
    void delete(Cliente cliente);

    @Query("SELECT * FROM cliente_table ORDER BY customerId DESC")
    LiveData<List<Cliente>> getAllClientes();

}
