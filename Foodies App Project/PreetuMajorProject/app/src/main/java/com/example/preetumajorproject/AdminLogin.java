package com.example.preetumajorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
TextView newuser;
EditText userpassword,username;
Button btnAdminLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        newuser=findViewById(R.id.newuser);
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminLogin.this,adminregistration.class);
                startActivity(intent);
            }
        });
//click event ogf login to redirect on admin dashboard
        username=findViewById(R.id.username);
        userpassword=findViewById(R.id.userpassword);
        btnAdminLogin=findViewById(R.id.btnAdminLogin);
        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("StudentDetail");
                Query query=myRef.orderByChild("mobile").equalTo(username.getText().toString());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       // Log.e("logindata",snapshot+"");
                        if(snapshot.exists())
                        {
                            if(snapshot.child(username.getText().toString()).child("password").getValue().toString().equals(userpassword.getText().toString()))
                            {
                                Intent intent=new Intent(AdminLogin.this, AdminDashbard.class);
                                Toast.makeText(AdminLogin.this,"Login Successful",Toast.LENGTH_LONG).show();
                                //All user data is now available in snapshot,then Let's create a ShraedPereference and store alla data of user
                                SharedPreferences shareddata=getSharedPreferences("logindata",MODE_PRIVATE);
                                SharedPreferences.Editor editor=shareddata.edit();
                                editor.putString("address",snapshot.child(username.getText().toString()).child("address").getValue().toString());
                                editor.putString("name",snapshot.child(username.getText().toString()).child("name").getValue().toString());
                                editor.putString("mobile",snapshot.child(username.getText().toString()).child("mobile").getValue().toString());
                                editor.putString("emailid",snapshot.child(username.getText().toString()).child("emailid").getValue().toString());
                                editor.putString("city",snapshot.child(username.getText().toString()).child("city").getValue().toString());
                                editor.putString("password",snapshot.child(username.getText().toString()).child("password").getValue().toString());
                                editor.putString("picture",snapshot.child(username.getText().toString()).child("picture").getValue().toString());
                                editor.commit();
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(AdminLogin.this,"Password not matched",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(AdminLogin.this,"UserId Password is invalid",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}