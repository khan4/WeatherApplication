package com.example.topway.weatherapp;

import java.util.ArrayList;

/**
 * Created by topway on 8/29/2017.
 */

public class GetArrayList {
    private ArrayList<Weather> weatherArrayList;

    public GetArrayList(){
    }

    public GetArrayList(ArrayList<Weather> weatherArrayList) {
        this.weatherArrayList = weatherArrayList;
    }

    public ArrayList<Weather> getWeatherArrayList() {
        return weatherArrayList;
    }
}
