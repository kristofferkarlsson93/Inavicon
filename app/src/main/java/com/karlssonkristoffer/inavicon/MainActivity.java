package com.karlssonkristoffer.inavicon;
import android.R.color;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import io.proximi.proximiiolibrary.ProximiioGeofence;
import android.graphics.drawable.TransitionDrawable;

public class MainActivity extends AppCompatActivity {
    private MainListener mainListener;
    private Chechpoint[] checkpoints ;
    private Path demoPath;
    private Button forceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkpoints = new Chechpoint[10];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainListener = new MainListener(this);
        demoPath = new Path();
        forceButton = (Button) findViewById(R.id.forceButton);
        forceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateIcon();
            }
        });

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
        /*TextView text = (TextView) findViewById(R.id.location);
        text.setText(String.valueOf(latittude) + "\n " + String.valueOf(longitude));*/
    }


    /**
     *  Prints out new geofence-name and shows next icon if user enters correct geofence.
     * @param activatedGeofence - A geofence from proximi
     */
    public void updateCheckpoint(ProximiioGeofence activatedGeofence) {
        /*TextView text = (TextView) findViewById(R.id.geofence);
        text.setText("Entered: " + activatedGeofence.getName());*/
        if(demoPath.hasNext()) {
            if(activatedGeofence.getName().equals(demoPath.getCurrent().getGeofenceName())) {
                animateIcon();
            }
        }else {
            //Code for finish message heare.
            ImageView icon = (ImageView) findViewById(R.id.currentIcon);
            TextView finishMessage = (TextView) findViewById(R.id.instruction);
            icon.setImageResource(color.transparent);
            finishMessage.setText("You have reached your destination");
            finishMessage.setTextSize(24);

        }
    }

    /**
     * Animates the background of the Icon to go from white to green and back. Then changes the
     * icon. Transition takes 1 secound, then it is still for 2 seconds before it fades out
     * and the icon changes.
     */
    public void animateIcon(){
        final TransitionDrawable transition = (TransitionDrawable)findViewById(R.id.bgSquare).getBackground();
        transition.startTransition(500);
        new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                transition.reverseTransition(100);
                ImageView icon = (ImageView) findViewById(R.id.currentIcon);
                icon.setImageResource(demoPath.getNext().getIcon());
                TextView instruction = (TextView) findViewById(R.id.instruction);
                if(demoPath.hasNextCheckPointInstructions()) {
                    instruction.setText(demoPath.getNextCheckPointInstructions());
                }else {
                    instruction.setText(" ");
                }
                demoPath.lookForNext();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainListener.onDestroy();
    }
}


//https://www.youtube.com/watch?v=nwXILgQz-SU