package com.app.idnbin.Profile.History.Operation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.idnbin.R;

public class OperationsHistoryActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar Tb_App;
    LinearLayout LLOperations,LLStatus,LLDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations_history);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Operation");
        LLOperations=findViewById(R.id.LLOperations);
        LLStatus=findViewById(R.id.LLStatus);
        LLDate=findViewById(R.id.LLDate);
        TextView TvApply = findViewById(R.id.TvApply);

        LLOperations.setOnClickListener(this);
        LLDate.setOnClickListener(this);
        LLStatus.setOnClickListener(this);
        TvApply.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LLOperations:
                startActivity(new Intent(this, OperationActivity.class));
                break;
            case R.id.LLStatus:
                startActivity(new Intent(this, StatusActivity.class));
                break;
            case R.id.LLDate:
                startActivity(new Intent(this, DateActivity.class));
                break;
            case R.id.TvApply:
                startActivity(new Intent(this,OperationHistoryActivity.class));
        }

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
}