package com.planensas.myapplication.DB;

import android.content.Context;

import com.planensas.myapplication.DAOs.ClienteDAO;
import com.planensas.myapplication.Entities.Cliente;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cliente.class}, version = 1)
public abstract class DatabaseLocal extends RoomDatabase
{
    private static DatabaseLocal instance;
    public abstract ClienteDAO clienteDAO();

    public static synchronized DatabaseLocal getInstance(Context context)
    {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseLocal.class, "database_local")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
