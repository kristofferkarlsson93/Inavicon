package com.karlssonkristoffer.inavicon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private MainListener mainListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainListener = new MainListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mainListener.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mainListener.onActivityResult(requestCode, resultCode, data);
    }

    //for debug
    public void updatePosText(double latittude, double longitude) {
        TextView text = (TextView) findViewById(R.id.location);
        text.setText(String.valueOf(latittude) + "\n " + String.valueOf(longitude));
    }
    //for debug
    public void updateGeofence(String name) {
        TextView text = (TextView) findViewById(R.id.geofence);
        text.setText("Entered: " + name);
        ImageView image = (ImageView) findViewById(R.id.pingvin);
        image.setImageResource(R.drawable.testimagetwo);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainListener.onDestroy();
    }
}


//https://www.youtube.com/watch?v=nwXILgQz-SU