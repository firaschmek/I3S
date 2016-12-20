package com.example.firas.i3s_android.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by firas on 15/10/2016.
 */

public class Rondes {
    List<Ronde> rondeList;

    public Rondes() {
    }

    public Rondes(List<Ronde> rondeList) {
        this.rondeList = rondeList;
    }

    public List<Ronde> getRondeList() {
        return rondeList;
    }

    public void setRondeList(List<Ronde> rondeList) {
        this.rondeList = rondeList;
    }
}
