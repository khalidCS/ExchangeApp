package com.droidman.exhangeapp.common;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.droidman.exhangeapp.model.Item;
import com.droidman.exhangeapp.model.User;
import com.droidman.exhangeapp.model.Table;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

import retrofit2.Response;

public class Common {
    private static Common common;

    private Common(){

    }

    public static Common getInstance(){
        if(common == null)
            common = new Common();
        return common;
    }

    public boolean isConnectedToInternet(ConnectivityManager connectivityManager){
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @NonNull
    public User extractUserFromResponse(Response<Table> response) {
        Table userTable = response.body();
        LinkedTreeMap<String, String> userTreeMap = (LinkedTreeMap<String, String>) userTable.getTable()[0];
        return new User(userTreeMap);
    }

    @NonNull
    public ArrayList<Item> extractItemsFromResponse(Response<Table> response) {
        ArrayList<Item> items = new ArrayList();
        Table itemTable = response.body();
        for(int i = 0; i<itemTable.getTable().length; i++) {
            LinkedTreeMap<String, Object> itemTreeMap = (LinkedTreeMap<String, Object>) itemTable.getTable()[i];
            items.add(new Item(itemTreeMap));
        }
        return items;
    }

}
