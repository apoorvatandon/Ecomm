package com.example.ecomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class customerlogin extends AppCompatActivity {
    TextView newUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlogin);
        newUser=findViewById(R.id.newUser);

    newUser.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(customerlogin.this,Signup.class);
            startActivity(i);

        }
});
    }}
