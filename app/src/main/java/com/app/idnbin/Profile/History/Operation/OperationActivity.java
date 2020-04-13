package com.app.idnbin.Profile.History.Operation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.app.idnbin.R;

public class OperationActivity extends AppCompatActivity {
    Toolbar Tb_App;
    CheckBox allCheckBox,depositCheckBox,withdrawalCheckBox,trebuyCheckBox,trewardCheckBox;
    private boolean isAllChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        Tb_App = findViewById(R.id.Tb_App);

        allCheckBox = findViewById(R.id.allCheckBox);
        depositCheckBox = findViewById(R.id.depositCheckBox);
        withdrawalCheckBox = findViewById(R.id.withdrawalCheckBox);
        trebuyCheckBox = findViewById(R.id.trebuyCheckBox);
        trewardCheckBox = findViewById(R.id.trewardCheckBox);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Operation");

        allCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(allCheckBox.isChecked()){
                    isAllChecked = true;
                    depositCheckBox.setChecked(true);
                    withdrawalCheckBox.setChecked(true);
                    trebuyCheckBox.setChecked(true);
                    trewardCheckBox.setChecked(true);
                }else {
                    isAllChecked = false;
                    depositCheckBox.setChecked(false);
                    withdrawalCheckBox.setChecked(false);
                    trebuyCheckBox.setChecked(false);
                    trewardCheckBox.setChecked(false);
                }
            }
        });
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
                if(!checked && isAllChecked){
                    allCheckBox.setChecked(false);
                }
                break;
        }

    }
}