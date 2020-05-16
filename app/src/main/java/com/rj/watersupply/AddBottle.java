package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSmoothScroller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.rj.watersupply.WebApi.API;
import com.rj.watersupply.WebApi.Builder;
import com.rj.watersupply.modal.Product;
import com.rj.watersupply.modal.ResponseLogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBottle extends AppCompatActivity {

    List<Product> products;
    Product product=null;
    AutoCompleteTextView edtAutocomplete;
    TextInputLayout txtQty,txtBasePrice,txtDescription;
    MaterialButton btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbottle);
        edtAutocomplete = findViewById(R.id.filled_exposed_dropdown);
        txtQty=findViewById(R.id.txtQty);
        txtBasePrice=findViewById(R.id.txtBasePrice);
        txtDescription=findViewById(R.id.txtDescription);
        btnSave=findViewById(R.id.btnSaveBottle);
        setProduct();
        edtAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                product=products.get(position);
                txtBasePrice.getEditText().setText(""+products.get(position).getBase_price());
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validation()){
                    HashMap<String, String> hashFields=new HashMap<>();
                    hashFields.put("pid",""+product.getPID());
                    hashFields.put("qty",txtQty.getEditText().getText().toString());
                    hashFields.put("price",txtBasePrice.getEditText().getText().toString());
                    hashFields.put("desc",txtDescription.getEditText().getText().toString());
                    API api=Builder.getClient();
                    Call<ResponseLogin> call=api.addBottle(hashFields);
                    call.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            if(response.isSuccessful()){
                                Snackbar.make(getView(), "Bottle successfully saved", Snackbar.LENGTH_LONG).show();
                                txtQty.getEditText().setText(null);
                                txtBasePrice.getEditText().setText(null);
                                txtDescription.getEditText().setText(null);
                            }
                            else{
                                Snackbar.make(getView(), "Please fill the detail", Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            Snackbar.make(getView(), "Page not found", Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setProduct();
                                }
                            }).show();
                        }
                    });
                }
            }
        });
    }

    private void setProduct() {
        API api= Builder.getClient();
        Call<List<Product>> call=api.getAllProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    products=response.body();
                    setAdapater();
                }
                else {
                    Snackbar.make(getView(), "Internal server error", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Snackbar.make(getView(), "Page not found", Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setProduct();
                    }
                }).show();
            }
        });
    }

    public void setAdapater(){
        ArrayList<String> pname= new ArrayList<>();
        int i=0;
        for(Product p:products){
            pname.add(p.getPname());
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,pname);
        edtAutocomplete.setAdapter(adapter);
    }

    public View getView() {
        return findViewById(android.R.id.content);
    }

    public boolean Validation(){
        boolean check=true;
        if(product==null){
            Snackbar.make(getView(), "Please fill the detail", Snackbar.LENGTH_LONG).show();
            check=false;
        }
        if(txtQty.getEditText().getText().toString().isEmpty()){
            txtQty.setError("Please fill the qty");
            check=false;
        }
        else{
            txtQty.setErrorEnabled(false);
        }
        return  check;
    }
}
