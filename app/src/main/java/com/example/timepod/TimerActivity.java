package com.example.timepod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

public class TimerActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private boolean running;
    private long pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        chronometer=findViewById(R.id.chro);

    }
    public void startChronometer(View v){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime()-pause);
            chronometer.start();
            running= true;

        }
    }

    public void pauseChronometer(View v){
        if(running){
            chronometer.stop();
            pause= SystemClock.elapsedRealtime()-chronometer.getBase();
            running=false;
        }
    }

    public void resetChronometer(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pause=0;
    }
}