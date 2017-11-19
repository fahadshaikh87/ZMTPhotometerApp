package de.zmt.photometerapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by FAHAD SHAIKH on 7/7/2017.
 */

public class ListDataAdapter extends ArrayAdapter {
    List list=new ArrayList();

    public ListDataAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    static class LayoutHandler
    {

         TextView SAMPLE,VALUE,UNIT;
    }

    @Override
    public void add(Object object){

        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount(){

        return list.size();

    }

    @Override
    public Object getItem(int position){


        return list.get(position);


    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View row=convertView;
        LayoutHandler layoutHandler;

        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler=new LayoutHandler();
            layoutHandler.SAMPLE=(TextView)row.findViewById(R.id.text_user_name);
            layoutHandler.VALUE=(TextView)row.findViewById(R.id.text_user_value);
            layoutHandler.UNIT=(TextView)row.findViewById(R.id.text_user_unit);
            row.setTag(layoutHandler);





        }

        else
        {
            layoutHandler=(LayoutHandler)row.getTag();




        }

        DataProvider dataProvider=(DataProvider)this.getItem(position);
        layoutHandler.SAMPLE.setText(dataProvider.getSample());
        layoutHandler.VALUE.setText(dataProvider.getValue());
        layoutHandler.UNIT.setText(dataProvider.getUnit());



        return row;
    }
}
