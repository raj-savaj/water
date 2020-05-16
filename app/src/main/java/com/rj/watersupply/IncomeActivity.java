package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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
