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
    private final int MONTH = 2;
    private final int YEAR = 1;
    private ArrayList<BarEntry> barEntries;
    private BarChart chartDay;
    private BarChart chartMonth;
    private BarChart chartYear;
    private DayChart dayChart;
    private String[] monthArray;

    public int selectedMonth;
    public int selectedYear;
    public int selection;

    private Spinner spSelection;
    public TextView tvOption;
    public TextView tvOptionEnd;
    private Year year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        this.chartYear = (BarChart) findViewById(R.id.chartYear);
        this.chartMonth = (BarChart) findViewById(R.id.chartMonth);
        this.chartDay = (BarChart) findViewById(R.id.chartDay);
        this.spSelection = (Spinner) findViewById(R.id.spSelection);
        this.tvOption = (TextView) findViewById(R.id.tvOption);
        this.tvOptionEnd = (TextView) findViewById(R.id.tvOptionEnd);
        this.tvOption.setOnClickListener(this);
        this.tvOptionEnd.setOnClickListener(this);
        this.spSelection.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.selection)));
        this.spSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Calendar calendar = Calendar.getInstance();
                IncomeActivity.this.selectedMonth = calendar.get(Calendar.MONTH) + 1;
                IncomeActivity.this.selectedYear = calendar.get(Calendar.YEAR);
                if (position == 0) {
                    IncomeActivity.this.selection = 1;
                    IncomeActivity.this.getAmount(1, String.valueOf(IncomeActivity.this.selectedYear), null);
                } else if (position == 1) {
                    IncomeActivity.this.selection = 2;
                    IncomeActivity.this.getAmount(2, String.valueOf(IncomeActivity.this.selectedYear), String.valueOf(IncomeActivity.this.selectedMonth));
                } else {
                    IncomeActivity.this.selection = 3;
                    IncomeActivity.this.getAmount(3, IncomeActivity.this.tvOption.getText().toString(), IncomeActivity.this.tvOptionEnd.getText().toString());
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.barEntries = new ArrayList<>();
        this.year = new Year();
        this.dayChart = new DayChart();
        this.monthArray = getResources().getStringArray(R.array.month_name);
    }

    @Override
    public void onClick(View view) {

    }

    public void getAmount(int type, String year2, String month) {

    }
}
