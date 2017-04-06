package com.karlssonkristoffer.inavicon;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.jar.Attributes;

import io.proximi.proximiiolibrary.ProximiioGeofence;

public class MainActivity extends AppCompatActivity {
    private MainListener mainListener;
    private Chechpoint[] checkpoints ;
    private Path demoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkpoints = new Chechpoint[10];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainListener = new MainListener(this);
        demoPath = new Path();
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
    public void updateCheckpoint(ProximiioGeofence activatedGeofence) {
        TextView text = (TextView) findViewById(R.id.geofence);
        text.setText("Entered: " + activatedGeofence.getName());
        ImageView icon = (ImageView) findViewById(R.id.pingvin);

        if(activatedGeofence.getName().equals(demoPath.getCurrent().getGeofenceName())) {
            icon.setImageResource(demoPath.getNext().getIcon());
            demoPath.lookForNext();
        }


       /* if(!demoPath.currentIsPassed()) {

        }*/

        //http://stackoverflow.com/questions/5254100/how-to-set-an-imageviews-image-from-a-string
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainListener.onDestroy();
    }
}


//https://www.youtube.com/watch?v=nwXILgQz-SU