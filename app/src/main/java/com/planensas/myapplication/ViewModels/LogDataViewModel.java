package com.planensas.myapplication.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.planensas.myapplication.Entities.LogData;
import com.planensas.myapplication.repositories.LogRepository;

import java.util.List;


public class LogDataViewModel extends AndroidViewModel
{
        private LogRepository repository;
        private LiveData<List<LogData>> allEstados;


        public LogDataViewModel(@NonNull Application application) {
            super(application);
            repository = new LogRepository(application);
            this.allEstados= repository.getAllLogs();
        }

        public void insert(LogData LogData) {
            repository.insert(LogData);
        }

        public void update(LogData LogData) {
            repository.update(LogData);
        }

        public void delete(LogData LogData) {
            repository.delete(LogData);
        }

        public LiveData<LogData> getEstado(int CustumerId) {return repository.getLog(CustumerId);}
        public LiveData<List<LogData>> getAllEstados() {
            return allEstados;
        }


}

