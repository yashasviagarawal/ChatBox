package com.example.chatbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText email,pass;
    Button login_btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.EmailId);
        pass = findViewById(R.id.password);
        login_btn = findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();

        Toast.makeText(getApplicationContext(),"Welcome to Login Page",Toast.LENGTH_SHORT).show();

       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"All Fields are Mandatory",Toast.LENGTH_SHORT).show();
                    email.setBackgroundResource(R.drawable.alert_background);
                }
                if(pass.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"All Fields are Mandatory",Toast.LENGTH_SHORT).show();
                    pass.setBackgroundResource(R.drawable.alert_background);
                }

                if(email.getText().toString().length()!=0 && pass.getText().toString().length()>=6)
                {
                    auth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {

                                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                        email.setText("");
                                        pass.setText("");
                                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Authentication Failed!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });

    }



}