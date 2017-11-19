package de.zmt.photometerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by FAHAD SHAIKH on 7/4/2017.
 */

public class Calibration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calibration);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void addSample(View view)
    {
        Intent intent=new Intent(this,NewContactActivity.class);
//        startActivity(intent);
    }
public void ViewSample(View view)
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