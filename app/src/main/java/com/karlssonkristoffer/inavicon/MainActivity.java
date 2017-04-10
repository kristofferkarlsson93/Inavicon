package com.karlssonkristoffer.inavicon;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.transition.Transition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes;

import io.proximi.proximiiolibrary.ProximiioGeofence;
import android.graphics.drawable.TransitionDrawable;

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


    //From Proximi. To get permissions from users phone
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mainListener.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //Catches the result from permission request.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mainListener.onActivityResult(requestCode, resultCode, data);
    }

    //for debug

    /**
     * Uppdates the possition text when user phone gets a new possition
     * @param latittude The new latitude to display
     * @param longitude The new longitude to display
     */
    public void updatePosText(double latittude, double longitude) {
        TextView text = (TextView) findViewById(R.id.location);
        text.setText(String.valueOf(latittude) + "\n " + String.valueOf(longitude));
    }


    /**
     *  Prints out new geofence-name and shows next icon if user enters correct geofence.
     * @param activatedGeofence - A geofence from proximi
     */
    public void updateCheckpoint(ProximiioGeofence activatedGeofence) {
        TextView text = (TextView) findViewById(R.id.geofence);
        text.setText("Entered: " + activatedGeofence.getName());

        if(activatedGeofence.getName().equals(demoPath.getCurrent().getGeofenceName())) {
            animate_bg();
            ImageView icon = (ImageView) findViewById(R.id.currentIcon);
            icon.setImageResource(demoPath.getNext().getIcon());
            if(demoPath.hasNextCheckPointInstructions()) {
                TextView instruction = (TextView) findViewById(R.id.instruction);
                instruction.setText(demoPath.getNextCheckPointInstructions());
            }
            demoPath.lookForNext();
        }

    }
        //http://stackoverflow.com/questions/5254100/how-to-set-an-imageviews-image-from-a-string
    public void animate_bg(){
        TransitionDrawable transition = (TransitionDrawable)findViewById(R.id.bgSquare).getBackground();
        transition.startTransition(5000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        transition.reverseTransition(1000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainListener.onDestroy();
    }
}


//https://www.youtube.com/watch?v=nwXILgQz-SU