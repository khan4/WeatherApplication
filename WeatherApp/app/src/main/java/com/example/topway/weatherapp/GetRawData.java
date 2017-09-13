package com.example.topway.weatherapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by topway on 8/21/2017.
 */

class GetRawData extends AsyncTask<String,Void,String> {

    public GetRawData(onDownloadComplete mCallBack){
        this.mCallBack=mCallBack;
    }
    private DownloadStatus downloadStatus;
    private onDownloadComplete mCallBack;

    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: start of doing background");
        HttpURLConnection connection=null;
        BufferedReader reader=null;

        if (strings==null){
            downloadStatus=DownloadStatus.NOT_INITILIZED;
            return null;
        }

        try{
            URL url=new URL(strings[0]);
            downloadStatus=DownloadStatus.PROCESSING;
            connection=(HttpURLConnection)url.openConnection();
            //connection.setRequestMethod();
            connection.connect();
            int response=connection.getResponseCode();
            Log.d(TAG, "doInBackground: The response code was"+response);
            StringBuilder result=new StringBuilder();
            reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

            for (String line=reader.readLine();line!=null;line=reader.readLine()){
                result.append(line);
                downloadStatus=DownloadStatus.OK;
            }
            return result.toString();

        }catch (MalformedURLException e){
            Log.d(TAG, "doInBackground: MAlformed exception"+e.getMessage());
            downloadStatus=DownloadStatus.FAILED;
        }
        catch (IOException e){
            Log.d(TAG, "doInBackground: This is I/o exception"+e.getMessage());
            downloadStatus=DownloadStatus.FAILED;
        }
        catch (SecurityException e){
            Log.d(TAG, "doInBackground: this is security exception//Need Permission"+e.getMessage());
            Log.d(TAG, "finally download status");
            downloadStatus=DownloadStatus.FAILED;
        }


        finally {
            if (connection!=null){
                connection.disconnect();
            }
            if (reader!=null){
                try{
                    reader.close();
                }catch (IOException e){
                    Log.d(TAG, "doInBackground: Io exception"+e.getMessage());
                }
            }
        }
        return null;
    }

    public interface onDownloadComplete{
        void onDownloadComplete(String data,DownloadStatus status);
    }
    /////////On post Execute
    @Override
    protected void onPostExecute(String s) {
        if (mCallBack!=null){
                mCallBack.onDownloadComplete(s,DownloadStatus.OK);
        }
        super.onPostExecute(s);

    }



}
