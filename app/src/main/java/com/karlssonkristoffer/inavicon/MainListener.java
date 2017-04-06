package com.karlssonkristoffer.inavicon;

/**
 * Created by Kristoffer on 2017-04-05.
 */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import io.proximi.proximiiolibrary.ProximiioAPI;
import io.proximi.proximiiolibrary.ProximiioGeofence;
import io.proximi.proximiiolibrary.ProximiioListener;


public class MainListener extends ProximiioListener {
    private ProximiioAPI proximiioAPI;
    @Nullable private MainActivity main;
    private static final String ID = "MainActivityListener";
    private static final String AUTH_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImlzcyI6IjBkMTMyNGRmLTM0ZjctNDhkNS1hNzQ4LTEwOGU0ZmExMjkxNiIsInR5cGUiOiJhcHBsaWNhdGlvbiIsImFwcGxpY2F0aW9uX2lkIjoiOGZiZjE5YzUtZTAyMi00N2ExLWE2MDYtNGUzZjdhN2UzMDA3In0.6d6mfN4r0bBssavMbOxHlOzLKBnimWS0laCn7xNFgpw"; // TODO: Replace with your own!

    public MainListener(@NonNull MainActivity main) {
        this.main = main;
        proximiioAPI = new ProximiioAPI(ID, main);
        proximiioAPI.setActivity(main);
        proximiioAPI.setListener(this);
        proximiioAPI.setAuth(AUTH_KEY);
        Log.d(ID, "Constructor");
    }

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        proximiioAPI.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        proximiioAPI.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void position(double lat, double lon, double accuracy) {
        // Do something with the positioning system.
        // See ProximiioListener for all methods that can be overridden.
        if (main != null) {
            //main.someAction();
            main.updatePosText(lat, lon);
        }
        Log.d(ID, "Position update! (" + lat + ", " + lon + ")");
    }

    @Override
    public void geofenceEnter(ProximiioGeofence geofence) {
        Log.d(ID, "Entered geofence: " + geofence.getName());
        main.updateCheckpoint(geofence);
    }

    void onDestroy() {
        // Set to null to avoid memory leaks.
        main = null;
        // Destroy this API object.
        // Parameter true indicates that we'd like to keep our listener working on the background.
        proximiioAPI.destroy(true);
    }

}

