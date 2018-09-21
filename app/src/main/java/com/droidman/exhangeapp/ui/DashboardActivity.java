package com.droidman.exhangeapp.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.droidman.exhangeapp.common.Common;
import com.droidman.exhangeapp.adapter.ItemAdapter;
import com.droidman.exhangeapp.R;
import com.droidman.exhangeapp.common.LocationUtils;
import com.droidman.exhangeapp.model.Table;
import com.droidman.exhangeapp.network.ItemServices;
import com.droidman.exhangeapp.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private Location location;
    private double longitude = 0;
    private double latitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        LocationUtils locationUtils = new LocationUtils(this);
        location = locationUtils.getLocation();
        if(location != null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getData();
    }

    private void getData() {
        ItemServices service = RetrofitClientInstance.getRetrofitInstance().create(ItemServices.class);
        Call<Table> call = service.getAllItem(latitude, longitude);
        call.enqueue(new Callback<Table>() {
            @Override
            public void onResponse(Call<Table> call, Response<Table> response) {
                recyclerView = findViewById(R.id.dashboardRecyclerView);
                if(latitude == 0 && longitude == 0)
                    adapter = new ItemAdapter(getApplicationContext(),Common.getInstance().extractItemsFromResponse(response),false);
                else
                    adapter = new ItemAdapter(getApplicationContext(),Common.getInstance().extractItemsFromResponse(response),true);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Table> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Sorry something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



