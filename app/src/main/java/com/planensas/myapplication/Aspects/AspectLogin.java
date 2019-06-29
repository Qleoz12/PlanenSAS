package com.planensas.myapplication.Aspects;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.planensas.myapplication.Activities.ClientList;
import com.planensas.myapplication.Entities.LogData;
import com.planensas.myapplication.MainActivity;
import com.planensas.myapplication.ViewModels.ClienteViewModel;
import com.planensas.myapplication.ViewModels.LogDataViewModel;
import com.planensas.myapplication.utils.AppVault;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Aspect
public class AspectLogin
{
    private static String tag="AspectJ>>>>";
    @Pointcut("execution(* onCreate(android.os.Bundle))")
    public void onCreate(){}

    LogDataViewModel logDataViewModel;

    @Before("onCreate()")
    public void onCreateAdvice(JoinPoint joinPoint)
    {

            Log.v("EEEEEEEEEx","ZZZZZZZZZZZZZZZZZZZZZZ ");
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String className = methodSignature.getDeclaringType().getSimpleName();
            String methodName = methodSignature.getName();
            Log.v("EEEEEEEEEx",className+" **********"+methodName);
            try
            {   //check logim
                Activity activity= (Activity) joinPoint.getThis();
                AppVault v= new AppVault(activity);
                Log.v("estado-login>>>>",""+v.getLogin());
                //Activities no check login
                if(className.equals("MainActivity") && !v.getLogin())
                {
                    Log.v(tag,"Do nothing");
                }
                else if(!className.equals("MainActivity") && v.getLogin())
                {
                   Log.v(tag,"logged over->"+className);
                }
                else if(className.equals("MainActivity") && v.getLogin())
                {
                    Log.v(tag,"Already Login");
                   Intent intent = new Intent(activity, ClientList.class);
                   activity.startActivity(intent);
                }
                else if(!className.equals("MainActivity") && !v.getLogin())
                {
                    Log.v(tag,"NOT Login");
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(activity, Activity.class);
                    activity.startActivity(intent);
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }

    }

    @Pointcut("execution(* GuardarClienteEdit())")
    public void GuardarClienteEdit(){}

    @Around("GuardarClienteEdit()")
    public void onGuardarClienteEdit(ProceedingJoinPoint joinPoint)
    {
        Log.v("ejecutando","s "+joinPoint.getClass().getName());
        //
        String operacion="operacion:-> edicion de usaurio";
        Activity activity= (Activity) joinPoint.getThis();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String usuario =new AppVault(activity).getUser();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date according to the chosen pattern
        DateFormat df = new SimpleDateFormat(pattern);
        // Get the today date using Calendar object.
        Date today = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String fecha =df.format(today);

        logDataViewModel = ViewModelProviders.of((FragmentActivity) activity).get(LogDataViewModel.class);

        try
        {
            Object result = joinPoint.proceed();
            logDataViewModel.insert(new LogData(operacion,className,methodName,usuario,fecha));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Log.v(tag,"Guardado log de "+operacion);
    }

}
