package com.example.chatbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartActivity extends AppCompatActivity {
    Button register,login;
    FirebaseUser user;
    DatabaseReference database;

   /* @Override
    protected void onStart() {
        super.onStart();
        if(user!=null)
        {
            Intent intent = new Intent(StartActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"Thanks for choosing this chatbox ",Toast.LENGTH_SHORT).show();

        register=findViewById(R.id.Go_to_register);
        login=findViewById(R.id.Go_to_login);
        user = FirebaseAuth.getInstance().getCurrentUser();
//        database = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}