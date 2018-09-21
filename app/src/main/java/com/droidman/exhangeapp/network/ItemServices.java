package com.droidman.exhangeapp.network;

import com.droidman.exhangeapp.model.Table;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItemServices {

    @GET("getMain.php")
    Call<Table> getAllItem(@Query("lat") double lat, @Query("lang") double lang);
}
