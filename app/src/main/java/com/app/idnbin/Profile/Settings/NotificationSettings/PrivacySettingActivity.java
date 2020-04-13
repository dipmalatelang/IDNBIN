package com.app.idnbin.Profile.Settings.NotificationSettings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;

public class PrivacySettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    Toolbar Tb_App;

    Switch switch_communication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_setting);

        Tb_App = findViewById(R.id.Tb_App);

        switch_communication = findViewById(R.id.switch_communication);

        switch_communication.setOnCheckedChangeListener(this);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Privacy Settings");
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            setPref(this, "switch_communication", "1");
        } else {
            setPref(this, "switch_communication", "0");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPref(this, "switch_communication").equalsIgnoreCase("1")) {
            switch_communication.setChecked(true);
        }
    }
}
