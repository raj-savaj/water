package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rj.watersupply.Adapter.CustomerAdapter;
import com.rj.watersupply.WebApi.API;
import com.rj.watersupply.WebApi.Builder;
import com.rj.watersupply.modal.CustomerData;
import com.rj.watersupply.modal.ResponseLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Customer extends AppCompatActivity {
    List<CustomerData> dataList;
    RecyclerView rcvCustomer;
    FloatingActionButton fab;
    CustomerAdapter customerAdapter;
    EditText txtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        txtSearch=findViewById(R.id.txtSearch);
        rcvCustomer=findViewById(R.id.rcv_customer);
        fab=findViewById(R.id.fab);
        rcvCustomer.setLayoutManager(new LinearLayoutManager(this));
        API api= Builder.getClient();
        Call<List<CustomerData>> call=api.getAllCustomer();
        call.enqueue(new Callback<List<CustomerData>>() {
            @Override
            public void onResponse(Call<List<CustomerData>> call, Response<List<CustomerData>> response) {
                if(response.isSuccessful()){
                    dataList=response.body();
                    customerAdapter=new CustomerAdapter(dataList);
                    rcvCustomer.setAdapter(customerAdapter);
                }
                else{
                    Toast.makeText(Customer.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CustomerData>> call, Throwable t) {
                Toast.makeText(Customer.this, "Page Not Found", Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AddCustomer.class);
                startActivity(i);
            }
        });

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(customerAdapter!=null){
                    customerAdapter.getFilter().filter(s.toString());
                }
            }
        });

    }
}
