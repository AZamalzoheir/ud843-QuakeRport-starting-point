package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amalzoheir on 11/20/2017.
 */

public class Earthquake {
        private double mMagnitude;
        private String mLocation;
        private Long mDate;
        private  String url;
        public Earthquake(double mMagnitude,String mLocation,Long mDate,String url){
            this.mMagnitude=mMagnitude;
            this.mLocation=mLocation;
            this.mDate=mDate;
            this.url=url;
        }
        public double getmMagnitude() {
            return mMagnitude;
        }
        public String getmLocation() {
            return mLocation;
        }
        public Long getDate() {
            return mDate;
        }
        public String grtUrl(){
            return url;
        }
}
