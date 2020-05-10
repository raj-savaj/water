package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;
import com.rj.watersupply.Util.InputValidatorHelper;
import com.rj.watersupply.WebApi.API;
import com.rj.watersupply.WebApi.Builder;
import com.rj.watersupply.modal.ResponseLogin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomer extends AppCompatActivity {

    TextInputLayout txtName, txtaddress, txtmno;
    TextInputLayout txtColdQty, txtHotQty, txtColdPrice, txtHotPrice, txtReturnCold, txtReturnHot;
    TextInputLayout txtDebit, txtCredit, txtTotal;
    MaterialButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        init();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()){
                    HashMap<String, String> hashMap=new HashMap<>();
                    hashMap.put("name",txtName.getEditText().getText().toString());
                    hashMap.put("add",txtaddress.getEditText().getText().toString());
                    hashMap.put("phone",txtmno.getEditText().getText().toString());
                    hashMap.put("cBottle",txtColdQty.getEditText().getText().toString());
                    hashMap.put("hBottle",txtHotQty.getEditText().getText().toString());
                    hashMap.put("cBottlePr",txtColdPrice.getEditText().getText().toString());
                    hashMap.put("hBottlePr",txtHotPrice.getEditText().getText().toString());
                    hashMap.put("cBottleRt",txtReturnCold.getEditText().getText().toString());
                    hashMap.put("hBottleRt",txtReturnHot.getEditText().getText().toString());
                    hashMap.put("credit",txtCredit.getEditText().getText().toString());
                    hashMap.put("debit",txtDebit.getEditText().getText().toString());
                    hashMap.put("total",txtTotal.getEditText().getText().toString());
                    btnSave.setEnabled(false);
                    API api= Builder.getClient();
                    Call<ResponseLogin> call=api.InsertCustomer(hashMap);
                    call.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            btnSave.setEnabled(true);
                            if(response.isSuccessful()) {
                                if(response.body().getStatus()==200){
                                    Toast.makeText(AddCustomer.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(getApplicationContext(),Customer.class);
                                    startActivity(i);
                                }
                                if(response.body().getStatus()==401){
                                    txtmno.setError(response.body().getMsg());
                                }
                            }else{
                                Toast.makeText(AddCustomer.this, "Server Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            btnSave.setEnabled(true);
                            Toast.makeText(AddCustomer.this, "Page Not Found ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void init() {
        txtName = findViewById(R.id.txtName);
        txtaddress = findViewById(R.id.txtaddress);
        txtmno = findViewById(R.id.txtmno);
        txtColdQty = findViewById(R.id.txtColdQty);
        txtHotQty = findViewById(R.id.txtHotQty);
        txtColdPrice = findViewById(R.id.txtColdPrice);
        txtHotPrice = findViewById(R.id.txtHotPrice);
        txtReturnCold = findViewById(R.id.txtReturnCold);
        txtReturnHot = findViewById(R.id.txtReturnHot);
        txtDebit = findViewById(R.id.txtDebit);
        txtCredit = findViewById(R.id.txtCredit);
        txtTotal = findViewById(R.id.txtTotal);
        btnSave = findViewById(R.id.btnSave);
    }

    public boolean validation(){
        boolean status=true;
        InputValidatorHelper v=new InputValidatorHelper();

        if(v.isNullOrEmpty(txtName.getEditText().getText().toString())){
            status=false;
            txtName.setError("Please Enter Name");
        }
        else{
            txtName.setErrorEnabled(false);
        }

        if(v.isNullOrEmpty(txtaddress.getEditText().getText().toString())){
            status=false;
            txtaddress.setError("Please Enter Address");
        }
        else{
            txtaddress.setErrorEnabled(false);
        }

        if(v.isNullOrEmpty(txtmno.getEditText().getText().toString())){
            status=false;
            txtmno.setError("Please Enter Mobile No");
        }
        else{
            txtmno.setErrorEnabled(false);
        }

        if(v.isNullOrEmpty(txtColdQty.getEditText().getText().toString())){
            status=false;
            txtColdQty.setError("Please Enter Cold Qty");
        }
        else{
            txtColdQty.setErrorEnabled(false);
        }

        if(v.isNullOrEmpty(txtHotQty.getEditText().getText().toString())){
            status=false;
            txtHotQty.setError("Please Enter Hot Qty");
        }
        else{
            txtHotQty.setErrorEnabled(false);
        }

        if(v.isNullOrEmpty(txtColdPrice.getEditText().getText().toString())){
            status=false;
            txtColdPrice.setError("Please Enter Cold Price");
        }
        else{
            txtColdPrice.setErrorEnabled(false);
        }

        if(v.isNullOrEmpty(txtHotPrice.getEditText().getText().toString())){
            status=false;
            txtHotPrice.setError("Please Enter Hot Price");
        }
        else{
            txtHotPrice.setErrorEnabled(false);
        }

        if(v.isNullOrEmpty(txtDebit.getEditText().getText().toString())){
            status=false;
            txtDebit.setError("Please Enter Debit Amount");
        }
        else{
            txtDebit.setErrorEnabled(false);
        }

        if(v.isNullOrEmpty(txtCredit.getEditText().getText().toString())){
            status=false;
            txtCredit.setError("Please Enter Credit Amount");
        }
        else{
            txtCredit.setErrorEnabled(false);
        }
        return status;
    }
}
