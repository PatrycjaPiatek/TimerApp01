package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class NotesListActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DatabaseHelper._ID,
       DatabaseHelper.SUBJECT, DatabaseHelper.TIME};

    final int[] to = new int[] {R.id.id, R.id.title, R.id.time};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        //view
        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record,
                cursor, from, to, 0);

        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        //onClickListener for List Items:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewid) {
                TextView idTextView = view.findViewById(R.id.id);
                TextView titleTextView = view.findViewById(R.id.title);
                TextView timeTextView = view.findViewById(R.id.time);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String time = timeTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(),
                        ModifySessionActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("time", time);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
//plus
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        /*if(id == R.id.add_record){
//            Intent add_men = new Intent(this, AddNoteActivity.class);
//            startActivity(add_men);
//        }*/
//        return super.onOptionsItemSelected(item);
//    }
}