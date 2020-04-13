package com.app.idnbin.Profile.History.Trading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.idnbin.Profile.History.Operation.DateActivity;
import com.app.idnbin.Profile.History.Operation.OperationsHistoryActivity;
import com.app.idnbin.R;

public class TradingHistoryActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout LLInstrumentType,LLBalance,LLDate,LLAsset;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trading_history);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Trading History");

        LLInstrumentType = findViewById(R.id.LLInstrumentType);
        LLBalance = findViewById(R.id.LLBalance);
        LLDate =findViewById(R.id.LLDate);
        LLAsset = findViewById(R.id.LLAsset);
        TextView TvApply = findViewById(R.id.TvApply);

        LLInstrumentType.setOnClickListener(this);
        LLBalance.setOnClickListener(this);
        LLDate.setOnClickListener(this);
        LLAsset.setOnClickListener(this);
        TvApply.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.LLInstrumentType:
                startActivity(new Intent(this, InstrumentTypeActivity.class));
                break;
            case R.id.LLBalance:
                startActivity(new Intent(this, BalanceActivity.class));
                break;
            case R.id.LLAsset:
                startActivity(new Intent(this, AssetActivity.class));
                break;
            case R.id.LLDate:
                startActivity(new Intent(this, DateActivity.class));
                break;
            case R.id.TvApply:
                startActivity(new Intent(this, TradHistoryActivity.class));
        }

    }
}
