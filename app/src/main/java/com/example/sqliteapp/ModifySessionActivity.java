package com.example.sqliteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModifySessionActivity extends Activity implements View.OnClickListener {

    //Widgets:
    private EditText titleText;
    private TextView timeText;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modify_session);

        dbManager = new DBManager(this);
        dbManager.open();

        titleText = findViewById(R.id.subject_edittext);
        timeText = findViewById(R.id.time_textview); // not modify time

        Button updateBtn = findViewById(R.id.btn_update);
        Button deleteBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String time = intent.getStringExtra("time");

        _id = Long.parseLong(id);

        titleText.setText(title);
        timeText.setText(time);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:

                String title = titleText.getText().toString();
                String time = timeText.getText().toString();
                dbManager.update(_id, title, time);
                this.returnHome();
                break;
            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),
                NotesListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}