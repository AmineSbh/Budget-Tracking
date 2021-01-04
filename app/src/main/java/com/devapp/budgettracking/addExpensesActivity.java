package com.devapp.budgettracking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.devapp.budgettracking.R;

public class addExpensesActivity extends AppCompatActivity {

    private SharedPreferences sharedPref = null;
    Location location;
    String name;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

    }

    public void add(View view) {

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


        // Get name.
        EditText eTname = findViewById(R.id.nom);
        EditText Tprix = findViewById(R.id.prix);

        name = eTname.getText().toString();
        long prix = Long.valueOf(Tprix.getText().toString());

        if(name.trim().equals("")) {
            eTname.setError( "Name is required" );
            eTname.setHint("Please enter an event name");
            return;
        }

        DataBase db = new DataBase(this);
        db.addRow(new Expenses(db.getCurrentId() + 1, name, location.getLatitude(),
                location.getLongitude(), prix));
    }

    public void menu(View view) {
        // Create an intent for the second activity
        Intent intent = new Intent(this, MainActivity.class);

        // Start the activity
        startActivity(intent);

        finish();
    }
}
