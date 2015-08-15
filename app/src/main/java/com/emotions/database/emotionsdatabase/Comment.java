package com.emotions.database.emotionsdatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Abhinav Tripathi on 14-Aug-15.
 */
public class Comment  extends AppCompatActivity {
    EditText etComment;
    Button btnSubmit;
    Timer gpsTimer;
    Location lastLocation = null;
    long lastprovidertimestamp = 0;
    boolean gps_recorder_running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);

        etComment = (EditText) findViewById(R.id.etComment);
        etComment.setText("");
        Utilities.setComment("");
        etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Utilities.setComment(etComment.getText().toString());
            }
        });

        btnSubmit = (Button) findViewById(R.id.btnSubmitResponse);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utilities.isNetworkAvailable(Comment.this))
                {
                    btnSubmit.setEnabled(false);
                    ParseObject obj = new ParseObject("EmotionsDatabase");
                    obj.put("Happiness", Utilities.getHappiness());
                    obj.put("Sleepiness", Utilities.getSleepiness());
                    int n = Utilities.getNumEngagements();
                    String str = Integer.toString(n);
                    if(n>3)
                        str = "Many";
                    obj.put("Engagements", str);
                    obj.put("Comment", Utilities.getComment());

                    double lat = 0, lon = 0;
                    if(lastLocation != null)
                    {
                        lat = lastLocation.getLatitude();
                        lon = lastLocation.getLongitude();
                    }
                    obj.put("Latitude", lat);
                    obj.put("Longtitude", lon);

                    Toast.makeText(Comment.this, "Saving data... Pls wait!", Toast.LENGTH_SHORT).show();
                    obj.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e!=null)
                            {
                                Toast.makeText(Comment.this, "Save UnSuccessFull!", Toast.LENGTH_SHORT).show();
                                btnSubmit.setEnabled(true);
                                return;
                            }
                            Toast.makeText(Comment.this, "Save SuccessFull!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Comment.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        }
                    });
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Comment.this);
                    builder.setMessage("Internet connection could not be established!!")
                            .setTitle("Network error!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                lastLocation = getBestLocation();
                Toast.makeText(Comment.this,"Location updated",Toast.LENGTH_SHORT).show();
            }
        }).start();*/

        gpsTimer = new Timer();
        startRecording();
    }

    private static final String TAG = "Comment";
    /**
     * try to get the 'best' location selected from all providers
     */
    private Location getBestLocation() {
        Location gpslocation = getLocationByProvider(LocationManager.GPS_PROVIDER);
        Location networkLocation =
                getLocationByProvider(LocationManager.NETWORK_PROVIDER);

        // if we have only one location available, the choice is easy
        if (gpslocation == null) {
            Log.d(TAG, "No GPS Location available.");
            return networkLocation;
        }
        if (networkLocation == null) {
            Log.d(TAG, "No Network Location available");
            return gpslocation;
        }
        // a locationupdate is considered 'old' if its older than the configured
        // update interval. this means, we didn't get a
        // update from this provider since the last check
        long old = System.currentTimeMillis() - lastprovidertimestamp;
        boolean gpsIsOld = (gpslocation.getTime() < old);
        boolean networkIsOld = (networkLocation.getTime() < old);
        // gps is current and available, gps is better than network
        if (!gpsIsOld) {
            Log.d(TAG, "Returning current GPS Location");
            return gpslocation;
        }
        // gps is old, we can't trust it. use network location
        if (!networkIsOld) {
            Log.d(TAG, "GPS is old, Network is current, returning network");
            return networkLocation;
        }
        // both are old return the newer of those two
        if (gpslocation.getTime() > networkLocation.getTime()) {
            Log.d(TAG, "Both are old, returning gps(newer)");
            return gpslocation;
        } else {
            Log.d(TAG, "Both are old, returning network(newer)");
            return networkLocation;
        }
    }

    /**
     * get the last known location from a specific provider (network/gps)
     */
    private Location getLocationByProvider(String provider) {
        Location location = null;/*
        if (!isProviderSupported(provider)) {
            return null;
        }*/
        LocationManager locationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);
        try {
            if (locationManager.isProviderEnabled(provider)) {
                location = locationManager.getLastKnownLocation(provider);
            }
        } catch (IllegalArgumentException e) {
            Log.d(TAG, "Cannot acces Provider " + provider);
        }
        return location;
    }

    public void startRecording() {
        gpsTimer.cancel();
        gpsTimer = new Timer();
        long checkInterval = 500000;
        long minDistance = 0;
        // receive updates
        LocationManager locationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);
        for (String s : locationManager.getAllProviders()) {
            locationManager.requestLocationUpdates(s, checkInterval,
                    minDistance, new LocationListener() {

                        @Override
                        public void onStatusChanged(String provider,
                                                    int status, Bundle extras) {}

                        @Override
                        public void onProviderEnabled(String provider) {}

                        @Override
                        public void onProviderDisabled(String provider) {}

                        @Override
                        public void onLocationChanged(Location location) {
                            // if this is a gps location, we can use it
                            if (location.getProvider().equals(
                                    LocationManager.GPS_PROVIDER)) {
                                doLocationUpdate(location, true);
                            }
                        }
                    });
            // //Toast.makeText(this, "GPS Service STARTED",
            // Toast.LENGTH_LONG).show();
            gps_recorder_running = true;
        }
        // start the gps receiver thread
        gpsTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Location location = getBestLocation();
                doLocationUpdate(location, false);
            }
        }, 0, checkInterval);
    }

    public void doLocationUpdate(Location l, boolean force) {
        long minDistance = 1;
        Log.d(TAG, "update received:" + l);
        if (l == null) {
            Log.d(TAG, "Empty location");
            if (force)
                Toast.makeText(this, "Current location not available",
                        Toast.LENGTH_SHORT).show();
            return;
        }
        if (lastLocation != null) {
            float distance = l.distanceTo(lastLocation);
            Log.d(TAG, "Distance to last: " + distance);
            if (l.distanceTo(lastLocation) < minDistance && !force) {
                Log.d(TAG, "Position didn't change");
                return;
            }
            if (l.getAccuracy() >= lastLocation.getAccuracy()
                    && l.distanceTo(lastLocation) < l.getAccuracy() && !force) {
                Log.d(TAG,
                        "Accuracy got worse and we are still "
                                + "within the accuracy range.. Not updating");
                return;
            }
            if (l.getTime() <= lastprovidertimestamp && !force) {
                Log.d(TAG, "Timestamp not newer than last");
                return;
            }
        }
        lastprovidertimestamp = l.getTime();
        lastLocation = l;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gpsTimer.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        gpsTimer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gpsTimer.cancel();
    }
}
