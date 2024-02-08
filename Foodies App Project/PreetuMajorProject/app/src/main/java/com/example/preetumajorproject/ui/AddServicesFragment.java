package com.example.preetumajorproject.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.preetumajorproject.R;
import com.example.preetumajorproject.adminregistration;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddServicesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddServicesFragment newInstance(String param1, String param2) {
        AddServicesFragment fragment = new AddServicesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ImageView shopimg;
    Button addimg;
    StorageReference storage;
    public  static String imageurldb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_add_services, container, false);
        EditText stittle=root.findViewById(R.id.stittle);
        EditText shopdesc=root.findViewById(R.id.shopdesc);
        EditText servicecharge=root.findViewById(R.id.servicecharge);
        EditText shopname=root.findViewById(R.id.shopname);
        shopimg=root.findViewById(R.id.shopimg);
        addimg=root.findViewById(R.id.addimg);
        Button btnsave=root.findViewById(R.id.btnsave);
//add imgae onclick button and add pic of shop
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opengallery=new Intent();
                opengallery.setType("image/*");
                opengallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(opengallery,2);
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("ShopDetail");
                if (imageurldb!=null) {
                  SharedPreferences shareddata=getActivity().getSharedPreferences("logindata",MODE_PRIVATE);

                  String name=shareddata.getString("mobile","");
                    HashMap<String, String> sd = new HashMap<>();
                    sd.put("stittle", stittle.getText().toString());
                    sd.put("shopdesc", shopdesc.getText().toString());
                    sd.put("servicecharge", servicecharge.getText().toString());
                    sd.put("shopname", shopname.getText().toString());
                    sd.put("shopimg", imageurldb);
                    sd.put("executiveid",name);
                    myRef.child(shopname.getText().toString()).setValue(sd);
                    Toast.makeText(getActivity(),"File Uploaded Successful",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"Please Wait To Upload Image",Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && data!=null)
        {
         Uri imagepath=data.getData();
        shopimg=getActivity().findViewById(R.id.shopimg);
        shopimg.setImageURI(imagepath);
            //get reference of cloud storage to the StorageReference Object
            storage= FirebaseStorage.getInstance().getReference();
            //move image in the firebase storage
            StorageReference imagereference=storage.child("Shopimg/"+System.currentTimeMillis()+".jpg");
            imagereference.putFile(imagepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //Toast.makeText(AddServicesFragment.this,"File Uploaded",Toast.LENGTH_LONG).show();
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
                  // Toast.makeText(AddServicesFragment.this,"File not Uploaded",Toast.LENGTH_LONG).show();

                }
            }) ;

        }
    }
}