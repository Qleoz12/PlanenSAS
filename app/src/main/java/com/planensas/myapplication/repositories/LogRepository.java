package com.planensas.myapplication.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.planensas.myapplication.DAOs.LogsDAO;
import com.planensas.myapplication.DB.DatabaseLocal;
import com.planensas.myapplication.Entities.LogData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LogRepository
{
    private LogsDAO logsDAO;
    private LiveData<List<LogData>> allLogs;


    public LogRepository(Application application) {
        DatabaseLocal database = DatabaseLocal.getInstance(application);
        logsDAO = database.logsDAO();
        allLogs = logsDAO.getAllogs();
    }


    public void insert(LogData Log) {
        new InsertLogAsyncTask(logsDAO).execute(Log);
    }

    public void update(LogData Log) {
        new UpdateLogAsyncTask(logsDAO).execute(Log);
    }

    public void delete(LogData Log) {
        new DeleteLogAsyncTask(logsDAO).execute(Log);
    }

    public LiveData<LogData> getLog(int id) {
        try {
            return new getLogAsyncTask(logsDAO).execute(id).get() ;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.v(this.getClass().getName(),"Error en el metodo getCliente");
        return null;
    }



    public LiveData<List<LogData>> getAllLogs() {
        return allLogs;
    }

    private static class InsertLogAsyncTask extends AsyncTask<LogData, Void, Void> {
        private LogsDAO LogsDAO;

        private InsertLogAsyncTask(LogsDAO LogsDAO) {
            this.LogsDAO = LogsDAO;
        }

        @Override
        protected Void doInBackground(LogData... Logs) {
            LogsDAO.insert(Logs[0]);
            return null;
        }
    }

    private static class UpdateLogAsyncTask extends AsyncTask<LogData, Void, Void> {
        private LogsDAO LogsDAO;

        private UpdateLogAsyncTask(LogsDAO LogsDAO) {
            this.LogsDAO = LogsDAO;
        }

        @Override
        protected Void doInBackground(LogData... Logs) {
            LogsDAO.update(Logs[0]);
            return null;
        }
    }

    private static class DeleteLogAsyncTask extends AsyncTask<LogData, Void, Void> {
        private LogsDAO LogsDAO;

        private DeleteLogAsyncTask(LogsDAO LogsDAO) {
            this.LogsDAO = LogsDAO;
        }

        @Override
        protected Void doInBackground(LogData... Logs) {
            LogsDAO.delete(Logs[0]);
            return null;
        }
    }


    private static class getLogAsyncTask extends AsyncTask<Integer, Void, LiveData<LogData>> {
        private LogsDAO LogsDAO;

        private getLogAsyncTask(LogsDAO LogsDAO) {
            this.LogsDAO = LogsDAO;
        }

        @Override
        protected LiveData<LogData> doInBackground(Integer... integers) {
            return LogsDAO.getlog(integers[0]);
        }
    }



    
}
