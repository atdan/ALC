package com.example.eets_nostredame.cryptocurrencyconverter;

/**
 * Created by Eets_Nostredame on 04/11/2017.
 */

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import com.example.eets_nostredame.cryptocurrencyconverter.Network.Utils;

/**
 * Created by Jer on 11/2/2017.
 */

public class CurrencyLoader extends AsyncTaskLoader {
    private String mCurrencyUrl;
    public CurrencyLoader(Context context, String currencyUrl) {
        super(context);
        mCurrencyUrl = currencyUrl;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public Object loadInBackground() {
        if (mCurrencyUrl == null){
            return null;
        }
        return Utils.fetchCurrencyData(mCurrencyUrl, getContext());
    }
}