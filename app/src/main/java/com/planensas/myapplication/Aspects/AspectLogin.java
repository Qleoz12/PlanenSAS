package com.planensas.myapplication.Aspects;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.planensas.myapplication.Activities.ClientList;
import com.planensas.myapplication.MainActivity;
import com.planensas.myapplication.utils.AppVault;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class AspectLogin
{
    private static String tag="AspectJ>>>>";
    @Pointcut("execution(* onCreate(android.os.Bundle))")
    public void onCreate(){}


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

    @Pointcut("execution(* ClientEdit.GuardarClienteEdit())")
    public void GuardarClienteEdit(){}

    @Around("GuardarClienteEdit()")
    public void onGuardarClienteEdit(ProceedingJoinPoint joinPoint)
    {
        Log.v("ejecutando","s "+joinPoint.getClass().getName());
        try
        {
            Object result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Log.v("EEEEEEEEEY","ZZZZZZZZZZZZZZZZZZZZZZ ");
    }

}
