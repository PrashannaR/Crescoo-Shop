package com.example.crescooshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.example.crescooshop.R;

public class ViewInv extends AppCompatActivity {

    public String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inv);

        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
    }
}