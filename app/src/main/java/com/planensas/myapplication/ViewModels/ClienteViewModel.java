package com.planensas.myapplication.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.repositories.ClienteRepository;

import java.util.List;



public class ClienteViewModel extends AndroidViewModel {
    private ClienteRepository repository;
    private LiveData<List<Cliente>> allClientes;
    private Cliente curreCliente;
    private int currentState;

    public ClienteViewModel(@NonNull Application application) {
        super(application);
        repository = new ClienteRepository(application);
        this.allClientes=repository.getAllClientes();
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

    public LiveData<Cliente> getCliente(int CustumerId) {return repository.getCliente(CustumerId);}

    public LiveData<List<Cliente>> getAllClientes() {
        return allClientes;
    }

    public Cliente getCurreCliente() {
        return curreCliente;
    }

    public void setCurreCliente(Cliente curreCliente) {
        this.curreCliente = curreCliente;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }
}