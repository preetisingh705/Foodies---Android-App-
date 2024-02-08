package com.example.preetumajorproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

public class StudentRegister extends AppCompatActivity {
    TextView registeruser;
    ImageView chooseimg, uesrimage;
    StorageReference storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        //get reference of cloud storage to the StorageReference Object
        storage = FirebaseStorage.getInstance().getReference();

        //open gallary on click of + button
        uesrimage = findViewById(R.id.userimage);
        chooseimg = findViewById(R.id.chooseimg);
        chooseimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opengallery = new Intent();
                opengallery.setType("image/*");
                opengallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(opengallery, 1);
            }
        });

        registeruser = findViewById(R.id.registeruser);
        registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(StudentRegister.this, StudentLogin.class);
                startActivity(in);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            //get path from gallary and display in a ImageView Id is:userimg
            Uri imagepath = data.getData();
            uesrimage.setImageURI(imagepath);

            //move image in the firebase storage
            StorageReference imagereference = storage.child("profilepic/" + System.currentTimeMillis() + ".jpg");
            imagereference.putFile(imagepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(StudentRegister.this, "File Uploaded", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(StudentRegister.this, "File not Uploaded", Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}