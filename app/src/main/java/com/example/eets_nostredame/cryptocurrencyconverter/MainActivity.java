package com.example.eets_nostredame.cryptocurrencyconverter;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements  LoaderManager.LoaderCallbacks<List<MyCurrency>> {

    private CurrencyAdapter mCurrencyAdapter;
    private String coinID;


    private ListActivity listActivity;
    private String currencyRequetUrl;
    private TextView mEmptyTextView;
    private static final int CURRENCY_LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView currencyListView = (ListView) findViewById(R.id.list);
        mEmptyTextView = (TextView) findViewById(R.id.empty_text);
        listActivity = new ListActivity();

        currencyRequetUrl = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR,NGN,MXN,COP,JPY,ILS,GBP,CNY,BRL,CAD,ZAR,RUB,EGP,INR,TRY,SEK,MTL,ARS,KHR";

        ConnectivityManager connectManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(CURRENCY_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyTextView.setText(R.string.no_internet_connection);
        }



        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        currencyListView.setEmptyView(mEmptyTextView);

        mCurrencyAdapter = new CurrencyAdapter(this, new ArrayList<MyCurrency>());

        currencyListView.setAdapter(mCurrencyAdapter);

        currencyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyCurrency currentCurrency = (MyCurrency) mCurrencyAdapter.getItem(position);
                double btcRate = Double.parseDouble(currentCurrency.getBtcRate());
                double ethRate = Double.parseDouble(currentCurrency.getEthRate());
                String getCurrencyID = currentCurrency.getCurrencyID();
                String getCurrencyName = currentCurrency.getCurrencyName();


                Intent convertIntent = new Intent(view.getContext(), ConvertActivity.class);
                convertIntent.putExtra("btcRate", btcRate);
                convertIntent.putExtra("ethRate", ethRate);
                convertIntent.putExtra("key",getCurrencyID);
                convertIntent.putExtra("key2",getCurrencyName);

                startActivity(convertIntent);

            }
        });}


    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
            super.onBackPressed();
        //}
    }


    @Override
    public Loader<List<MyCurrency>> onCreateLoader(int i, Bundle bundle) {

        return new CurrencyLoader(this, currencyRequetUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<MyCurrency>> loader, List<MyCurrency> myCurrencies) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyTextView.setText(R.string.nothing_to_see_here);

        mCurrencyAdapter.clear();

        if (myCurrencies != null && !myCurrencies.isEmpty()) {
            mCurrencyAdapter.addAll(myCurrencies);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<MyCurrency>> loader) {
        mCurrencyAdapter.clear();
    }


    @Override
    public void onRestart(){
        super.onRestart();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:
                onRestart();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void add(View view) {
        Intent startPreferenceActivity = new Intent(MainActivity.this, ListActivity.class);
        startActivity(startPreferenceActivity);


    }
}