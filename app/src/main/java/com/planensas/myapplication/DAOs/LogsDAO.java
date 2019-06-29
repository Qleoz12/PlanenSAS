package com.planensas.myapplication.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.planensas.myapplication.Entities.LogData;

import java.util.List;

@Dao
public interface LogsDAO
{
        @Insert
        void insert(LogData log);

        @Update
        void update(LogData log);

        @Delete
        void delete(LogData log);

        @Query("SELECT * FROM logs_table WHERE logId = :id")
        LiveData<LogData> getlog(int id);



        @Query("SELECT * FROM logs_table ORDER BY logId")
        LiveData<List<LogData>> getAllogs();

}
