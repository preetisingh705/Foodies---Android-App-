package com.example.preetumajorproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class adminregistration extends AppCompatActivity {
    EditText txtname,txtmobile,txtpassword,txtemail,txtaddress,txtcity;
    Button btnregister;
    TextView register;
ImageView chooseimg,uesrimage;
//create a object of storageReference class.this is 1st step
    StorageReference storage;
    public static String imageurldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminregistration);

        //get reference of cloud storage to the StorageReference Object
        storage= FirebaseStorage.getInstance().getReference();

        //open gallary on click of + button
        uesrimage=findViewById(R.id.userimage);
        chooseimg=findViewById(R.id.chooseimg);
        chooseimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opengallery=new Intent();
                opengallery.setType("image/*");
                opengallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(opengallery,1);
            }
        });
        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(adminregistration.this,AdminLogin.class);
                startActivity(in);
            }
        });
        //store data in database on click of register now button
txtname=findViewById(R.id.txtname);
txtmobile=findViewById(R.id.txtmobile);
txtemail=findViewById(R.id.txtemail);
txtaddress=findViewById(R.id.txtaddress);
txtpassword=findViewById(R.id.txtpassword);
txtcity=findViewById(R.id.txtcity);
btnregister=findViewById(R.id.btnregister);
btnregister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // Toast.makeText(adminregistration.this,txtname.getText(),Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("StudentDetail");
        if(imageurldb!=null)
        {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("name",txtname.getText().toString());
        hashMap.put("mobile",txtmobile.getText().toString());
        hashMap.put("emailid",txtemail.getText().toString());
        hashMap.put("address",txtaddress.getText().toString());
        hashMap.put("city",txtcity.getText().toString());
        hashMap.put("password",txtpassword.getText().toString());
        hashMap.put("picture",imageurldb);
        myRef.child(txtmobile.getText().toString()).setValue(hashMap);
        Toast.makeText(adminregistration.this,"Registration Successful",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(adminregistration.this,AdminLogin.class);
        startActivity(intent);
        }
        else
        {
            Toast.makeText(adminregistration.this,"Please wait to image upload",Toast.LENGTH_LONG).show();
        }
    }
});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && data!=null)
        {
            //get path from gallary and display in a ImageView Id is:userimg
          Uri imagepath=data.getData();
          uesrimage.setImageURI(imagepath);

          //move image in the firebase storage
            StorageReference imagereference=storage.child("profilepic/"+System.currentTimeMillis()+".jpg");
           imagereference.putFile(imagepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   Toast.makeText(adminregistration.this,"File Uploaded",Toast.LENGTH_LONG).show();
                   imagereference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                        // Log.e("ImageURL",uri+"");
                           imageurldb=uri.toString();
                       }
                   });
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(adminregistration.this,"File not Uploaded",Toast.LENGTH_LONG).show();

               }
           }) ;


        }
    }
    //ending of activity result function
}