package com.planensas.myapplication.Aspects;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.planensas.myapplication.MainActivity;
import com.planensas.myapplication.utils.AppVault;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class AspectLogin
{

    @Pointcut("execution(* onCreate(android.os.Bundle))")
    public void onCreate(){}

    @Pointcut("execution(void *.onClick(..))")
    public void onButtonClick() {}

    @Before("onButtonClick() && args(view)")
    public void onClickAdvice(View view) {
        if (view instanceof TextView) {
            String text = ((TextView) view).getText().toString();

        }
    }

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
                if(className.equals("MainActivity"))
                {
                    Log.v("AspectJ>>>>","Do nothing");
                }
                else
                {
                    if(!v.getLogin())
                    {
                        Log.v("AspectJ>>>>","no login go back");
                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                    }
                    else
                    {
                        Log.v("AspectJ>>>>","logged over->"+className);
                    }
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

    }

}
