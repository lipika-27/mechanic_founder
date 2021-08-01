package com.example.mechanicfounder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mechanicfounder.Model.User;
import com.example.mechanicfounder.fragments.Home_Fragment;
import com.example.mechanicfounder.fragments.Mechanic_FinderFragment;
import com.example.mechanicfounder.fragments.User_settingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserMainPage extends AppCompatActivity {

    BottomNavigationView  btn;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

//        getSupportActionBar().hide();

        toolbar = findViewById(R.id.toolbar);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                toolbar.setTitle("Hello  " + user.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.user_frmlayout,new Mechanic_FinderFragment()).commit();
        btn = findViewById(R.id.bottomnav);
        btn.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Fragment temp = null ;
                switch (item.getItemId())
                {
                    case R.id.search_mechanic : temp = new Mechanic_FinderFragment();
                    break;
                    case R.id.setting_user : temp = new User_settingFragment();
                    break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.user_frmlayout,temp).commit();
                finish();
            }
        });
    }
}