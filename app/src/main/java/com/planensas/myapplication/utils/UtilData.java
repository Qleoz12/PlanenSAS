package com.planensas.myapplication.utils;

public class UtilData
{
    public static final String EXTRA_ID = "com.plannen.EXTRA_ID";
    public static final String EXTRA_NOMBRE ="com.plannen.EXTRA_TITLE";
    public static final String EXTRA_APELLIDO ="com.plannen.EXTRA_APELLIDO";
    public static final String EXTRA_DIRECCION ="com.plannen.EXTRA_DIRECCION";
    public static final String EXTRA_TELEFONO ="com.plannen.EXTRA_TELEFONO";
    public static final String EXTRA_ESTADO ="com.plannen.EXTRA_ESTADO";


    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    //email
    private static final String emailPattern = "[a-zA-Z0-9._-]{4,}+@[a-z]{3,}+\\.+[a-z]{2,}";
    public static boolean validEmail(String email)
    {
        if(email.isEmpty() || !email.contains("@"))
        {
            return false;
        }else
        {
           return email.trim().matches(emailPattern);
        }
    }
    //userid
    public static final String Extra_userid ="com.plannen.Extra_userid" ;



}
