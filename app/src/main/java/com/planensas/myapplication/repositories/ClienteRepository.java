package com.planensas.myapplication.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.planensas.myapplication.DAOs.ClienteDAO;
import com.planensas.myapplication.DB.DatabaseLocal;
import com.planensas.myapplication.Entities.Cliente;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ClienteRepository
{
    private ClienteDAO clienteDAO;
    private LiveData<List<Cliente>> allClientes;


    public ClienteRepository(Application application) {
        DatabaseLocal database = DatabaseLocal.getInstance(application);
        clienteDAO = database.clienteDAO();
        allClientes = clienteDAO.getAllClientes();
    }


    public void insert(Cliente note) {
        new InsertNoteAsyncTask(clienteDAO).execute(note);
    }

    public void update(Cliente note) {
        new UpdateNoteAsyncTask(clienteDAO).execute(note);
    }

    public void delete(Cliente note) {
        new DeleteNoteAsyncTask(clienteDAO).execute(note);
    }

    public LiveData<Cliente> getCliente(int id) {
        try {
            return new getClienteAsyncTask(clienteDAO).execute(id).get() ;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.v(this.getClass().getName(),"Error en el metodo getCliente");
        return null;
    }

    public LiveData<List<Cliente>> getAllClientes() {
        return allClientes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Cliente, Void, Void> {
        private ClienteDAO clienteDAO;

        private InsertNoteAsyncTask(ClienteDAO clienteDAO) {
            this.clienteDAO = clienteDAO;
        }

        @Override
        protected Void doInBackground(Cliente... notes) {
            clienteDAO.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Cliente, Void, Void> {
        private ClienteDAO clienteDAO;

        private UpdateNoteAsyncTask(ClienteDAO clienteDAO) {
            this.clienteDAO = clienteDAO;
        }

        @Override
        protected Void doInBackground(Cliente... notes) {
            clienteDAO.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Cliente, Void, Void> {
        private ClienteDAO clienteDAO;

        private DeleteNoteAsyncTask(ClienteDAO clienteDAO) {
            this.clienteDAO = clienteDAO;
        }

        @Override
        protected Void doInBackground(Cliente... notes) {
            clienteDAO.delete(notes[0]);
            return null;
        }
    }


    private static class getClienteAsyncTask extends AsyncTask<Integer, Void, LiveData<Cliente>> {
        private ClienteDAO clienteDAO;

        private getClienteAsyncTask(ClienteDAO clienteDAO) {
            this.clienteDAO = clienteDAO;
        }

        @Override
        protected LiveData<Cliente> doInBackground(Integer... integers) {
            return clienteDAO.getCliente(integers[0]);
        }
    }

    
}
