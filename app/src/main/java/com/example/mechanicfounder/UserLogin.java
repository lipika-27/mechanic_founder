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

import org.w3c.dom.Text;

public class UserLogin extends AppCompatActivity {

    TextView register;
    Button loginbtn;
    EditText email,password;
    ProgressDialog dialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

      //  getSupportActionBar().hide();

        register = findViewById(R.id.textView);
        loginbtn = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);

        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setTitle("LogIn");
        dialog.setMessage("LogIN in to your account");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent = new Intent(UserLogin.this,UserMainPage.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(UserLogin.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        if(auth.getCurrentUser() != null)
        {
            Intent intent = new Intent(UserLogin.this,UserMainPage.class);
            startActivity(intent);
        }

    }

    public void registerUser() {

        Intent intent = new Intent(UserLogin.this,UserRegister.class);
        startActivity(intent);
    }
}