package com.example.mechanicfounder.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mechanicfounder.MainActivity;
import com.example.mechanicfounder.MechanicMainPage;
import com.example.mechanicfounder.R;
import com.google.firebase.auth.FirebaseAuth;

public class Logout_Fragment extends Fragment {

    Button logout_mech;
    FirebaseAuth auth;
    public Logout_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_logout_, container, false);
        logout_mech = view.findViewById(R.id.logout_mech);
        auth = FirebaseAuth.getInstance();

        logout_mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}