package com.example.firebase_example;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

public class FirebaseUtil {
    private FirebaseDatabase fbase;
    private FirebaseAuth mAuth;
    private static FirebaseUser currentUser;
    DatabaseReference mRef;
    static Iview viewer;
    static FirebaseUtil instance=null;


    private FirebaseUtil(Iview viewer) {
        this.fbase = FirebaseDatabase.getInstance("https://mysportgroup-3fdf8-default-rtdb.firebaseio.com");
        this.mAuth = FirebaseAuth.getInstance();
       



    }
     static FirebaseUtil getInstance(Iview v)
     {
         if(instance==null)
             instance = new FirebaseUtil(v);
         viewer = v;   // you cant do it on the constructor , cause it will take the iviewer of the mainActivity always

             return instance;
     }


    public FirebaseDatabase getFbase() {
        return fbase;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public DatabaseReference getmRef() {
        return mRef;
    }

    public void insert_group(Group g) {
        mRef = fbase.getReference("group/").push();
        mRef.setValue(g);
    }

    public void register_user(String email, String pass, int age , String address , String sports) {



        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user;
                            currentUser = mAuth.getCurrentUser();
                            mRef= fbase.getReference("user/" + currentUser.getUid());
                            user = new User(email, pass, age , address , sports);
                            mRef.setValue(user);
                            viewer.display_message("added successfully");
                            viewer.nextScreen();


                        } else
                            viewer.display_message("auth failed.");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("error",e.getMessage());
                    }
                });
    }

    public void login_user(String email, String pass) {




        mAuth.signInWithEmailAndPassword(email, pass).
                addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            currentUser = mAuth.getCurrentUser();

                            viewer.nextScreen();
                        } else {
                            viewer.display_message("bad");

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("error",e.getMessage());
                    }
                });;

    }

    public void get_Age() {
        mRef=fbase.getReference("user/" + this.currentUser.getUid());

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User user = snapshot.getValue(User.class);

                viewer.display_message("welcome, your age is " + user.getAge());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void old_users(Iview viewer)
    {
        ArrayList<String> arr = viewer.getArr();
        mRef=fbase.getReference("user");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("****** on data change");
                User user;
                for (DataSnapshot keynode: snapshot.getChildren()) {
                    user = keynode.getValue(User.class);
                    if(user.getAge()>40)

                        arr.add(user.getEmail());

                }
                viewer.onDataChange(arr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("*************** cancled");
            }

        });

    }
}
