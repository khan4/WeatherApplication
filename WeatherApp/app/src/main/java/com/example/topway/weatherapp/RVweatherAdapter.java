package com.example.topway.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by topway on 8/26/2017.
 */

public class RVweatherAdapter extends RecyclerView.Adapter<RVweatherViewHolder> {
    private LayoutInflater inflater;
    private List<Weather> weatherArrayList;
    private Context context;
    int flag=0;

    public RVweatherAdapter(MainActivity context) {
        this.context = context;
        inflater=context.getLayoutInflater();
        this.weatherArrayList=new ArrayList<>();
    }

    @Override
    public RVweatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.place_activity, parent, false);
            return new RVweatherViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RVweatherViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: pehle on bind view Holder");
            holder.populate(context, weatherArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        if (weatherArrayList!=null) {
            return weatherArrayList.size();
        }return 0;
    }

    public void LoadNewData(List<Weather> weathers){
        weatherArrayList=weathers;
        notifyDataSetChanged();
    }
}