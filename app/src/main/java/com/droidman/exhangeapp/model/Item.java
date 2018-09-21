package com.droidman.exhangeapp.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class Item {

    @SerializedName("id")
    private String id;
    @SerializedName("descr")
    private String desc;
    @SerializedName("img")
    private String img = "http://www.abualzait.site/mobapps/exchangeApp/item.png";
    @SerializedName("userID")
    private String userId;
    @SerializedName("fname")
    private String fname;
    @SerializedName("lname")
    private String lname;
    @SerializedName("uimg")
    private String uimg;
    @SerializedName("phone")
    private String phone;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("distance")
    private Double distance;

    public Item(LinkedTreeMap<String,Object> itemTreeMap) {
        id = (String)itemTreeMap.get("id");
        desc = (String)itemTreeMap.get("descr");
        userId = (String)itemTreeMap.get("userID");
        fname = (String)itemTreeMap.get("fname");
        lname = (String)itemTreeMap.get("lname");
        uimg = (String)itemTreeMap.get("uimg");
        phone = (String)itemTreeMap.get("phone");
        latitude = (String)itemTreeMap.get("latitude");
        longitude = (String)itemTreeMap.get("longitude");
        distance = (Double) itemTreeMap.get("distance");
        if(itemTreeMap.get("img") != null && !itemTreeMap.get("img").equals(""))
            img = (String)itemTreeMap.get("img");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
