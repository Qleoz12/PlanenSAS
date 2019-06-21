package com.planensas.myapplication.ViewModels;

import android.app.Application;

import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.repositories.ClienteRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ClienteViewModel extends AndroidViewModel {
    private ClienteRepository repository;
    private LiveData<List<Cliente>> allClientes;

    public ClienteViewModel(@NonNull Application application) {
        super(application);
        repository = new ClienteRepository(application);
        allClientes=repository.getAllNotes();
    }

    public void insert(Cliente Cliente) {
        repository.insert(Cliente);
    }

    public void update(Cliente Cliente) {
        repository.update(Cliente);
    }

    public void delete(Cliente Cliente) {
        repository.delete(Cliente);
    }

    public LiveData<List<Cliente>> getAllClientes() {
        return allClientes;
    }
}