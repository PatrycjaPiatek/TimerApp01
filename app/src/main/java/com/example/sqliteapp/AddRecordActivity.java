package com.example.sqliteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddRecordActivity extends Activity implements View.OnClickListener {

    private EditText subjectEditText; //session subject
    private TextView timeTextView; //time
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setTitle("Add Record"); //
        setContentView(R.layout.activity_add_record);

        Button addTodoBtn = findViewById(R.id.add_record); //?

        //Instantination of widgets:
        subjectEditText = findViewById(R.id.subject_edit_text);
        timeTextView = findViewById(R.id.time_text_view);

        //get time from chronometer, set time to string and set time to textView
        String sessionTime = TimeActivity.timeForDatabase; //string for time
        timeTextView.setText(sessionTime);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_record) {
            final String name = subjectEditText.getText().toString(); //get the value from widged
            final String time = timeTextView.getText().toString(); //get the value from widged

            dbManager.insert(name, time); //save record to database

            Intent main = new Intent(this,
                    StartActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //If set, and the activity being launched is already running in the current task, then instead of launching a new instance of that activity, all of the other activities on top of it will be closed and this Intent will be delivered to the (now on top) old activity as a new Intent.
            startActivity(main);
        }
    }
}