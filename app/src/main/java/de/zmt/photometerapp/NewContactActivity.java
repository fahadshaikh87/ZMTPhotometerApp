package de.zmt.photometerapp;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by FAHAD SHAIKH on 7/6/2017.
 */

public class NewContactActivity extends Activity {
    EditText SampleName, SampleValue, SampleUnit;
    Context context=this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calibration);
        SampleName = (EditText) findViewById(R.id.samplename);
        SampleValue = (EditText) findViewById(R.id.value);
        SampleUnit = (EditText) findViewById(R.id.unit);





    }

public void addSample(View view)
{

  String name=SampleName.getText().toString();
    String value=SampleValue.getText().toString();
    String unit=SampleUnit.getText().toString();

    userDbHelper=new UserDbHelper(context);
    sqLiteDatabase=userDbHelper.getWritableDatabase();
    userDbHelper.addInformations(name,value,unit,sqLiteDatabase);
    Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_LONG).show();
    userDbHelper.close();




}


}



