package de.zmt.photometerapp;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;




import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Handler;




public class MainLayout extends AppCompatActivity {


    //widgets
    Button btnPaired, BluetoothButton;
    private Button b_get;
    private GPSTracker gps;
    double longitude;
    double latitude;

    ListView devicelist;
    int BLUETOOTH_REQUEST = 1;
    TextView tdate;


    //Bluetooth
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";
    private BluetoothSocket bTSocket;

    public static final String TAG = "ServerConnectThread";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Thread t = new Thread() {

            @Override
            public void run() {

                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                TextView tdate = (TextView) findViewById(R.id.DateTime);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss ");
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);


                            }
                        });
                    }
                } catch (InterruptedException e) {

                }
            }
        };

        t.start();


        btnPaired = (Button) findViewById(R.id.PairedDevicesButton);
        devicelist = (ListView) findViewById(R.id.ListView);
        BluetoothButton = (Button) findViewById(R.id.BluetoothButton);


        //if the device has bluetooth

        BluetoothButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myBluetooth = myBluetooth.getDefaultAdapter();
                if (myBluetooth == null) {
                    Toast.makeText(getBaseContext(), "No Bluetooth adpater found", Toast.LENGTH_LONG).show();

                } else {

                    if (!myBluetooth.isEnabled()) {
                        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(i, BLUETOOTH_REQUEST);

                    }
                }
            }
        });


        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pairedDevicesList();
            }
        });

    }

    private void pairedDevicesList() {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bt : pairedDevices) {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            // Make an intent to start next activity.
            Intent i = new Intent(MainLayout.this, SecondLayer.class);

            //Change the activity.
            i.putExtra(EXTRA_ADDRESS, address); //this will be received at Second layer (class) Activity
            startActivity(i);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.info_id:
                Toast.makeText(getApplicationContext(), "The Leibniz Centre for Tropical Marine Research aims to provide a scientific basis for the protection and sustainable use of tropical coastal ecosystems. Its activities are focused on three main areas:\n" +
                        "Research: In close cooperation with partners in the tropics, the ZMT leads interdisciplinary projects concerning the structure and functioning of tropical coastal ecosystems and their response to human impacts and natural changes.\n" +
                        "Capacity building: The ZMT is involved in the education of students and young scientists from all over the world in the field of tropical marine ecology. In tropical countries, it further supports capacity building within this field of research.\n" +
                        "Consulting: The ZMT is both a national and international focal point for research, educational and government institutions for the exchange of expert knowledge in the field of tropical marine ecology", Toast.LENGTH_LONG).show();

                break;
            case R.id.contact_us:
                Toast.makeText(getApplicationContext(), "Leibniz Centre for Tropical Marine Research GmbH\n" +
                        "Fahrenheitstraße 6\n" +
                        "28359 Bremen\n" +
                        "Germany\n" +
                        "\n" +
                        "Phone: +49 (421) 23800-0\n" +
                        "Fax: +49 (421) 23800-30\n" +
                        "E-mail: contact@zmt-bremen.de", Toast.LENGTH_LONG).show();
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }





        public void getLocation(View view)
        {


            b_get = (Button) findViewById(R.id.LocationButton);


            b_get.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    gps = new GPSTracker(MainLayout.this);


                    if (gps.canGetLocation()) {


                        longitude = gps.getLongitude();
                        latitude = gps.getLatitude();

                        Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
                    } else {

                        gps.showSettingsAlert();
                    }

                }
            });
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            gps.stopUsingGPS();
        }
    }





