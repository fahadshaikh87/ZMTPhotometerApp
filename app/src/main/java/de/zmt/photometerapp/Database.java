package de.zmt.photometerapp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by FAHAD SHAIKH on 5/31/2017.
 */

public class Database extends AppCompatActivity {


    ListView listview;
    ArrayAdapter<String> adapter;
    String[] android_versions = {"Sample Name", "Location", "Time", "Parameter", "Concentration"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);








        listview = (ListView) findViewById(R.id.database_id);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android_versions);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_LONG).show();


            }
        });
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