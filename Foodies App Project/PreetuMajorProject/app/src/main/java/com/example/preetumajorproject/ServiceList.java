package com.example.preetumajorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceList extends AppCompatActivity {
RecyclerView recycle;
dbadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ShopDetail");
        recycle = findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        FirebaseRecyclerOptions<dbmodel> options =
                new FirebaseRecyclerOptions.Builder<dbmodel>()
                        .setQuery(myRef, dbmodel.class)
                        .build();
        adapter = new dbadapter(options);
        recycle.setAdapter(adapter);
    }
        @Override
        protected void onStart() {
            super.onStart();
            adapter.startListening();
        }
        @Override
        protected void onStop() {
            super.onStop();
            adapter.stopListening();*/
        }
    }
