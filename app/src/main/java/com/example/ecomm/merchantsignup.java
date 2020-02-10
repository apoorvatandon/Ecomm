package com.example.ecomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class merchantsignup extends AppCompatActivity {
    EditText pass,phone,name,email,vericode;
    Button submit,verify;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;
    PhoneAuthProvider.ForceResendingToken resend;

    private AuthCredential credential;
    String mveriid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchantsignup);
        pass = findViewById(R.id.pass);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        submit = findViewById(R.id.submit);
        vericode=findViewById(R.id.vericode);
        verify=findViewById(R.id.verify);

        auth= FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setVisibility(View.INVISIBLE);
                vericode.setVisibility(View.VISIBLE);
                String phn=phone.getText().toString();
                calling(phn);


            }
        });

     verify.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String code=vericode.getText().toString();
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mveriid,code);
        signInWithPhoneAuthCredential(credential);

       }
      });
        mcallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(merchantsignup.this,"please enter correct phone number",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                mveriid = s;
                resend = forceResendingToken;
                Toast.makeText(merchantsignup.this,"code sent",Toast.LENGTH_SHORT).show();
            }

        };
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i=new Intent(merchantsignup.this,merchantitempage.class);
                            startActivity(i);


                        } else {
                            Toast.makeText(merchantsignup.this,"Error in Signup...Try again",Toast.LENGTH_SHORT).show();

                        }
                    }

                });

    }
    public void calling(String call)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber( call, 60, TimeUnit.SECONDS, this, mcallback);
    }
}
