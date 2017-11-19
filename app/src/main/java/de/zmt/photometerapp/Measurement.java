package de.zmt.photometerapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by FAHAD SHAIKH on 5/12/2017.
 */

public class Measurement extends AppCompatActivity {

    ListView listview;
    ArrayAdapter<String> adapter;
    String[] android_versions = {"Stored Calibration", "New Calibration",};

    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measurement_layout);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);







        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listview = (ListView) findViewById(R.id.measurement_id);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android_versions);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(getBaseContext(), "Mount LED at 880nm", Toast.LENGTH_LONG).show();
                        Intent newcalibration = new Intent(Measurement.this, StoredCalibration.class);
                        startActivity(newcalibration);
                        break;

                    case 1:
                        Toast.makeText(getBaseContext(), "Mount LED at 880nm", Toast.LENGTH_LONG).show();
                        Intent storedcalibration = new Intent(Measurement.this, Calibration.class);
                        startActivity(storedcalibration);
                        break;

                }
            }

        });
    }


    public void addContact(View view)
    {
        Intent intent=new Intent(this,NewContactActivity.class);
        startActivity(intent);
    }

    public void viewContact(View view)
    {
        Intent intent=new Intent(this,DataListActivity.class);
        startActivity(intent);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}

