package com.example.eets_nostredame.cryptocurrencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ConvertActivity extends AppCompatActivity {

    private static final String TAG = null;
    TextView BTCconvertedTextView;
    TextView ETHconvertedTextView;
    View btcView;
    View ethView;
    double btcRate;
    double ethRate;


    double BTCtotalAmount;
    double ETHtotalAmount;
    EditText BTCconvertAmountEdit;
    EditText ETHconvertAmountEdit;
    TextView currencyID;
    TextView currencyIDD;
    String getCurrencyID;
    String getCurrencyName;
    TextView CurrencyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_convert);
        setContentView(R.layout.convert_dialog);

        btcView = findViewById(R.id.btc_view_id);
        ethView = findViewById(R.id.eth_view_id);
        CurrencyName = findViewById(R.id.currency_name);



        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            getCurrencyName = bundle.getString("key2");
            getCurrencyID = bundle.getString("key");
            btcRate = bundle.getDouble("btcRate", 0.0);
            ethRate = bundle.getDouble("ethRate", 0.0);

        }



        BTCconvertAmountEdit = findViewById(R.id.btc_edit_textview);
        ETHconvertAmountEdit = findViewById(R.id.eth_edit_textView);
        ETHconvertedTextView = findViewById(R.id.converted_ETH);
        BTCconvertedTextView = findViewById(R.id.converted_BTC);
        currencyID = findViewById(R.id.currency_id);
        currencyID.setText(getCurrencyID);
        currencyIDD = findViewById(R.id.currency_idd);
        currencyIDD.setText(getCurrencyID);
        CurrencyName.setText(getCurrencyName);


        BTCconvertAmountEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                currencyID.setText(getCurrencyID);



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                BTCconvertedTextView.setText("");
                currencyID.setText(getCurrencyID);



                try {
                    double convertAmount = Double.parseDouble(BTCconvertAmountEdit.getText().toString());
                    BTCtotalAmount = convertAmount / btcRate;
                    String final_text_BTC =""+ BTCtotalAmount + " BTC";
                    BTCconvertedTextView.setText(final_text_BTC);
                    Log.i("amount:: ", "" + BTCtotalAmount);
                }catch (Exception e){
                    Log.e(TAG, "onTextChanged: "+ e );
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                currencyID.setText(getCurrencyID);

            }
        });




        ETHconvertAmountEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                currencyID.setText(getCurrencyID);



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ETHconvertedTextView.setText("");
                currencyID.setText(getCurrencyID);

                try {
                    double convertAmount = Double.parseDouble(BTCconvertAmountEdit.getText().toString());
                    ETHtotalAmount = convertAmount / ethRate;
                    //String final_text_ETH = ETHtotalAmount + " ETH";
                    ETHconvertedTextView.setText(""+ ETHtotalAmount + " ETH");
                    Log.i("amount:: ", "" + BTCtotalAmount);
                }catch (Exception e){
                    Log.e(TAG, "onTextChanged: "+ e );
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                currencyID.setText(getCurrencyID);



            }
        });


    }
}
