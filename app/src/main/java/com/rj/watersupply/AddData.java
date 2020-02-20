package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


import java.util.Calendar;

public class AddData extends AppCompatActivity {

    EditText tvDate;
    EditText tpAddDateTime;

    TimePickerDialog picker;
    DatePickerDialog datepick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        tvDate=findViewById(R.id.tvDate);
        tpAddDateTime=findViewById(R.id.tpAddDateTime);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar newCalendar = Calendar.getInstance();
                datepick = new DatePickerDialog(AddData.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        tvDate.setText(""+newDate.getTime());
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datepick.show();
            }
        });
        tpAddDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                picker = new TimePickerDialog(AddData.this, R.style.TimePicker,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tpAddDateTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minutes, true);
                picker.show();
            }
        });
    }
}
