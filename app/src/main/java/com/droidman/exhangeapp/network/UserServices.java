package com.droidman.exhangeapp.network;

import com.droidman.exhangeapp.model.Table;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserServices {
    @GET("user/login.php")
    Call<Table> login(@Query("email") String email, @Query("password") String password);

    @GET("user/register.php")
    Call<Table> register(@Query("fname") String firstName, @Query("lname") String lastName, @Query("phone") String phone, @Query("email") String email, @Query("password") String password);
}
