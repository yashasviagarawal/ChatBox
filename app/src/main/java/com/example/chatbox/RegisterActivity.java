package com.example.chatbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText username,email,pass,confpass;
    Button image_btn,register_btn;
    FirebaseAuth auth;
    DatabaseReference reference;

    String selectedPhotoUrl = "";

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==0 && resultCode== Activity.RESULT_OK && data!=null){
            Log.d("RegisterActivity","photo selected");

            selectedPhotoUrl = data.getData();

            String bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUrl);

            String bitmapDrawable = BitmapDrawable(bitmap)
            val text = ""

            image_btn.setBackgroundDrawable(bitmapDrawable)
            image_btn.text = text

        }

        super.onActivityResult(requestCode, resultCode, data);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toast.makeText(getApplicationContext(),"Welcome to Register Page",Toast.LENGTH_SHORT).show();

        username = findViewById(R.id.username);
        email = findViewById(R.id.regEmailId);
        pass = findViewById(R.id.regpassword);
        confpass = findViewById(R.id.confPass);
        image_btn = findViewById(R.id.image_btn);
        register_btn = findViewById(R.id.register);

        String Email = email.getText().toString();

        String Pass = pass.getText().toString();

        auth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                //intent.setType("image");
                startActivityForResult(intent,0);
            }
        });





        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"All Fields are Mandatory",Toast.LENGTH_SHORT).show();
                    username.setBackgroundResource(R.drawable.alert_background);
                }
                if(email.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"All Fields are Mandatory",Toast.LENGTH_SHORT).show();
                    email.setBackgroundResource(R.drawable.alert_background);
                }
                if(pass.getText().toString().equals("")||pass.getText().toString().length()<6)
                {
                    Toast.makeText(RegisterActivity.this,"All Fields are Mandatory",Toast.LENGTH_SHORT).show();
                    pass.setBackgroundResource(R.drawable.alert_background);
                }
                if(confpass.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"All Fields are Mandatory",Toast.LENGTH_SHORT).show();
                    confpass.setBackgroundResource(R.drawable.alert_background);
                }
                if(pass.getText().toString().equals(confpass.getText().toString()))
                {
                    auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        FirebaseUser firebaseUser = auth.getCurrentUser();
                                        assert firebaseUser != null;
                                        String userid = firebaseUser.getUid();

                                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid).push();

                                        HashMap<String, String> hashmap = new HashMap<>();
                                        hashmap.put("id",userid);
                                        hashmap.put("Username",username.getText().toString());
                                        hashmap.put("Profile Pic","Default");

                                        reference.setValue(hashmap)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful())
                                                        {

                                                            Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                        else
                                                        {
                                                            Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                });

                                    }

                                }
                            });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                    username.setText("");
                    email.setText("");
                    pass.setText("");
                    confpass.setText("");

                }
            }
        });


    }


}