package com.app.idnbin.Profile.History.Operation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar Tb_App;
    RadioButton RB_Custom,RB_alltime,RB_day,RB_day1,RB_day2,RB_day3,RB_day4;
    LinearLayout LL_CustomDate;
    LinearLayout to_date_layout, from_date_layout;
    TextView display_from_date,display_to_date;
    static final int DATE_DIALOG_ID = 1;
    static final int TO_DIALOG_ID = 2;
    private int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        Tb_App = findViewById(R.id.Tb_App);
        RB_Custom =findViewById(R.id.RB_Custom);
        RB_alltime =findViewById(R.id.RB_alltime);
        RB_day =findViewById(R.id.RB_day);
        RB_day1 =findViewById(R.id.RB_day1);
        RB_day2 =findViewById(R.id.RB_day2);
        RB_day3 =findViewById(R.id.RB_day3);
        RB_day4 =findViewById(R.id.RB_day4);

        LL_CustomDate = findViewById(R.id.LL_CustomDate);
        to_date_layout=findViewById(R.id.to_date_layout);
        from_date_layout=findViewById(R.id.from_date_layout);
        display_from_date=findViewById(R.id.display_from_date);
        display_to_date=findViewById(R.id.display_to_date);

        to_date_layout.setOnClickListener(this);
        from_date_layout.setOnClickListener(this);
        to_date_layout.setOnClickListener(this);
        from_date_layout.setOnClickListener(this);
        RB_Custom.setOnClickListener(this);
        RB_alltime.setOnClickListener(this);
        RB_day.setOnClickListener(this);
        RB_day1.setOnClickListener(this);
        RB_day2.setOnClickListener(this);
        RB_day3.setOnClickListener(this);
        RB_day4.setOnClickListener(this);


        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String dateToStr = format.format(today);
        display_from_date.setText(dateToStr);
        display_to_date.setText(dateToStr);
        display_from_date.setText(dateToStr);
        display_to_date.setText(dateToStr);

        Boolean RadioButtonState = RB_Custom.isChecked();
        Toast.makeText(this, ""+RadioButtonState, Toast.LENGTH_SHORT).show();
        
        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Date");
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.from_date_layout:
                showDialog(DATE_DIALOG_ID);
                break;
            case R.id.to_date_layout:
                showDialog(DATE_DIALOG_ID);
                break;
            case R.id.RB_Custom:
                if(RB_Custom.isChecked())
                {
                    LL_CustomDate.setVisibility(View.VISIBLE);
                    Toast.makeText(DateActivity.this, "visible", Toast.LENGTH_SHORT).show();
                }
                else if(!RB_Custom.isChecked())
                {
                    LL_CustomDate.setVisibility(View.GONE);
                    Toast.makeText(DateActivity.this, "gone", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.RB_alltime:
                LL_CustomDate.setVisibility(View.GONE);
                break;
            case R.id.RB_day:
                LL_CustomDate.setVisibility(View.GONE);
                break;
            case R.id.RB_day1:
                LL_CustomDate.setVisibility(View.GONE);
                break;
            case R.id.RB_day2:
                LL_CustomDate.setVisibility(View.GONE);
                break;
            case R.id.RB_day3:
                LL_CustomDate.setVisibility(View.GONE);
                break;
            case R.id.RB_day4:
                LL_CustomDate.setVisibility(View.GONE);
                break;
        }

    }
    @Override
    protected Dialog onCreateDialog(int id) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        int mYear = c.get(java.util.Calendar.YEAR);
        int mMonth = c.get(java.util.Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case DATE_DIALOG_ID:
                DatePickerDialog fromdatePicker=new DatePickerDialog(this, datePickerListener, mYear, mMonth,mDay);
                fromdatePicker.updateDate(mYear, mMonth, mDay);
                return fromdatePicker;
            case TO_DIALOG_ID:
                DatePickerDialog todatePicker=new DatePickerDialog(this, datePickerListener1, mYear, mMonth,mDay);
                todatePicker.updateDate(mYear, mMonth, mDay);
                return todatePicker;
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            display_from_date.setText(new StringBuilder().append(month + 1)
                    .append("/").append(day).append("/").append(year)
                    .append(" "));

        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener1
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            display_to_date.setText(new StringBuilder().append(month + 1)
                    .append("/").append(day).append("/").append(year)
                    .append(" "));

        }
    };

}
