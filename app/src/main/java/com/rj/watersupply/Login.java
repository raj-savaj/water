package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rj.watersupply.WebApi.API;
import com.rj.watersupply.WebApi.Builder;
import com.rj.watersupply.modal.ResponseLogin;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText uname,pass;
    TextView btnlogin;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=findViewById(R.id.uname);
        pass=findViewById(R.id.pass);
        btnlogin=findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        btnlogin.setEnabled(false);
        API api= Builder.getClient();
        Call<ResponseLogin> call=api.checkLogin(uname.getText().toString(),pass.getText().toString());
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                btnlogin.setEnabled(true);
                if(response.isSuccessful()) {
                    if(response.body().getStatus()==200){
                        Toast.makeText(Login.this, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        ShowDialog();
                    }
                }
                else{
                    Toast.makeText(Login.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(Login.this, "Page Not Found ", Toast.LENGTH_SHORT).show();
                Log.e("MSG",""+t.getMessage());
                btnlogin.setEnabled(true);
            }
        });
    }

    public void ShowDialog() {
        dialog = new Dialog(Login.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_cancel);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView t = (TextView) dialog.findViewById(R.id.btnok);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                pass.setText(null);
            }
        });
    }
}
