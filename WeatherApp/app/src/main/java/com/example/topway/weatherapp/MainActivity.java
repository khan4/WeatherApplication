package com.example.topway.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.MalformedJsonException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

enum DownloadStatus{IDLE,PROCESSING,FAILED,NOT_INITILIZED,OK}
public class MainActivity extends AppCompatActivity implements GetJsonData.GetData{

    private static final String TAG = "";
    private int PLACE_PICKER_REQUEST=1;
    private String address;
    public static double latitude;
    public static double  longitude;
    private RVweatherAdapter rVweatherAdapter;
    private TextView placePickr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placePickr = findViewById(R.id.placePickerTextView);
        //Click listener Starts
        placePickr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent = builder.build(getApplicationContext());
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                    Log.d(TAG, "onClick: Ends");
                } catch (GooglePlayServicesRepairableException e) {
                    Log.d(TAG, "this is exception");
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.d(TAG, "onClick: this is exception");
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // GetRawData getRawData=new GetRawData();
        if (requestCode==PLACE_PICKER_REQUEST){
            if (resultCode==RESULT_OK){
                Log.d(TAG, "onActivityResult: Place pickr starts");
                Place place=PlacePicker.getPlace(data,this);

                address=String.format(""+place.getAddress());
               String  latlong=String.format(""+place.getLatLng());
               String  LATLON= latlong.replace("lat/lng: (","");
               String  latitudelongitude= LATLON.replace(")","");
               String[]  parts=latitudelongitude.split(",");
               String  p1Lat=parts[0];
               String p2Lon=parts[1];

                latitude=Double.parseDouble(p1Lat);
                longitude=Double.parseDouble(p2Lon);

                GetJsonData getJsonData=new GetJsonData(this);
                RecyclerView recyclerView=findViewById(R.id.MainActivityRecyclearView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                rVweatherAdapter=new RVweatherAdapter(this);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setAdapter(rVweatherAdapter);
                // getRawData.execute("http://api.openweathermap.org/data/2.5/forecast?lat=" + latitude + "&lon=" + longitude +
                //      "&id=524901&APPID=e9d2c7867e470d2659a2c5d2f0c107d6");
                // Log.d(TAG, "onActivityResuOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                //  Intent intent=CurrentWeather.getIntent(getBaseContext());
                // startActivity(intent);*/
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void Data(ArrayList<Weather> arrayList) {
        ArrayList<Weather> weatherArrayList=new ArrayList<>();
        weatherArrayList.add(arrayList.get(4));
        weatherArrayList.add(arrayList.get(12));
        weatherArrayList.add(arrayList.get(20));
        weatherArrayList.add(arrayList.get(28));
        weatherArrayList.add(arrayList.get(36));
        rVweatherAdapter.LoadNewData(weatherArrayList);

        Weather weather=arrayList.get(0);
        ImageView imageView=findViewById(R.id.CurrentplaceHolder);
        TextView adress=findViewById(R.id.address);
        TextView weaMain=findViewById(R.id.weather_main);
        TextView Celcius=findViewById(R.id.textView_Celcius);
        TextView humidity=findViewById(R.id.textView_Humidity);
        TextView dateTime=findViewById(R.id.textView_Date_Time);

        String CurrentWeather=weather.getWeatherMain();
        if (CurrentWeather.equalsIgnoreCase("Clear")){
            Picasso.with(this).load(R.drawable.sunny).into(imageView);
        }
        else if (CurrentWeather.equalsIgnoreCase("Rain")){
            Picasso.with(this).load(R.drawable.heavyrainy).into(imageView);
        }
        else if (CurrentWeather.equalsIgnoreCase("Clouds")&&weather.getWeatherDescription().equalsIgnoreCase("overcast clouds")){
            Picasso.with(this).load(R.drawable.clouds).into(imageView);
        }
        else if (CurrentWeather.equalsIgnoreCase("Clouds")&&weather.getWeatherDescription().equalsIgnoreCase("few clouds")){
            Picasso.with(this).load(R.drawable.scattered).into(imageView);
        }
        else if (CurrentWeather.equalsIgnoreCase("Clouds")&&weather.getWeatherDescription().equalsIgnoreCase("broken clouds")){
            Picasso.with(this).load(R.drawable.scattered).into(imageView);
        }
        else if (CurrentWeather.equalsIgnoreCase("Clouds")&&weather.getWeatherDescription().equalsIgnoreCase("scattered clouds")){
            Picasso.with(this).load(R.drawable.clouds).into(imageView);
        }

        if (CurrentWeather.equalsIgnoreCase("Rain")){
            weaMain.setText(weather.getWeatherMain());
        }
        else {
            weaMain.setText(weather.getWeatherMain());
        }


        adress.setText(address);
        String cel=Double.toString(weather.getTemp());
        String celcius=cel.replace(".0","");
        String c=celcius.concat("℃");
        Celcius.setText(c);
        String hum=Double.toString(weather.getHumidity());
        String humi=hum.replace(".0","");
        String h=humi.concat("% Humidity");
        humidity.setText(h);
        dateTime.setText(weather.getDataTime());
    }

}









