package com.example.mechanicfounder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mechanicfounder.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MechanicDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth auth;
    TextView tvname,tvBname,tvaddress,tvstate,tvphno,tvemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_details);

        tvname = findViewById(R.id.tvname);
        tvBname = findViewById(R.id.tvBname);
        tvaddress = findViewById(R.id.tvaddress);
        tvstate = findViewById(R.id.tvstate);
        tvphno = findViewById(R.id.tvphno);
        tvemail = findViewById(R.id.tvemail);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        String mechanicId = getIntent().getStringExtra("mechId");
        String mechBname = getIntent().getStringExtra("BNmae");
        String mechName = getIntent().getStringExtra("userName");
       // String address = getIntent().getStringExtra("address");
        String state = getIntent().getStringExtra("state");


        tvBname.setText(mechBname);
        tvname.setText(mechName);
       // tvaddress.setText(address);
        tvstate.setText(state);

        database.getReference().child("Mechanic").child(mechanicId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                tvphno.setText(user.getPhNo());
                tvemail.setText(user.getEmail());
                tvaddress.setText(user.getAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}