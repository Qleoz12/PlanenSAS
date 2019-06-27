package com.planensas.myapplication.utils;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;



public class ErrorCenter
{
    private Context context;
    public ErrorCenter(@NonNull Application application)
    {
        this.context=application.getBaseContext();
    }

    public void ShowError(String string)
    {
        Toast.makeText(this.context,string,Toast.LENGTH_SHORT).show();
    }
}
