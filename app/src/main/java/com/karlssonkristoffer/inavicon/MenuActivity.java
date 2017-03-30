package com.karlssonkristoffer.inavicon;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;

import android.graphics.Color;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    RelativeLayout menuLayout;
    Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menuLayout = new RelativeLayout(this);
        menuLayout.setBackgroundColor(Color.BLUE);
        startButton = new Button(this);
        startButton.setBackgroundColor(Color.GREEN);
        startButton.setText("START!");
        RelativeLayout.LayoutParams startButtonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        startButtonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        startButtonDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        startButtonDetails.width = 700;
        menuLayout.addView(startButton, startButtonDetails);
        setContentView(menuLayout);


    }
}
