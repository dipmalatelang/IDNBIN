package com.app.idnbin.Profile.History.Trading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.app.idnbin.R;

public class InstrumentTypeActivity extends AppCompatActivity {
    Toolbar Tb_App;
    CheckBox allCheckBox, OptionsCheckBox, forexCheckBox, stocksCheckBox, cryptoCheckBox, commoditiesCheckBox, etfCheckBox;
    private static final String TAG = "InstrumentTypeActivity";
    private boolean isAllChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_type);
        Tb_App = findViewById(R.id.Tb_App);

        allCheckBox = findViewById(R.id.allCheckBox);
        OptionsCheckBox = findViewById(R.id.OptionsCheckBox);
        forexCheckBox = findViewById(R.id.forexCheckBox);
        stocksCheckBox = findViewById(R.id.stocksCheckBox);
        cryptoCheckBox = findViewById(R.id.cryptoCheckBox);
        commoditiesCheckBox = findViewById(R.id.commoditiesCheckBox);
        etfCheckBox = findViewById(R.id.etfCheckBox);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Instrument type");

        allCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(allCheckBox.isChecked()){
                    isAllChecked = true;
                    OptionsCheckBox.setChecked(true);
                    forexCheckBox.setChecked(true);
                    stocksCheckBox.setChecked(true);
                    cryptoCheckBox.setChecked(true);
                    commoditiesCheckBox.setChecked(true);
                    etfCheckBox.setChecked(true);
                }else {
                    isAllChecked = false;
                    OptionsCheckBox.setChecked(false);
                    forexCheckBox.setChecked(false);
                    stocksCheckBox.setChecked(false);
                    cryptoCheckBox.setChecked(false);
                    commoditiesCheckBox.setChecked(false);
                    etfCheckBox.setChecked(false);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onCheckBoxClick(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){

            case R.id.OptionsCheckBox:
            case R.id.forexCheckBox:
            case R.id.stocksCheckBox:
            case R.id.cryptoCheckBox:
            case R.id.commoditiesCheckBox:
            case R.id.etfCheckBox:
                if(!checked && isAllChecked){
                    allCheckBox.setChecked(false);
                }
                break;
        }
    }
}
