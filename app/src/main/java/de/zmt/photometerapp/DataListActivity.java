package de.zmt.photometerapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class DataListActivity extends AppCompatActivity {
    ListView listview;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_layout);
        listview=(ListView)findViewById(R.id.list_view);
        listDataAdapter=new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listview.setAdapter(listDataAdapter);
        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        cursor=userDbHelper.getInformations(sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            do{
                String Sample,Value,Unit;
                Sample=cursor.getString(0);
                Value=cursor.getString(1);
                Unit=cursor.getString(2);
                DataProvider dataProvider=new DataProvider(Sample,Value,Unit);
                listDataAdapter.add(dataProvider);

            }while(cursor.moveToNext());
        }

    }
}
