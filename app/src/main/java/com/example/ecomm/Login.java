package com.example.ecomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    Button merchant,customer;
    TextView newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        merchant=findViewById(R.id.merchant);
        customer=findViewById(R.id.customer);
        newUser=findViewById(R.id.newUser);
        merchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,merchantlogin.class);
                startActivity(i);

            }
        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,customerlogin.class);
                startActivity(i);

            }
        });
          newUser.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

              }
          });
    }
}
