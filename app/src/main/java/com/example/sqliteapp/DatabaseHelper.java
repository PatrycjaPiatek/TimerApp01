package com.example.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //TableName
    public static final String TABLE_NAME = "SESSIONS";

    //Table Columns:
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String TIME = "time";

    //Database Information
    static final String DB_NAME = "SESSION_BASE.DB";

    //Database Version:
    static final int DB_VERSION = 1;

    //Creating Table Query:
    private static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT +
            " TEXT NOT NULL, " + TIME + " TEXT);";

    //Constructor:
    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Executing the Query
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //DELETE TABLE
        onCreate(db);
    }
}
