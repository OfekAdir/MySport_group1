package com.example.firebase_example;

import java.util.ArrayList;

public interface Iview {
    public void display_message(String msg);
    public void onDataChange(ArrayList<String>arr);
    public ArrayList<String>getArr();
    public void nextScreen();
}
