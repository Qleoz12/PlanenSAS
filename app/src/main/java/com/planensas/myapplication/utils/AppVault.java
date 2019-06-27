package com.planensas.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static android.content.Context.MODE_PRIVATE;

public class AppVault
{
    private static final String SHARED_PREFERENCE_NAME = "AppVaultSharedPreferences";
    private static final String ENCRYPTED_user= "com.planensas.userdata";
    private static final String ENCRYPTED_pass= "com.planensas.passdata";
    private static final String ENCRYPTED_Login= "com.planensas.login";
    private static final String ENCRYPTED_Login_state= "Loggued";

    private static final String LOG_TAG = AppVault.class.getName();
    Cryptography shield;
    private final Context mContext;


    public AppVault(Context mContext)
    {
        this.mContext = mContext;
        shield=new Cryptography(mContext);


    }


    public void saveUser(String data)
    {
        try
        {
            String key=shield.encryptData(data);
            SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(encrypt(ENCRYPTED_user), encrypt(key));
            editor.apply(); // Or commit if targeting old devices

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public void savepass(String data)
    {

        try
        {
            String key=shield.encryptData(data);
            SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(encrypt(ENCRYPTED_pass), encrypt(key));
            editor.apply(); // Or commit if targeting old devices

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public String getUser()
    {
        try {
        SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
            if(preferences.contains(encrypt(ENCRYPTED_user)))
            {
                String dataEncrypted = preferences.getString(encrypt(ENCRYPTED_user),encrypt("default"));
                return shield.decryptData(decrypt(dataEncrypted));
            }


        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String getPass()
    {
        try
        {
            SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
            if(preferences.contains(encrypt(ENCRYPTED_pass)))
            {
                String dataEncrypted = preferences.getString(encrypt(ENCRYPTED_pass),encrypt("default"));
                return shield.decryptData(decrypt(dataEncrypted));
            }
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    //forLogin
    public boolean getLogin()
    {
        if(getUser()!=null && !getUser().equals("Error") && getPass()!=null && !getPass().equals("Error"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean logout()
    {
        //clean shield
        try {
            shield.removeKeys();
            SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
            preferences.edit().clear().commit();
            return true;
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String encrypt(String input) {
        // This is base64 encoding, which is not an encryption
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    private static String decrypt(String input) {
        return new String(Base64.decode(input, Base64.DEFAULT));
    }


}
