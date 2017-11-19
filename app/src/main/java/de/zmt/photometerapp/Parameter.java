package de.zmt.photometerapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by FAHAD SHAIKH on 5/12/2017.
 */

public class Parameter extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "de.zmt.photometerapp.Parameter";

    ListView listview;
    ArrayAdapter<String> adapter;
    String[] android_versions = {"Custom \n ", "Phosphate\n ppm", "Nitrate\n ppm(as NO3)",
            "Nitrite\n ppm(as NO2)", "Silica\n ppm(as SiO2)", "Total Alkalinity \n TA", "Nitrate+Nitrite \n ppm(as NOx)", "pH\n ppm"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameter_layout);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listview = (ListView) findViewById(R.id.parameter_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android_versions);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(getBaseContext(), "Mount LED at 880nm", Toast.LENGTH_LONG).show();
                        Intent newint = getIntent();

                        String address = newint.getStringExtra("device_address"); //receive the address of the bluetooth device
                        Intent customActivity =  new Intent(getApplicationContext(),BlankActivity.class);
                        customActivity.putExtra("device_address", address);
                        startActivity(customActivity);
                        break;

                        case 1:
                            Toast.makeText(getBaseContext(), "Mount LED at 570nm", Toast.LENGTH_LONG).show();
                            Intent secondint = getIntent();
                            String secondaddress = secondint.getStringExtra("device_address");
                            Intent phosphateActivity = new Intent(getApplicationContext(),BlankActivity.class);
                            phosphateActivity.putExtra("device_address",secondaddress);
                            startActivity(phosphateActivity);
                            break;
                    case 2:
                        Toast.makeText(getBaseContext(), "Mount LED at 540nm", Toast.LENGTH_LONG).show();
                        Intent thirdint = getIntent();
                        String thirdaddress = thirdint.getStringExtra("device_address");
                        Intent nitrateActivity = new Intent(getApplicationContext(),BlankActivity.class);
                      nitrateActivity.putExtra("device_address",thirdaddress);


                        startActivity(nitrateActivity);
                        break;
                    case 3:
                    Toast.makeText(getBaseContext(), "Mount LED at 540nm", Toast.LENGTH_LONG).show();
                        Intent forthint = getIntent();
                        String forthaddress = forthint.getStringExtra("device_address");
                        Intent nitriteActivity = new Intent(getApplicationContext(),BlankActivity.class);
                        nitriteActivity.putExtra("device_address",forthaddress);


                    startActivity(nitriteActivity);
                    break;
                    case 4:
                        Toast.makeText(getBaseContext(), "Mount LED at 540nm", Toast.LENGTH_LONG).show();
                        Intent fifthint = getIntent();
                        String fifthaddress = fifthint.getStringExtra("device_address");
                        Intent SilicaActivity = new Intent(getApplicationContext(),BlankActivity.class);
                        SilicaActivity.putExtra("device_address",fifthaddress);


                        startActivity(SilicaActivity);
                        break;
                    case 5:
                        Toast.makeText(getBaseContext(), "Mount LED at 590nm", Toast.LENGTH_LONG).show();
                        Intent sixthint = getIntent();
                        String sixthaddress = sixthint.getStringExtra("device_address");
                        Intent totalAlkalinityActivity = new Intent(getApplicationContext(),BlankActivity.class);
                        totalAlkalinityActivity.putExtra("device_address",sixthaddress);
                        startActivity(totalAlkalinityActivity);
                        break;
                    case 6:
                        Toast.makeText(getBaseContext(), "Mount LED at 540nm", Toast.LENGTH_LONG).show();

                        Intent sevenint = getIntent();
                        String sevenaddress = sevenint.getStringExtra("device_address");
                        Intent nitratenitriteActivity = new Intent(getApplicationContext(),BlankActivity.class);
                        nitratenitriteActivity.putExtra("device_address",sevenaddress);
                        startActivity(nitratenitriteActivity);
                        break;

                    case 7:
                        Toast.makeText(getBaseContext(), "Mount LED at 570nm", Toast.LENGTH_LONG).show();
                        Intent eightint = getIntent();
                        String seventhaddress = eightint.getStringExtra("device_address");
                        Intent pHActivity = new Intent(getApplicationContext(),BlankActivity.class);
                        pHActivity.putExtra("device_address",seventhaddress);

                        startActivity(pHActivity);
                        break;




                }
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