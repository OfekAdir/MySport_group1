package com.example.firebase_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements Iview {
    private User user;
    EditText et_email , et_password , et_age , et_address;
    FirebaseUtil fb;
    CheckBox []arr = new CheckBox[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_age = findViewById(R.id.et_age);
        et_address = findViewById(R.id.et_address);
        for(int i=0;i<arr.length;i++)
        {
            int id = getResources().getIdentifier("ch"+i , "id" , getPackageName());
            arr[i] = findViewById(id);
        }

        fb = FirebaseUtil.getInstance(this);
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

    public void saveData(View view) {
        String email = et_email.getText().toString();
        String pass = et_password.getText().toString();
        int age = Integer.parseInt(et_age.getText().toString());
        String address = et_address.getText().toString();
        String sp = "";

        for(int i=0;i<arr.length;i++)
        {
            if(arr[i].isChecked())
                sp+=arr[i].getText().toString()+",";
        }
        fb.register_user(email , pass , age , address , sp);

    }
}