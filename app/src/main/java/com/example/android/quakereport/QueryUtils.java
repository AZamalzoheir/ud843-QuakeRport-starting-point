package com.example.android.quakereport;

import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by Amalzoheir on 11/20/2017.
 */

public final class QueryUtils {
    private QueryUtils() {
    }
    public static  ArrayList<Earthquake> extractEarthquakes(String earthquakeJSON) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject root=new JSONObject(earthquakeJSON);
            JSONArray featured=root.getJSONArray("features");
            for(int i=0;i<featured.length();i++) {
            JSONObject Earthquake=featured.getJSONObject(i);
                JSONObject proparites=Earthquake.getJSONObject("properties");
                double magniuted=proparites.getDouble("mag");
                String loaction=proparites.getString("place");
                Long time=proparites.getLong("time");
                String url=proparites.getString("url");
                earthquakes.add(new Earthquake(magniuted,loaction,time,url));
            }
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
             Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }
    private static URL createurl(String stringUrl) throws MalformedInputException {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("inquaryutil", "error when making url", e);
            return null;
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOError, IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        InputStream inputstream = null;
        HttpURLConnection urlconnection = null;
        try {
            urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");
            urlconnection.setReadTimeout(10000);
            urlconnection.setConnectTimeout(15000);
            urlconnection.connect();
            if (urlconnection.getResponseCode() == 200) {
                inputstream = urlconnection.getInputStream();
                jsonResponse = readFromStream(inputstream);
            }
        } catch (IOException e) {
            Log.e("inquaryutil", "error when request file from server", e);
        } finally {
            if (urlconnection != null) {
                urlconnection.disconnect();
            }
            if (inputstream != null) {
                inputstream.close();
            }

        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputstream) throws IOException {
        StringBuilder output = new StringBuilder();
        try {
            if (inputstream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputstream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            Log.e("inquaryutil", "error when make read from file", e);
        }
        return output.toString();
    }
    public static ArrayList<Earthquake> fetchEarthquakeData(String requestUrl) throws MalformedInputException {
        // Create URL object
        URL url =createurl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("query", "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        ArrayList<Earthquake> earthquakes =extractEarthquakes(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }
}
