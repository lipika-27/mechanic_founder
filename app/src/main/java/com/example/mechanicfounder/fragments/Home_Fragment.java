package com.example.mechanicfounder.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mechanicfounder.Model.User;
import com.example.mechanicfounder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home_Fragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase database;
    TextView tvname,tvemail,tvBname,tvphno,tvaddress,tvstate,tvavailable;

    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_,container,false);

        tvname = view.findViewById(R.id.tvname);
        tvemail = view.findViewById(R.id.tvemail);
        tvBname = view.findViewById(R.id.tvBname);
        tvphno = view.findViewById(R.id.tvphno);
        tvaddress = view.findViewById(R.id.tvaddress);
        tvstate = view.findViewById(R.id.tvstate);
       // tvavailable = view.findViewById(R.id.tvavailable);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        database.getReference().child("Mechanic").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                tvname.setText(user.getUserName());
                tvemail.setText(user.getEmail());
                tvBname.setText(user.getBName());
                tvphno.setText(user.getPhNo());
                tvaddress.setText(user.getAddress());
                tvstate.setText(user.getState());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}