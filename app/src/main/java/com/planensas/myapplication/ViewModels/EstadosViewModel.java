package com.planensas.myapplication.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.planensas.myapplication.Entities.Estado;
import com.planensas.myapplication.repositories.EstadoRepository;

import java.util.List;

public class EstadosViewModel extends AndroidViewModel
{
    private EstadoRepository repository;
    private LiveData<List<Estado>> allEstados;


    public EstadosViewModel(@NonNull Application application) {
        super(application);
        repository = new EstadoRepository(application);
        this.allEstados= repository.getAllEstados();
    }

    public void insert(Estado Estado) {
        repository.insert(Estado);
    }

    public void update(Estado Estado) {
        repository.update(Estado);
    }

    public void delete(Estado Estado) {
        repository.delete(Estado);
    }

    public LiveData<Estado> getCliente(int CustumerId) {return repository.getCliente(CustumerId);}
    public LiveData<Estado> getClienteByName(String name) {return repository.getClienteByName(name);}
    public LiveData<List<Estado>> getAllEstados() {
        return allEstados;
    }


}
