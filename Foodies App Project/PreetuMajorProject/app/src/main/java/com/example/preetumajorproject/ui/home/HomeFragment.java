package com.example.preetumajorproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.preetumajorproject.R;
import com.example.preetumajorproject.ui.AddServicesFragment;
import com.example.preetumajorproject.ui.ChangePasswordFragment;
import com.example.preetumajorproject.ui.LoginFragment;
import com.example.preetumajorproject.ui.LogoutFragment;
import com.example.preetumajorproject.ui.MyProfileFragment;
import com.example.preetumajorproject.ui.RegistrationFragment;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        CardView card1,card2,card3,card4,card5,card6;
        card1=root.findViewById(R.id.profile);
        card2=root.findViewById(R.id.registration);
        card3=root.findViewById(R.id.login);
        card4=root.findViewById(R.id.addservice);
        card5=root.findViewById(R.id.password);
        card6=root.findViewById(R.id.logout);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new MyProfileFragment());
                fr.commit();
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new RegistrationFragment());
                fr.commit();
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new LoginFragment());
                fr.commit();
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new AddServicesFragment());
                fr.commit();
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new ChangePasswordFragment());
                fr.commit();
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new LogoutFragment());
                fr.commit();
            }
        });


        return root;
    }
}