package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.rj.watersupply.modal.DayChart;
import com.rj.watersupply.modal.Year;

import java.util.ArrayList;
import java.util.Calendar;
import android.text.format.DateFormat;
import java.util.HashMap;

public class IncomeActivity extends AppCompatActivity implements View.OnClickListener {

    private final int DAY = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
       
    }

    @Override
    public void onClick(View view) {

    }

    public void getAmount(int type, String year2, String month) {

    }
}
