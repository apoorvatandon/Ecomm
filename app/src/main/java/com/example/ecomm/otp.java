package com.example.ecomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class otp extends AppCompatActivity {
    EditText otp;
    Button veri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otp=findViewById(R.id.otp);
        veri=findViewById(R.id.veri);
        veri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.putExtra("otp",otp.getText().toString());
                setResult(RESULT_OK,i);
                otp.this.finish();
            }
        });
    }
}
