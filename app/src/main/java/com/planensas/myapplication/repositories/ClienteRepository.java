package com.planensas.myapplication.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.planensas.myapplication.DAOs.ClienteDAO;
import com.planensas.myapplication.DB.DatabaseLocal;
import com.planensas.myapplication.Entities.Cliente;

import java.util.List;

import androidx.lifecycle.LiveData;

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

    public LiveData<List<Cliente>> getAllNotes() {
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

    
}
