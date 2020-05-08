package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class AddCustomer extends AppCompatActivity {

    TextInputLayout txtDate;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        txtDate=findViewById(R.id.txtDate);
        Objects.requireNonNull(txtDate.getEditText()).setShowSoftInputOnFocus(false);
        txtDate.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    MaterialDatePicker.Builder builder =MaterialDatePicker.Builder.datePicker();
                    MaterialDatePicker picker = builder.build();
                    picker.show(getSupportFragmentManager(), picker.toString());
                    picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                            txtDate.getEditText().setText(formatter.format(new Date(Long.parseLong(selection.toString()))));
                        }
                    });
                }
            }
        });
    }
}
