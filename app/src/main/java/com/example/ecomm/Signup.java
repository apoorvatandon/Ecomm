package com.example.ecomm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText password,phone,username,email;
    Button register;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
       email=findViewById(R.id.email);
       username=findViewById(R.id.username);
        register=findViewById(R.id.register);
        auth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass=password.getText().toString().trim();
                String phno=phone.getText().toString().trim();
                String eml=email.getText().toString().trim();
                String name=username.getText().toString().trim();
                auth.createUserWithEmailAndPassword(eml,pass);

            }
        });
    }
}
