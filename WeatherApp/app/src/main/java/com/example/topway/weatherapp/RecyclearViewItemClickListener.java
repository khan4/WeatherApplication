package com.example.topway.weatherapp;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by topway on 9/11/2017.
 */

public class RecyclearViewItemClickListener extends RecyclerView.SimpleOnItemTouchListener {
    interface OnRecyclearClickListener{
        void onItemLongClick(View view,int position);
    }
    private final OnRecyclearClickListener mListener;
    private final GestureDetectorCompat mGestureDetector;

    public RecyclearViewItemClickListener(Context context,final RecyclerView recyclerView,
                                          OnRecyclearClickListener listener){
        mListener=listener;
        mGestureDetector=new GestureDetectorCompat(context,
                new GestureDetector.SimpleOnGestureListener() {
                    
                    @Override
                    public void onLongPress(MotionEvent motionEvent) {

                    }

                });
    }
}
