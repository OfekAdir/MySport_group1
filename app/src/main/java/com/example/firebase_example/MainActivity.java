package com.example.firebase_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Iview {


    FirebaseUtil fb;
    TextView cuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fb = FirebaseUtil.getInstance(this);
        cuser = findViewById(R.id.cuser);
        displayUser();

    }


    public void displayUser()
    {
        if(fb.getmAuth().getCurrentUser()!=null)
            cuser.setText(fb.getmAuth().getCurrentUser().getEmail());
        else
            cuser.setText("no user connected");
    }

    public void goto_register(View view) {

        ////TODO TODO CHANGE START ACTIVITY FOR RESULT
      Intent intent = new Intent(this , RegisterActivity.class);
        startActivity(intent);



    }

    public void goto_login(View view) {
        Intent intent = new Intent(this , LoginActivity.class);
        startActivity(intent);
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

    }


    public void goto_show_data(View view) {
        Intent intent = new Intent(this , ShowDataActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        fb.getmAuth().signOut();
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        displayUser();

    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUser();
    }
}