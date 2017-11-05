package com.example.eets_nostredame.cryptocurrencyconverter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ListActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView listView;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> listadapter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String selectedCountries;

    public static String SELECTED_CURRENCIES_KEY = "selected-countries";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //onRestoredPreferences();
        listadapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1,list);
        listView = findViewById(R.id.list_of_added_currencies);
        spinner = findViewById(R.id.list_item_spinner);
        sharedPreferences = getSharedPreferences("currency", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        selectedCountries = sharedPreferences.getString(SELECTED_CURRENCIES_KEY," ");

        Resources resources = getResources();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.currency_code));
        spinner.setAdapter(arrayAdapter);

        final HashMap<String, String> currencyMap = new HashMap<>();
        for(int i =0;i < resources.getStringArray(R.array.currency_code).length;i++){
//            currencyMap.put(resources.getStringArray(R.array.currency_name)[i],resources.getStringArray(R.array.currency_code)[i]);
            if(selectedCountries.contains(resources.getStringArray(R.array.currency_code)[i]+" ")){
                if(!resources.getStringArray(R.array.currency_code)[i].equals("NGN")) {
                    list.add(resources.getStringArray(R.array.currency_code)[i]);
                }
            }
        }
        listView.setAdapter(listadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s = spinner.getItemAtPosition(i).toString() + " ";
//                String s = currencyMap.get(key)+ " ";
                if(!selectedCountries.contains(s)){
                    selectedCountries+= s;
                    editor.putString(SELECTED_CURRENCIES_KEY,selectedCountries);
                    editor.apply();
                }
                if (!list.contains(s)){
                    list.add(s);
                    listadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Savedpreferences(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
    public void Savedpreferences(ArrayList<String> list) {


        SharedPreferences preferences = this.getSharedPreferences("object", 0);
        SharedPreferences.Editor editor = preferences.edit();
        ArrayList<String> arraylist = new ArrayList<String>(list);
        Set<String> newset = new HashSet<String>(arraylist);
        editor.putStringSet("stringset", newset);
        editor.apply();
    }


    public void onRestoredPreferences()
    {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("object", 0);
        Set<String> getdataback = preferences.getStringSet("stringset", null);
        //list.clear();
//        list.addAll(getdataback);
    }
}
