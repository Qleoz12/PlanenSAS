package com.planensas.myapplication.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.planensas.myapplication.DAOs.EstadosDAO;
import com.planensas.myapplication.DB.DatabaseLocal;
import com.planensas.myapplication.Entities.Estado;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EstadoRepository
{
    private EstadosDAO EstadosDAO;
    private LiveData<List<Estado>> allEstados;


    public EstadoRepository(Application application) {
        DatabaseLocal database = DatabaseLocal.getInstance(application);
        EstadosDAO = database.estadosDAO();
        allEstados = EstadosDAO.getAllEstados();
    }


    public void insert(Estado estado) {
        new InsertEstadoAsyncTask(EstadosDAO).execute(estado);
    }

    public void update(Estado estado) {
        new UpdateEstadoAsyncTask(EstadosDAO).execute(estado);
    }

    public void delete(Estado estado) {
        new DeleteEstadoAsyncTask(EstadosDAO).execute(estado);
    }

    public LiveData<Estado> getEstado(int id) {
        try {
            return new getEstadoAsyncTask(EstadosDAO).execute(id).get() ;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.v(this.getClass().getName(),"Error en el metodo getCliente");
        return null;
    }

    public LiveData<Estado> getEstadoByName(String name) {return EstadosDAO.getestadoByName(name);}

    public LiveData<List<Estado>> getAllEstados() {
        return allEstados;
    }

    private static class InsertEstadoAsyncTask extends AsyncTask<Estado, Void, Void> {
        private EstadosDAO estadosDAO;

        private InsertEstadoAsyncTask(EstadosDAO estadosDAO) {
            this.estadosDAO = estadosDAO;
        }

        @Override
        protected Void doInBackground(Estado... estados) {
            estadosDAO.insert(estados[0]);
            return null;
        }
    }

    private static class UpdateEstadoAsyncTask extends AsyncTask<Estado, Void, Void> {
        private EstadosDAO estadosDAO;

        private UpdateEstadoAsyncTask(EstadosDAO estadosDAO) {
            this.estadosDAO = estadosDAO;
        }

        @Override
        protected Void doInBackground(Estado... estados) {
            estadosDAO.update(estados[0]);
            return null;
        }
    }

    private static class DeleteEstadoAsyncTask extends AsyncTask<Estado, Void, Void> {
        private EstadosDAO estadosDAO;

        private DeleteEstadoAsyncTask(EstadosDAO estadosDAO) {
            this.estadosDAO = estadosDAO;
        }

        @Override
        protected Void doInBackground(Estado... estados) {
            estadosDAO.delete(estados[0]);
            return null;
        }
    }


    private static class getEstadoAsyncTask extends AsyncTask<Integer, Void, LiveData<Estado>> {
        private EstadosDAO estadosDAO;

        private getEstadoAsyncTask(EstadosDAO estadosDAO) {
            this.estadosDAO = estadosDAO;
        }

        @Override
        protected LiveData<Estado> doInBackground(Integer... integers) {
            return estadosDAO.getestado(integers[0]);
        }
    }

    
}
