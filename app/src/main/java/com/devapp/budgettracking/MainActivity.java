package com.devapp.budgettracking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.devapp.budgettracking.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref = null;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBase(this);

        /*ListView listView = findViewById(R.id.listview);

        List<Expenses> listEx = new ArrayList<>();
        listEx.add(new Expenses(1,"tele",0.0,0.0,100));
        listView.setAdapter((ListAdapter) listEx);*/
    }

    public void setting(View view) {
        // Create an intent for the second activity
        Intent intent = new Intent(this, settingsActivity.class);

        // Start the activity
        startActivity(intent);

        finish();
    }

    public void addExpenses(View view) {
        // Create an intent for the second activity
        Intent intent = new Intent(this, addExpensesActivity.class);

        // Start the activity
        startActivity(intent);

        finish();
    }

    private void printEvents() {

        List<Expenses> events = db.getAllRows();
        for (Expenses e : events) {
            String log = "ID: " + e.getID() + ", Name: " + e.getName() + ", Location: (" +
                    e.getLongitude() + ", " + e.getLatitude() + "), Type: " + e.getPrix();
        }

    }

    private void listEvents() {

        // Get list from database
        final List<Expenses> events = db.getAllRows();

        // Copy to new list
        List<String> list = new ArrayList<>();
        for (Expenses e : events) {
            list.add(e.getName());
        }

        // Create ListView
        ListView listView = findViewById(R.id.listview);

    }
}

