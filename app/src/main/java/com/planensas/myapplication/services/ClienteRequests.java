package com.planensas.myapplication.services;

import com.planensas.myapplication.Entities.Cliente;
import com.planensas.myapplication.Entities.Models.Login;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClienteRequests
{
    @GET("customers.php")
    Call<List<Cliente>> getClientes(@Query("userId") Long userId);

    @GET("login.php")
    Call<Login> loginApi(@Query("email") String email,@Query("pass") String password);
}
