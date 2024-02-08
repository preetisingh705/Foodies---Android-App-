package com.example.preetumajorproject;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import static java.util.logging.Level.parse;

public class dbadapter extends FirebaseRecyclerAdapter<dbmodel,dbadapter.myviewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public dbadapter(@NonNull FirebaseRecyclerOptions<dbmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull dbmodel dbmodel) {
myviewholder.stittle.setText(dbmodel.getStittle());
myviewholder.shopdesc.setText(dbmodel.getShopdesc());
myviewholder.shopname.setText(dbmodel.getShopname());
myviewholder.servicecharge.setText(dbmodel.getServicecharge());
Glide.with(myviewholder.shopimg.getContext()).load(Uri.parse(dbmodel.getShopimg())).into(myviewholder.shopimg);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sampleshopdesign,parent,false);
        return new myviewholder(view);
    }

    class  myviewholder extends RecyclerView.ViewHolder{
        TextView stittle,shopdesc,servicecharge,shopname;
        ImageView shopimg;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
           stittle=itemView.findViewById(R.id.stittle);
           shopdesc=itemView.findViewById(R.id.shopdesc);
           servicecharge=itemView.findViewById(R.id.servicecharge);
           shopname=itemView.findViewById(R.id.shopname);
           shopimg=itemView.findViewById(R.id.shopimg);
        }
    }
}
