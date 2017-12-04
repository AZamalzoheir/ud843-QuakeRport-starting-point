package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Amalzoheir on 11/20/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPERATOR="of";
    String originalLocation;
    String primaryLocation;
    String locationOffset;
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
        TextView magnitudeView=(TextView) listItemView.findViewById(R.id.magnitude);
        double mmagnitude=cureentEarthquake.getmMagnitude();
        magnitudeView.setText(formatMagnitude(mmagnitude));
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(cureentEarthquake.getmMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
        TextView primaryLocationView=(TextView) listItemView.findViewById(R.id.primarylocation);
        TextView LocationOffsetView=(TextView) listItemView.findViewById(R.id.loactionofset);
        originalLocation=cureentEarthquake.getmLocation();
        if(originalLocation.contains(LOCATION_SEPERATOR)){
            String[]parts=originalLocation.split(LOCATION_SEPERATOR);//split text into two text
            locationOffset=parts[0]+LOCATION_SEPERATOR;
            primaryLocation=parts[1];
        }
        else
        {
         locationOffset=getContext().getString(R.string.near_the);
         primaryLocation=originalLocation;
        }
        primaryLocationView.setText(primaryLocation);
        LocationOffsetView.setText(locationOffset);
        TextView date=(TextView) listItemView.findViewById(R.id.date);
        TextView time=(TextView) listItemView.findViewById(R.id.time);
        Date dateObject=new Date(cureentEarthquake.getDate());
        date.setText(formatDate(dateObject));
        time.setText(formatTime(dateObject));
        return listItemView;
    }
    //format magnitude to string
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormatter=new DecimalFormat("0.0");
        String mmagnitudeToDisplay=magnitudeFormatter.format(magnitude);
        return  mmagnitudeToDisplay;
    }
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormatter=new SimpleDateFormat("MMM DD,YYYY");
        String dateToDisplay=dateFormatter.format(dateObject);
        return  dateToDisplay;
    }
    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormatter=new SimpleDateFormat("h:mm a");
        String  timeToDisplay=timeFormatter.format(dateObject);
        return  timeToDisplay;
    }
    private int getMagnitudeColor(double magnitude){
        int mMagnitude=(int)magnitude;
        int magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude1);
        switch (mMagnitude){
            case 10:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude10plus);
                break;
            case 9:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude9);
                break;
            case 8:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude8);
                break;
            case 7:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude7);
                break;
            case 6:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude6);
                break;
            case 5:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude5);
                break;
            case 4:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude4);
                break;
            case 3:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude3);
                break;
            case 2:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude2);
                break;
            case 1:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude1);
                break;
            default:
                magnitudeColor= ContextCompat.getColor(getContext(),R.color.magnitude10plus);
                break;
        }
        return magnitudeColor;
    }
}
