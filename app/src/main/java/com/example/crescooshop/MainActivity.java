package com.example.crescooshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.crescooshop.Activities.Inventory;
import com.example.crescooshop.Activities.LoginOrSignUp;
import com.example.crescooshop.Signup.SignupOne;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();

    FirebaseAuth auth;
    DatabaseReference databaseReference;
    public String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://crescoo-53eff-default-rtdb.firebaseio.com/");

        if(auth.getCurrentUser() != null){
            phone = auth.getCurrentUser().getPhoneNumber();

        }

        //logs in if there is a logged in account
        if (auth.getCurrentUser() != null){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, Inventory.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();
                    //Toast.makeText(MainActivity.this, phone, Toast.LENGTH_SHORT).show();
                }
            }, 3000);
        }else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginOrSignUp.class);
                    startActivity(intent);
                    finish();
                    //Toast.makeText(MainActivity.this, "Nil", Toast.LENGTH_SHORT).show();
                }
            }, 3000);
        }
    }
}