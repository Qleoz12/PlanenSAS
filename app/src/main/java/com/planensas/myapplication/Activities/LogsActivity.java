package com.planensas.myapplication.Activities;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.planensas.myapplication.Entities.LogData;
import com.planensas.myapplication.R;
import com.planensas.myapplication.ViewModels.LogDataViewModel;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

import butterknife.BindView;

public class LogsActivity extends BaseActivity {
    //views
    @BindView(R.id.ListViewLogs) ListView listView;
    //data
    LogDataViewModel logDataViewModel;
    ArrayList<String> logs;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initViews();


    }

    @Override
    public int getLayoutId() { return R.layout.activity_logs; }

    public void initViews()
    {
        logs = new ArrayList<String>();

        logDataViewModel=ViewModelProviders.of(this).get(LogDataViewModel.class);
        logDataViewModel.getAllLogs().observe(this, new Observer<List<LogData>>() {
            @Override
            public void onChanged(@Nullable List<LogData> logsData)
            {
                for (LogData log :logsData)
                {

                    String logdata=log.toString();
                    logs.add(logdata);
                }
                adapter = new ArrayAdapter<String>(LogsActivity.this, android.R.layout.simple_list_item_1, logs);
                listView.setAdapter(adapter);
            }
        });

    }

}
