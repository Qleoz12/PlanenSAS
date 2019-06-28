package com.planensas.myapplication.DB;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.planensas.myapplication.DAOs.ClienteDAO;
import com.planensas.myapplication.DAOs.EstadosDAO;
import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.Entities.Estado;



@Database(entities = {Cliente.class,Estado.class}, version = 2)
public abstract class DatabaseLocal extends RoomDatabase
{
    private static DatabaseLocal instance;
    public abstract ClienteDAO clienteDAO();
    public abstract EstadosDAO estadosDAO();

    public static synchronized DatabaseLocal getInstance(Context context)
    {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseLocal.class, "database_local")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private EstadosDAO estadosDAO;

        private PopulateDbAsyncTask(DatabaseLocal db)
        {
            estadosDAO = db.estadosDAO();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            estadosDAO.insert(new Estado(0, "pending",""));
            estadosDAO.insert(new Estado(1, "approved",""));
            estadosDAO.insert(new Estado(2, "accepted",""));
            estadosDAO.insert(new Estado(3, "rejected",""));
            estadosDAO.insert(new Estado(4, "disabled",""));
            return null;
        }
    }
}
