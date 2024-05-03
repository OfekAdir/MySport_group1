package com.example.firebase_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.ArrayList;

public class InsertGroupActivity extends AppCompatActivity implements Iview{
    private FirebaseUtil fb;
    private EditText maxPlayersNum;
    private Spinner sportType;
    private DatePicker groupDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_group);
        maxPlayersNum = findViewById(R.id.maxPlayersNum);
        sportType = findViewById(R.id.sportType);
        groupDate = findViewById(R.id.groupDate);

        fb=FirebaseUtil.getInstance(this);
    }

    public void insertGroup(View view) {
        int year = groupDate.getYear();
        int month = groupDate.getMonth();
        int dayOfMonth = groupDate.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        Date date = calendar.getTime();

        // Formatting the date properly
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateGroup = sdf.format(date);

        Group g = new Group(
                fb.getCurrentUser().getUid(),
                dateGroup,
                Integer.parseInt(maxPlayersNum.getText().toString()),
                sportType.getSelectedItem().toString());

        fb.insert_group(g);
    }

    @Override
    public void display_message(String msg) {

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
}