package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Amalzoheir on 11/20/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public  EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake) {
        super(context, 0, earthquake);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthqueake_lisi_tem, parent, false);
        }
        Earthquake cureentEarthquake=getItem(position);
        TextView magnitude=(TextView) listItemView.findViewById(R.id.magnitude);
        magnitude.setText(cureentEarthquake.getmMagnitude());
        TextView locatio=(TextView) listItemView.findViewById(R.id.loaction);
        locatio.setText(cureentEarthquake.getmLocation());
        TextView date=(TextView) listItemView.findViewById(R.id.date);
        date.setText(cureentEarthquake.getmDate());
        return listItemView;
    }
}
