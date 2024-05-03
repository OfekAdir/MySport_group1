package com.example.firebase_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity implements Iview {

    ListView list_users;
    ArrayAdapter <String> adp;
    ArrayList<String>arr;

    private FirebaseDatabase fbase;
    private DatabaseReference myref;
    private User user;
    private FirebaseUtil fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        list_users = findViewById(R.id.list_users);
        fb = FirebaseUtil.getInstance(this);


        arr = new ArrayList();
        adp = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,arr);
        list_users.setAdapter(adp);
        fb.old_users( this);


    }



    @Override
    public void display_message(String msg) {

    }
    @Override
    public ArrayList<String> getArr() {
        return arr;
    }

    @Override
    public void nextScreen() {

    }

    @Override
    public void onDataChange(ArrayList<String> arr) {
        // if the retreived data was in the same page with button pressed, then we needed to clean the list
        // arr.clear();
        adp.notifyDataSetChanged();
        //arr.addAll(arr);
    }
}