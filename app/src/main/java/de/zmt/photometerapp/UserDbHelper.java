package de.zmt.photometerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by FAHAD SHAIKH on 7/6/2017.
 */

public class UserDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="USERINFO.DB";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_QUERY=
            "CREATE TABLE "+UserContract.NewUserInfo.TABLE_NAME+"("+ UserContract.NewUserInfo.SAMPLE_NAME+" TEXT,"+
            UserContract.NewUserInfo.VALUE+" NUMBER ,"+UserContract.NewUserInfo.UNIT+" TEXT );";


    public UserDbHelper(Context context)
    {
        super (context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS","Database created/opened.....");

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS","Table created....");


    }

    public void addInformations(String name,String value,String unit,SQLiteDatabase db)
    {
        ContentValues contentValues=new ContentValues();


        contentValues.put(UserContract.NewUserInfo.SAMPLE_NAME,name);
        contentValues.put(UserContract.NewUserInfo.VALUE,value);
        contentValues.put(UserContract.NewUserInfo.UNIT,unit);

        db.insert(UserContract.NewUserInfo.TABLE_NAME,null,contentValues);

        Log.e("DATABASE OPERATIONS","One row inserted....");






    }

    public Cursor getInformations(SQLiteDatabase db)
    {

       Cursor cursor;
        String[] projections={UserContract.NewUserInfo.SAMPLE_NAME,UserContract.NewUserInfo.VALUE,UserContract.NewUserInfo.UNIT};
       cursor= db.query(UserContract.NewUserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;



    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
