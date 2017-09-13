package com.example.topway.weatherapp;

import android.util.Log;

/**
 * Created by topway on 8/21/2017.
 */

public class Weather {

    private static final String TAG = "";
    private double temp;
    private int  humidity;
    private double pressure;
    private double seaLevel;
    private String dataTime;
    private double cloudPer;
    private String weatherMain;
    private String weatherDescription;

    public Weather(double temp, double pressure,double seaLevel, String dataTime, double cloudPer,
                   String weatherMain, String weatherDescription,int humidity) {
        this.temp = temp;
        this.humidity=humidity;
        this.pressure = pressure;
        this.seaLevel = seaLevel;
        this.dataTime = dataTime;
        this.cloudPer = cloudPer;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
    }

    public int  getHumidity() {
        return humidity;
    }

    public String getDataTime() {
        return dataTime;
    }
    public double getCloudPer() {
        return cloudPer;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getTemp() {
        return temp;
    }


    public double getPressure() {
        return pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    @Override
    public String toString() {
        return
                "temp=" + temp +
                "\nhumidity=" + humidity +
                "\npressure=" + pressure +
                "\nseaLevel=" + seaLevel +
                "\ndataTime='" + dataTime +
                "\ncloudPer=" + cloudPer +
                "\nweatherMain='" + weatherMain +
                "\nweatherDescription='" + weatherDescription;
    }
}
