package com.devapp.budgettracking;

import android.app.usage.UsageEvents;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "eventManager";

    // Table name
    private static final String TABLE_EVENTS = "event";

    // Table columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LAT = "latitude";
    private static final String KEY_LNG = "longitude";
    private static final String KEY_PRIX= "type";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_LAT + " TEXT," + KEY_LNG + " TEXT," + KEY_PRIX + " TEXT" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);

    }

    // Upgrade the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

        // Create tables again
        onCreate(db);
    }

    // Add a new row
    void addRow(Expenses e) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, e.getName());
        values.put(KEY_LAT, Double.toString(e.getLatitude()));
        values.put(KEY_LNG, Double.toString(e.getLongitude()));
        values.put(KEY_PRIX, e.getPrix());

        // Insert row
        db.insert(TABLE_EVENTS, null, values);
        db.close();

    }

    // Get all rows
    public List<Expenses> getAllRows() {
        List<Expenses> l = new ArrayList<>();

        // Select all query
        String selectQuery = "SELECT * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all the rows and addi the to the list
        if (cursor.moveToFirst()) {
            do {
                Expenses e = new Expenses();
                e.setID(Integer.parseInt(cursor.getString(0)));
                e.setName(cursor.getString(1));
                e.setLatitude(cursor.getDouble(2));
                e.setLongitude(cursor.getDouble(3));
                e.setPrix(cursor.getFloat(4));

                // Add row to list
                l.add(e);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Return the list
        return l;
    }

    // Clear the table
    public void clear() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EVENTS);
        db.close();
    }

    public int getCurrentId() {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT MAX(" + KEY_ID + ") FROM " + TABLE_EVENTS;
        Cursor c = db.rawQuery(q, null);

        if (c.moveToFirst()) {
            int id = c.getInt(0);
            c.close();
            db.close();
            return id;
        } else {
            return 0;
        }
    }

}