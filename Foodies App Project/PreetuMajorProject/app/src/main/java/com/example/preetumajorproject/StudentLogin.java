package com.example.preetumajorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StudentLogin extends AppCompatActivity {
TextView forgotpass,userregister1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        forgotpass=findViewById(R.id.forgot);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentLogin.this,ForgotPassword.class);
                startActivity(intent);
            }
        });
        userregister1=findViewById(R.id.userregister1);
        userregister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentLogin.this,StudentRegister.class);
                startActivity(intent);
            }
        });



    }
}