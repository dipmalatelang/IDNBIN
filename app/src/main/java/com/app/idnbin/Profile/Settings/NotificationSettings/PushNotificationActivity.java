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

public class PushNotificationActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    Toolbar Tb_App;
    Switch switch_closed_trade,switch_succ_withdraw,switch_pending_order,switch_margin_trdng,switch_market_news,switch_sharp_jump,switch_rise_fall
            ,switch_support,switch_price_alerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        Tb_App = findViewById(R.id.Tb_App);

        switch_closed_trade = findViewById(R.id.switch_closed_trade);
        switch_succ_withdraw = findViewById(R.id.switch_succ_withdraw);
        switch_pending_order = findViewById(R.id.switch_pending_order);
        switch_margin_trdng = findViewById(R.id.switch_margin_trdng);
        switch_market_news = findViewById(R.id.switch_market_news);
        switch_sharp_jump = findViewById(R.id.switch_sharp_jump);
        switch_rise_fall = findViewById(R.id.switch_rise_fall);
        switch_support = findViewById(R.id.switch_support);
        switch_price_alerts = findViewById(R.id.switch_price_alerts);

        switch_closed_trade.setOnCheckedChangeListener(this);
        switch_succ_withdraw.setOnCheckedChangeListener(this);
        switch_pending_order.setOnCheckedChangeListener(this);
        switch_margin_trdng.setOnCheckedChangeListener(this);
        switch_market_news.setOnCheckedChangeListener(this);
        switch_sharp_jump.setOnCheckedChangeListener(this);
        switch_rise_fall.setOnCheckedChangeListener(this);
        switch_support.setOnCheckedChangeListener(this);
        switch_price_alerts.setOnCheckedChangeListener(this);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Push Notifications");
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
        switch (buttonView.getId()){
            case R.id.switch_closed_trade:
                if (isChecked) {
                    setPref(this, "switch_closed_trade", "1");
                } else {
                    setPref(this, "switch_closed_trade", "0");
                }
                break;
            case R.id.switch_succ_withdraw:
                if (isChecked) {
                    setPref(this, "switch_succ_withdraw", "1");
                } else {
                    setPref(this, "switch_succ_withdraw", "0");
                }
                break;
            case R.id.switch_pending_order:
                if (isChecked) {
                    setPref(this, "switch_pending_order", "1");
                } else {
                    setPref(this, "switch_pending_order", "0");
                }
                break;
            case R.id.switch_margin_trdng:
                if (isChecked) {
                    setPref(this, "switch_margin_trdng", "1");
                } else {
                    setPref(this, "switch_margin_trdng", "0");
                }
                break;
            case R.id.switch_market_news:
                if (isChecked) {
                    setPref(this, "switch_market_news", "1");
                } else {
                    setPref(this, "switch_market_news", "0");
                }
                break;
            case R.id.switch_sharp_jump:
                if (isChecked) {
                    setPref(this, "switch_sharp_jump", "1");
                } else {
                    setPref(this, "switch_sharp_jump", "0");
                }
                break;
            case R.id.switch_rise_fall:
                if (isChecked) {
                    setPref(this, "switch_rise_fall", "1");
                } else {
                    setPref(this, "switch_rise_fall", "0");
                }
                break;
            case R.id.switch_support:
                if (isChecked) {
                    setPref(this, "switch_support", "1");
                } else {
                    setPref(this, "switch_support", "0");
                }
                break;
            case R.id.switch_price_alerts:
                if (isChecked) {
                    setPref(this, "switch_price_alerts", "1");
                } else {
                    setPref(this, "switch_price_alerts", "0");
                }
                break;
        }
    }

    public void checkSetting(){
        if (getPref(this, "switch_closed_trade").equalsIgnoreCase("1")) {
            switch_closed_trade.setChecked(true);
        }

        if (getPref(this, "switch_succ_withdraw").equalsIgnoreCase("1")) {
            switch_succ_withdraw.setChecked(true);
        }

        if (getPref(this, "switch_pending_order").equalsIgnoreCase("1")) {
            switch_pending_order.setChecked(true);
        }


        if (getPref(this, "switch_margin_trdng").equalsIgnoreCase("1")) {
            switch_margin_trdng.setChecked(true);
        }


        if (getPref(this, "switch_market_news").equalsIgnoreCase("1")) {
            switch_market_news.setChecked(true);
        }


        if (getPref(this, "switch_sharp_jump").equalsIgnoreCase("1")) {
            switch_sharp_jump.setChecked(true);
        }

        if (getPref(this, "switch_rise_fall").equalsIgnoreCase("1")) {
            switch_rise_fall.setChecked(true);
        }

        if (getPref(this, "switch_support").equalsIgnoreCase("1")) {
            switch_support.setChecked(true);
        }

        if (getPref(this, "switch_price_alerts").equalsIgnoreCase("1")) {
            switch_price_alerts.setChecked(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkSetting();
    }
}
