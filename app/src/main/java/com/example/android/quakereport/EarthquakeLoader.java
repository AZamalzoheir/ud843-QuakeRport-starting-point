package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
/**
 * Created by Amalzoheir on 12/4/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {
    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String murl;
    public EarthquakeLoader(Context context,String murl){
        super(context);
        this.murl=murl;
    }

    @Override
    protected void onForceLoad() {
        onForceLoad();
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        if (murl == null) {
            return null;
        }
        ArrayList<Earthquake> earthquakes= null;
        try {
            earthquakes = QueryUtils.fetchEarthquakeData(murl);
        } catch (MalformedInputException e) {
            e.printStackTrace();
        }
        return earthquakes;
    }

}
