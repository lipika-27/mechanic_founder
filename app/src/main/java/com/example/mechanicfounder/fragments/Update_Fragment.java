package com.example.mechanicfounder.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mechanicfounder.Model.User;
import com.example.mechanicfounder.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class Update_Fragment extends Fragment {

    TextInputEditText edname,edBname,edemail,edaddress,edstate,edphno;
    Button savebtn;

    FirebaseAuth auth;
    FirebaseDatabase database;

    public Update_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_, container, false);

        edname = view.findViewById(R.id.edname);
        edBname = view.findViewById(R.id.edBname);
        edemail = view.findViewById(R.id.edemail);
        edaddress = view.findViewById(R.id.edaddress);
        edstate = view.findViewById(R.id.edstate);
        edphno = view.findViewById(R.id.edphno);
        savebtn = view.findViewById(R.id.savebtn);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mname = edname.getText().toString();
                String mBname = edBname.getText().toString();
                String memail = edemail.getText().toString();
                String maddress = edaddress.getText().toString();
                String mstate = edstate.getText().toString();
                String mphno = edphno.getText().toString();

                HashMap<String,Object> map = new HashMap<>();
                map.put("userName",mname);
                map.put("BName",mBname);
                map.put("email",memail);
                map.put("address",maddress);
                map.put("state",mstate);
                map.put("phNo",mphno);

                database.getReference().child("Mechanic").child(FirebaseAuth.getInstance().getUid()).updateChildren(map);
                Toast.makeText(getContext(),"Bussiness Update",Toast.LENGTH_SHORT).show();
            }
        });

        database.getReference().child("Mechanic").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                edname.setText(user.getUserName());
                edBname.setText(user.getBName());
                edemail.setText(user.getEmail());
                edaddress.setText(user.getAddress());
                edstate.setText(user.getState());
                edphno.setText(user.getPhNo());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}