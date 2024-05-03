package com.example.firebase_example;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements Iview {

    private EditText et_email, et_password;
    private FirebaseUtil fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fb = FirebaseUtil.getInstance(this);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

    }

    public void login(View view) {
        String email = et_email.getText().toString();
        String pass = et_password.getText().toString();
        fb.login_user(email , pass);


    }




    @Override
    public void display_message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataChange(ArrayList<String> arr) {

    }

    @Override
    public ArrayList<String> getArr() {
        return null;
    }

    @Override
    public void nextScreen() {
        Intent help = new Intent(this , HomeActivity.class);
        startActivity(help);
        finish();
    }
}