package com.droidman.exhangeapp.model;

import com.google.gson.annotations.SerializedName;

public class Table {
    @SerializedName("Table")
    private Object[] table;

    public Object[] getTable() {
        return table;
    }

    public void setTable(Object[] table) {
        this.table = table;
    }
}
