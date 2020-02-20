package com.rj.watersupply;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    EditText uname,pass;
    TextView btnlogin;
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
                btnlogin.setEnabled(false);
                showCancelDialog();
            }
        });
    }

    private void showCancelDialog() {
        final Dialog dialog = new Dialog(Login.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_cancel);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView t=(TextView)dialog.findViewById(R.id.btnok);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnlogin.setEnabled(true);
                dialog.dismiss();
                Intent i=new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
