package com.example.eets_nostredame.cryptocurrencyconverter;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView listView;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> listadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listadapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1,list);
        listView = findViewById(R.id.list_of_added_currencies);
        spinner = findViewById(R.id.list_item_spinner);

        Resources resources = getResources();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.currency_name));
        spinner.setAdapter(arrayAdapter);
        listView.setAdapter(listadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s = spinner.getItemAtPosition(i).toString();
                if (!list.contains(s)){
                    list.add(s);
                    listadapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
