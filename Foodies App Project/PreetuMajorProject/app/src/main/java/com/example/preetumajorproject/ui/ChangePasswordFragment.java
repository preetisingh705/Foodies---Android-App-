package com.example.preetumajorproject.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preetumajorproject.AdminDashbard;
import com.example.preetumajorproject.AdminLogin;
import com.example.preetumajorproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePasswordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangePasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangePasswordFragment newInstance(String param1, String param2) {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_change_password, container, false);
        EditText npass=root.findViewById(R.id.npass);
        EditText cpass=root.findViewById(R.id.cpass);
        Button changepass=root.findViewById(R.id.changepass);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        String newpass=npass.getText().toString();
        String cpassword=cpass.getText().toString();
        if (newpass.equals(cpassword))
        {
           FirebaseDatabase database=FirebaseDatabase.getInstance();
            DatabaseReference myRef=database.getReference("StudentDetail");
            SharedPreferences shareddata=getActivity().getSharedPreferences("logindata",MODE_PRIVATE);
            String mobile=shareddata.getString("mobile","");
            myRef.child(mobile).child("password").setValue(newpass);
        Toast.makeText(getActivity(),"Password Changed",Toast.LENGTH_LONG).show();
            Intent in=new Intent(getActivity(), AdminDashbard.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);

        }
        else
        {
            Toast.makeText(getActivity(),"Password and Confirm Password not Matched.",Toast.LENGTH_LONG).show();
        }
            }
        });
        return root;
    }
}