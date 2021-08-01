package com.example.mechanicfounder.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mechanicfounder.Adapters.SampleMechanic;
import com.example.mechanicfounder.Model.User;
import com.example.mechanicfounder.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Mechanic_FinderFragment extends Fragment {

    public Mechanic_FinderFragment() {
        // Required empty public constructor
    }

    ArrayList<User> list = new ArrayList<>();
    FirebaseDatabase database;
    RecyclerView mechanicReyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_mechanic__finder, container, false);

         mechanicReyclerview = view.findViewById(R.id.mechanic_recyclerView);
         database = FirebaseDatabase.getInstance();

        SampleMechanic adapter = new SampleMechanic(list,getContext());
        mechanicReyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mechanicReyclerview.setLayoutManager(layoutManager);

        database.getReference().child("Mechanic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    User user = dataSnapshot.getValue(User.class);
                    user.setMechId(dataSnapshot.getKey());
                    list.add(user);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


         return view;
    }
}