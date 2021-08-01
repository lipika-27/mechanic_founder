package com.example.mechanicfounder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mechanicfounder.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegister extends AppCompatActivity {

    EditText userName,email,password;
    Button button;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        userName = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        button = findViewById(R.id.button);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Creating Account");
        dialog.setMessage("we are creating your account");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userName.getText().toString().isEmpty())
                {
                    userName.setText("enter your name");
                    return;
                }

                if(email.getText().toString().isEmpty())
                {
                    email.setText("enter your email");
                    return;
                }
                if(password.getText().toString().isEmpty())
                {
                    password.setText("enter a password");
                    return;
                }
                dialog.show();
                auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                dialog.dismiss();
                                if(task.isSuccessful())
                                {
                                    User user = new User(userName.getText().toString(),email.getText().toString(),password.getText().toString());
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(user);
                                    Intent intent = new Intent(UserRegister.this,UserMainPage.class);
                                    startActivity(intent);
                                    Toast.makeText(UserRegister.this,"User created Successfully",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(UserRegister.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}