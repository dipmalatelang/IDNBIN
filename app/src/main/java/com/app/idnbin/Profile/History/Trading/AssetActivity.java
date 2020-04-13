package com.app.idnbin.Profile.History.Trading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.app.idnbin.Assets.Base.Commodities;
import com.app.idnbin.Assets.Base.Crypto;
import com.app.idnbin.Assets.Base.Digital;
import com.app.idnbin.Assets.Base.ETF;
import com.app.idnbin.Assets.Base.Forex;
import com.app.idnbin.Assets.Base.Stocks;
import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.R;

import java.util.ArrayList;

public class AssetActivity extends AppCompatActivity {
    RecyclerView RvAsset;
    CheckBox CbSelectAll;
    TextView TvDigital;
    Digital digital = new Digital();
    Forex forex = new Forex();
    Stocks stocks = new Stocks();
    Crypto crypto = new Crypto();
    Commodities commodities = new Commodities();
    ETF etf = new ETF();
    AssetAdapter assetAdapter;
    ArrayList<SymbolsData> symbolsData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        RvAsset = findViewById(R.id.RvAsset);
        CbSelectAll = findViewById(R.id.CbSelectAll);
        TvDigital = findViewById(R.id.TvDigital);

        CbSelectAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            for (int i = 0; i < symbolsData.size(); i++) {
                if (isChecked) {
                    symbolsData.get(i).setIschecked(true);
                } else {
                    symbolsData.get(i).setIschecked(false);
                }
                assetAdapter = new AssetAdapter(AssetActivity.this, symbolsData);
                RvAsset.setAdapter(assetAdapter);
                assetAdapter.notifyDataSetChanged();
            }
        });

        symbolsData.addAll(digital.getDigitallist());
        symbolsData.addAll(forex.getForexlist());
        symbolsData.addAll(stocks.getStockslist());
        symbolsData.addAll(crypto.getCryptolist());
        symbolsData.addAll(commodities.getCommoditieslist());
        symbolsData.addAll(etf.getETFlist());

        RvAsset.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RvAsset.setItemAnimator(new DefaultItemAnimator());
        RvAsset.setNestedScrollingEnabled(false);
        assetAdapter = new AssetAdapter(AssetActivity.this, symbolsData);
        RvAsset.setAdapter(assetAdapter);
        assetAdapter.notifyDataSetChanged();
    }
}