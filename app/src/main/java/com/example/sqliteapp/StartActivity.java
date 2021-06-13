package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    private Button timer, list;
    private ImageView cat;
    Animation atg, btgone, btgtwo, movement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        timer = findViewById(R.id.btn_timer);
        list = findViewById(R.id.btn_list);
        cat = findViewById(R.id.ivSplash);

        //load animation
        //atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        btgone = AnimationUtils.loadAnimation(this, R.anim.btgone);
        btgtwo = AnimationUtils.loadAnimation(this, R.anim.btgtwo);
        movement = AnimationUtils.loadAnimation(this, R.anim.movement);
        //passing animation
        //cat.startAnimation(atg);
        timer.startAnimation(btgone);
        list.startAnimation(btgtwo);
        cat.startAnimation(movement);

//        for ( int i = 0; i<60; i++){
//            TranslateAnimation anim = new TranslateAnimation(0f,(float)(Math.random()*100),0f,(float)(Math.random()*100));
//
//            anim.setDuration(1500);
//            anim.setRepeatCount(1);
//            cat.startAnimation(anim);
//        }
    }

    public void onClickTimer(View view) {
        if (view.getId() == R.id.btn_timer) {

            Intent start = new Intent(this,
                    TimeActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(start);
        }
    }

    public void onClickList(View view) {
        if (view.getId() == R.id.btn_list) {

            Intent start = new Intent(this,
                    NotesListActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(start);
        }
    }
}