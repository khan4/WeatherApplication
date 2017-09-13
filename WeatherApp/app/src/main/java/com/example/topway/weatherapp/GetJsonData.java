package com.example.topway.weatherapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static java.lang.Math.round;

/**
 * Created by topway on 8/22/2017.
 */

public class GetJsonData implements GetRawData.onDownloadComplete{
    private ArrayList<Weather> weatherArrayList;
    public String weather_main;
    public String weather_description;
    GetData getData;

    public GetJsonData(GetData getData) {
    GetRawData getRawData=new GetRawData(this);
    MainActivity getPlace=new MainActivity();
       Log.d(TAG, "We are getting the latitude and longitude"+getPlace.longitude+"wwwwwwwwwwwwwwwwww"+getPlace.latitude);
      getRawData.execute("http://api.openweathermap.org/data/2.5/forecast?lat=" +getPlace.latitude + "&lon=" +getPlace.longitude+
              "&id=524901&APPID=e9d2c7867e470d2659a2c5d2f0c107d6");
     this.getData=getData;
    }

    public interface GetData{
        void Data(ArrayList<Weather> arrayList);
    }

    public void onDownloadComplete(String data, DownloadStatus downloadStatus) {
        if (downloadStatus == DownloadStatus.OK) {
             weatherArrayList = new ArrayList<>();
        }

        try {
            JSONObject jsonData = new JSONObject(data);
            // JSONObject jsonMain=jsonData.getJSONObject("main");
            JSONArray jsonArray = jsonData.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonWeather = jsonArray.getJSONObject(i);
                String dateAndTime = jsonWeather.getString("dt_txt");
                JSONObject jasonCloud = jsonWeather.getJSONObject("clouds");
                double cloudniness = jasonCloud.getDouble("all");
                JSONObject jasonTemp = jsonWeather.getJSONObject("main");
                double temperature = jasonTemp.getDouble("temp");
                temperature = (temperature - 273.15);
                temperature=round(temperature);
                double pressure = jasonTemp.getDouble("pressure");
                int humidity = jasonTemp.getInt("humidity");
                double sea_L = jasonTemp.getDouble("sea_level");

                JSONArray jsonArray2 = jsonWeather.getJSONArray("weather");
                for (int j = 0; j < jsonArray2.length(); j++) {
                    JSONObject jsonMainWeather = jsonArray2.getJSONObject(j);
                    weather_main = jsonMainWeather.getString("main");
                    weather_description = jsonMainWeather.getString("description");
                }
                   Weather weather = new Weather(temperature, pressure, sea_L, dateAndTime,
                            cloudniness, weather_main, weather_description, humidity);
                    weatherArrayList.add(weather);
            }
            if (getData!=null){
                getData.Data(weatherArrayList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "onDownload Failed");
            downloadStatus = DownloadStatus.FAILED;
        }

    }

}




