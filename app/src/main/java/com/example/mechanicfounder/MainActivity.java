package com.example.mechanicfounder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button userbutton,mechanicbutton;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userbutton = findViewById(R.id.userbutton);
        mechanicbutton = findViewById(R.id.mechanicbutton);

        auth = FirebaseAuth.getInstance();

        userbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    public void userLogin() {

        Intent intent = new Intent(MainActivity.this,UserLogin.class);
        startActivity(intent);
    }

    public void mechanicLogin(View view) {
        Intent intent = new Intent(MainActivity.this,MechanicLogin.class);
        startActivity(intent);
    }

}