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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MechanicRegister extends AppCompatActivity {

    EditText username,email,password;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Button register;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        register = findViewById(R.id.button);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Register");
        progressDialog.setMessage("we are creating your account");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().isEmpty())
                {
                    username.setText("enter your name");
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
                progressDialog.show();
                auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful())
                                {
                                    User user = new User(username.getText().toString(),email.getText().toString(),password.getText().toString());
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Mechanic").child(id).setValue(user);
                                    Intent intent = new Intent(MechanicRegister.this,MechanicMainPage.class);
                                    startActivity(intent);
                                    Toast.makeText(MechanicRegister.this,"account created successfully",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(MechanicRegister.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}