package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class TimeActivity extends AppCompatActivity {

    private Button btnend;
    ImageView icanchor, yellowCat;
    Animation roundingalone, scalecat;

    /////////////////////////////////////
    private Button btnstart; //BTNSTARTSTOP
    private Button btnreset; //BTNRESET
    Chronometer timerHere;
    private boolean mTimerRunning = false;
    public static String timeForDatabase = "";
    long timeWhenStopped = 0;
    /////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        //finding objects
        btnend = findViewById(R.id.btn_end);
        btnstart = findViewById(R.id.btn_start_time); //BTNSTARTSTOP
        btnreset = findViewById(R.id.btn_reset_time);
        timerHere = findViewById(R.id.timerHere);

        //finding images
        icanchor = findViewById(R.id.icanchor);
        yellowCat = findViewById(R.id.bgcircle);

        btnend.setVisibility(View.INVISIBLE);
        btnreset.setVisibility(View.INVISIBLE);

        //btnstart //BTNSTARTSTOP
        btnstart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(mTimerRunning){
                    pauseTimer();
                                   }
                else{
                    startTimer();
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            resetTimer();
            }
        });
    }

    private void startTimer(){

        //load animations IMAGES
        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);
        scalecat = AnimationUtils.loadAnimation(this, R.anim.scalecat);
        //passing animation IMAGES
        icanchor.startAnimation(roundingalone);
        yellowCat.startAnimation(scalecat);
        //btns changing //VISIBLE
        btnend.setVisibility(View.VISIBLE);
        btnreset.setVisibility(View.VISIBLE);
        btnend.animate().translationY(-480).setDuration(300).start();

        btnstart.setText(R.string.pause_time); //INVISIBLE //CHANGE TEXT TO PAUSE

        timerHere.setBase(SystemClock.elapsedRealtime() + timeWhenStopped); //Returns milliseconds since boot, including time spent in sleep.
        //start time
        timerHere.start();

        mTimerRunning = true;
    }
    private void pauseTimer(){

        timeWhenStopped = timerHere.getBase() - SystemClock.elapsedRealtime();
        timerHere.stop();

        //CLEARS ANIMS
        icanchor.clearAnimation();
        yellowCat.clearAnimation();

        //change button text
        btnstart.setText("start");

        mTimerRunning = false;
    }
    private void resetTimer(){
        timerHere.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
    }












    /////////////////////////////////////

    public void onClickEnd(View view) {
        if (view.getId() == R.id.btn_end) {

            ///////////////////////
            //for time
            timeForDatabase = timerHere.getText().toString();

            ///////////////////////

            Intent start = new Intent(this,
                    AddRecordActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(start);
        }
    }


























//    public void onClickStop(View view) {
//        if (view.getId() == R.id.btn_stop_time) {
//            timerHere.stop();
//            //get delta time from chronometer
//            //timeHelp = timerHere.getDrawingTime();
//            timeHelp = timerHere.getBase()-SystemClock.elapsedRealtime();
//            icanchor.clearAnimation();
//            yellowCat.clearAnimation();
//        }
//    }
//    public void onClickContinue(View view) {
//        if (view.getId() == R.id.btn_continue_time) {
//          timerHere.setBase(timeHelp+SystemClock.elapsedRealtime());
//          timerHere.start();
//            //passing animation
//            roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);
//            scalecat = AnimationUtils.loadAnimation(this, R.anim.scalecat);
//            icanchor.startAnimation(roundingalone);
//            yellowCat.startAnimation(scalecat);
//        }
//    }
}