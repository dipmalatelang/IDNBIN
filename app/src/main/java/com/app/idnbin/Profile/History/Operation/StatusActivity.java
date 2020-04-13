package com.app.idnbin.Profile.History.Operation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.app.idnbin.R;

public class StatusActivity extends AppCompatActivity {
    Toolbar Tb_App;
    private boolean isAllChceked = false;
    CheckBox allCheckBox,compelteCheckBox,inprocessCheckBox,canceledCheckBox,failedCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Tb_App = findViewById(R.id.Tb_App);
        allCheckBox =findViewById(R.id.allCheckBox);
        compelteCheckBox = findViewById(R.id.depositCheckBox);
        inprocessCheckBox =findViewById(R.id.withdrawalCheckBox);
        canceledCheckBox = findViewById(R.id.trebuyCheckBox);
        failedCheckBox = findViewById(R.id.trewardCheckBox);

        allCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(allCheckBox.isChecked()){
                    isAllChceked = true;
                    compelteCheckBox.setChecked(true);
                    inprocessCheckBox.setChecked(true);
                    canceledCheckBox.setChecked(true);
                    failedCheckBox.setChecked(true);

                }else {
                    isAllChceked = false;
                    compelteCheckBox.setChecked(false);
                    inprocessCheckBox.setChecked(false);
                    canceledCheckBox.setChecked(false);
                    failedCheckBox.setChecked(false);
                }


            }
        });

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Status");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onCheckBoxClick(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){

            case R.id.depositCheckBox:
            case R.id.withdrawalCheckBox:
            case R.id.trebuyCheckBox:
            case R.id.trewardCheckBox:
                if(!checked && isAllChceked){
                    allCheckBox.setChecked(false);
                }
                break;
        }

    }
}