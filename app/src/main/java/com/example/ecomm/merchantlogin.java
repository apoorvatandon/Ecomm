package com.example.ecomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class merchantlogin extends AppCompatActivity {
    EditText email, pass;
    Button submit;
    TextView newuser;
    FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchantlogin);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        submit = findViewById(R.id.submit);
        mauth = FirebaseAuth.getInstance();
        newuser=findViewById(R.id.newuser);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(merchantlogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(merchantlogin.this, merchantitempage.class);
                            startActivity(i);
                        } else
                            Toast.makeText(merchantlogin.this, "Wrong credentials!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(merchantlogin.this,merchantsignup.class);
                startActivity(i);
            }
        });

    }
}
