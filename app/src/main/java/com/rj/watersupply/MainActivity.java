package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;

import android.view.WindowManager.LayoutParams;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.DatePicker.OnDateChangedListener;
import java.util.Calendar;
import android.os.Build.VERSION;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivAbout;
    private ImageView ivAdd;
    private ImageView ivAddBottle;
    private ImageView ivCalendar;
    private ImageView ivIncome;
    private ImageView ivLogout;
    public Dialog addScheduleDataDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ivAddBottle = (ImageView) findViewById(R.id.ivAddBottle);
        this.ivAddBottle.setOnClickListener(this);
        this.ivAdd = (ImageView) findViewById(R.id.ivAdd);
        this.ivAdd.setOnClickListener(this);
        this.ivCalendar = (ImageView) findViewById(R.id.ivCalendar);
        this.ivCalendar.setOnClickListener(this);
        this.ivLogout = (ImageView) findViewById(R.id.ivLogout);
        this.ivLogout.setOnClickListener(this);
        this.ivIncome = (ImageView) findViewById(R.id.ivIncome);
        this.ivIncome.setOnClickListener(this);
        this.ivAbout = (ImageView) findViewById(R.id.ivAbout);
        this.ivAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivAddBottle:
                startActivity(new Intent(this, AddBottle.class));
                return;
            case R.id.ivAdd :
                startActivity(new Intent(this, AddData.class));
                return;
            case R.id.ivCalendar:
                startActivity(new Intent(this, MyScheduleCalendarActivity.class));
                return;
            case R.id.ivIncome :
                startActivity(new Intent(this, IncomeActivity.class));
                return;
            case R.id.ivAbout :
                 startActivity(new Intent(this, ViewDetail.class));
                return;
            case R.id.ivLogout :
                //logout();
                return;
            default:
                return;
        }
    }


}
