package de.zmt.photometerapp;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static de.zmt.photometerapp.MainLayout.EXTRA_ADDRESS;

public class SecondLayer extends AppCompatActivity {

    Button btnDis, MenuButton;

    TextView Date;
    View V;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;

    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent newint = getIntent();
        address = newint.getStringExtra(EXTRA_ADDRESS); //receive the address of the bluetooth device

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //view of the ledControl
        setContentView(R.layout.activity_second_layer);


        Button MenuButton = (Button) findViewById(R.id.MenuButton);


        Thread t = new Thread() {

            @Override
            public void run() {

                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                TextView tdate = (TextView) findViewById(R.id.Date);
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


        //call the widgtes

        btnDis = (Button) findViewById(R.id.DisconnectButton);


        //new ConnectBT().execute(); //Call the class to connect

        //commands to be sent to bluetooth


        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disconnect(); //close connection
            }
        });


    }

    private void Disconnect() {
        if (btSocket != null) //If the btSocket is busy
        {
            try {
                btSocket.close(); //close connection
            } catch (IOException e) {
                msg("Error");
            }
        }
        finish(); //return to the first layout

    }


    // fast way to call Toast
    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(SecondLayer.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            } catch (IOException e) {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }


    public void showPopUp(View view) {
        final ListView listView;
        ArrayAdapter<String> adapter;
         String[] android_versions = new String[]{"Help", "Database", "Parameter Selection", "Measurement"};


        listView = (ListView) findViewById(R.id.menulist);
        adapter = new ArrayAdapter<String>(this, R.layout.list_view_custom_layout, R.id.list_item, android_versions);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent helpActivity = new Intent(SecondLayer.this, Help.class);
                        startActivity(helpActivity);
                        break;
                    case 1:
                        Intent databaseActivity = new Intent(SecondLayer.this, Database.class);
                        startActivity(databaseActivity);
                        break;
                    case 2:
                        Intent parameterActivity = new Intent(SecondLayer.this, Parameter.class);
                        parameterActivity.putExtra("device_address", address);
                        startActivity(parameterActivity);
                        break;

                    case 3:
                        Intent measurementActivity = new Intent(SecondLayer.this, Measurement.class);
                        startActivity(measurementActivity);
                        break;



                    default:


                        Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_LONG).show();
                        view.setSelected(true);

                        break;
                }
            }
        });
    }
}

