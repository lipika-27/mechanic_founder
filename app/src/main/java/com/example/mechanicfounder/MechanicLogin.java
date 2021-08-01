package com.example.mechanicfounder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MechanicLogin extends AppCompatActivity {

    EditText email,password;
    Button login;
    FirebaseAuth auth;
    TextView textView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.login);
        textView = findViewById(R.id.textView);

        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setTitle("LogIn");
        dialog.setMessage("login into your account");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MechanicLogin.this,MechanicRegister.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty())
                {
                    email.setText("enter your email");
                    return;
                }
                if(password.getText().toString().isEmpty())
                {
                    password.setText("enter your password");
                    return;
                }
                dialog.show();
                auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                dialog.dismiss();
                                if(task.isSuccessful())
                                {
                                    Intent intent = new Intent(MechanicLogin.this,MechanicMainPage.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(MechanicLogin.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        if(auth.getCurrentUser() != null)
        {
            Intent intent = new Intent(MechanicLogin.this,UserMainPage.class);
            startActivity(intent);
        }


    }
}