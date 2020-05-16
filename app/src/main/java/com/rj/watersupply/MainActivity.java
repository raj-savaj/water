package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.rj.watersupply.WebApi.API;
import com.rj.watersupply.WebApi.Builder;
import com.rj.watersupply.modal.ProductDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivAbout;
    private ImageView ivAdd;
    private ImageView ivAddBottle;
    private ImageView ivCalendar;
    private ImageView ivIncome;
    private ImageView ivLogout;
    TextView txtCBtl,txtCBtlR,txtHBtl,txtHBtlR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        this.ivAbout.setOnClickListener(this);
        API api= Builder.getClient();
        Call<List<ProductDetail>> call= api.getProductDetail();
        call.enqueue(new Callback<List<ProductDetail>>() {
            @Override
            public void onResponse(Call<List<ProductDetail>> call, Response<List<ProductDetail>> response) {
                if(response.isSuccessful()){
                    for(ProductDetail p:response.body()){
                        if(p.getPcode().equals("BH01")){
                            txtHBtl.setText(""+p.getQty());
                            txtHBtlR.setText(""+p.getSell_qty());
                        }
                        else if(p.getPcode().equals("BC01")){
                            txtCBtl.setText(""+p.getQty());
                            txtCBtlR.setText(""+p.getSell_qty());
                        }
                    }
                }
                else{
                    Snackbar.make(getView(), "Internal server error", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductDetail>> call, Throwable t) {
                Snackbar.make(getView(), "Page not found", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void init(){
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

        txtCBtl=findViewById(R.id.txtCBtl);
        txtCBtlR=findViewById(R.id.txtCBtlR);
        txtHBtl=findViewById(R.id.txtHBtl);
        txtHBtlR=findViewById(R.id.txtHBtlR);

    }    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivAddBottle:
                startActivity(new Intent(this, BuyBottle.class));
                return;
            case R.id.ivAdd :
                startActivity(new Intent(this, Customer.class));
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
    public View getView() {
        return findViewById(android.R.id.content);
    }
}
