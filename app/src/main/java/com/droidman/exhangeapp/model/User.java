package com.droidman.exhangeapp.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class User {
    @SerializedName("id")
    private String id;
    @SerializedName("fname")
    private String firstName;
    @SerializedName("lname")
    private String lastName;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("img")
    private String img = "http://www.abualzait.site/mobapps/exchangeApp/user.png";


    public User(LinkedTreeMap<String,String> userTreeMap) {
        id = String.valueOf(userTreeMap.get("id"));
        firstName = userTreeMap.get("fname");
        lastName = userTreeMap.get("lname");
        phone = userTreeMap.get("phone");
        email = userTreeMap.get("email");
        if(userTreeMap.get("img") != null && !userTreeMap.get("img").equals(""))
            img = userTreeMap.get("img");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
