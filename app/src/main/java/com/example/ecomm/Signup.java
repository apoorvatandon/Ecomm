package com.example.ecomm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.security.AuthProvider;
import java.util.concurrent.TimeUnit;

public class Signup extends AppCompatActivity {
    EditText pass,phone,name,email,vericode;
    Button submit,verify;
    FirebaseAuth auth;
     PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;
     PhoneAuthProvider.ForceResendingToken resend;


    String mveriid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        pass = findViewById(R.id.pass);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        submit = findViewById(R.id.submit);
        vericode=findViewById(R.id.vericode);
        verify=findViewById(R.id.verify);

        String eml = email.getText().toString().trim();
        String nam = name.getText().toString().trim();
        String pas = pass.getText().toString();

        auth=FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vericode.setVisibility(View.VISIBLE);
                String phn= String.valueOf(phone.getText());
                calling(phn);  // OnVerificationStateChangedCallbacks
            }
        });


       mcallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
           @Override
           public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

               signInWithPhoneAuthCredential(phoneAuthCredential);
           }

           @Override
           public void onVerificationFailed(@NonNull FirebaseException e) {
               Toast.makeText(Signup.this,"please enter correct phone number",Toast.LENGTH_SHORT).show();

           }

           @Override
           public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
               super.onCodeSent(s, forceResendingToken);
               mveriid = s;
               resend = forceResendingToken;
               Toast.makeText(Signup.this,"code sent",Toast.LENGTH_SHORT).show();
              Intent i=new Intent(Signup.this,otp.class);
              startActivityForResult(i,3);


           }


       };

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i=new Intent(Signup.this,itemdisplaypage.class);
                            startActivity(i);


                        } else {
                            Toast.makeText(Signup.this,"Error in Signup...Try again",Toast.LENGTH_SHORT).show();

                        }
                    }

                });
    }
    public void calling(String call)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber( call, 60, TimeUnit.SECONDS, this, mcallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==3) {
            if (resultCode == RESULT_OK) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mveriid, data.getStringExtra("otp"));
                signInWithPhoneAuthCredential(credential);


            }
        }}}
