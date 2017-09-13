package com.example.topway.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.content.ContentValues.TAG;

/**
 * Created by topway on 8/26/2017.
 */

public class RVweatherViewHolder extends RecyclerView.ViewHolder {
    ImageView placeHolder=null;
    TextView description=null;
    TextView dateTime=null;
    TextView celcius=null;
    TextView humidity=null;
    public RVweatherViewHolder(View itemView) {
        super(itemView);
        this.placeHolder=itemView.findViewById(R.id.RVimageView);
        this.description=itemView.findViewById(R.id.RVdescription);
        this.dateTime=itemView.findViewById(R.id.RVdate);
        this.celcius=itemView.findViewById(R.id.RVCelcius);
        this.humidity=itemView.findViewById(R.id.RVhumidity);
    }

        public void populate(Context context, Weather weather){
            String CurrentWeather=weather.getWeatherMain();
            if (CurrentWeather.equalsIgnoreCase("Clear")){
                Picasso.with(context).load(R.drawable.sunny).into(placeHolder);
            }
            else if (CurrentWeather.equalsIgnoreCase("Rain")){
                Picasso.with(context).load(R.drawable.heavyrainy).into(placeHolder);
            }
            else if (CurrentWeather.equalsIgnoreCase("Clouds")&&weather.getWeatherDescription().equalsIgnoreCase("scattered clouds")){
                Picasso.with(context).load(R.drawable.clouds).into(placeHolder);
            }
            else if (CurrentWeather.equalsIgnoreCase("Clouds")&&weather.getWeatherDescription().equalsIgnoreCase("overcast clouds")){
                Log.d(TAG, "populate: overcast cloud is calling");
                Picasso.with(context).load(R.drawable.clouds).into(placeHolder);
            }
            else if (CurrentWeather.equalsIgnoreCase("Clouds")&&weather.getWeatherDescription().equalsIgnoreCase("broken clouds")){
                Log.d(TAG, "populate: Broken cloud is calling");
                Picasso.with(context).load(R.drawable.scattered).into(placeHolder);
            }
            else if (CurrentWeather.equalsIgnoreCase("Clouds")&&weather.getWeatherDescription().equalsIgnoreCase("few clouds")){
                Log.d(TAG, "populate: Broken cloud is calling");
                Picasso.with(context).load(R.drawable.scattered).into(placeHolder);
            }

            if (CurrentWeather.equalsIgnoreCase("Rain")){
                description.setText(weather.getWeatherMain());
            }
            else {
                description.setText(weather.getWeatherDescription());
            }
            String cel=Double.toString(weather.getTemp());
            String celc=cel.replace(".0","");
            String c=celc.concat("℃");
            celcius.setText(c);
            String hum=Double.toString(weather.getHumidity());
            String hu=hum.replace(".0","");
            String h=hu.concat("% Humidity");
            humidity.setText(h);

            dateTime.setText(weather.getDataTime());
        }

    }


