package com.example.crescooshop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.crescooshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Billing extends AppCompatActivity {

    public String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        //get value
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        Intent intentTwo = getIntent();
        phone = intentTwo.getStringExtra("phone");

        //navBar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        bottomNavigationView.setSelectedItemId(R.id.bill);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        Intent intent = new Intent(getApplicationContext(), Profile.class);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bill:
                        finish();
                        return true;
                    case R.id.inventory:
                        Intent intentTwo = new Intent(getApplicationContext(), Inventory.class);
                        intentTwo.putExtra("phone", phone);
                        startActivity(intentTwo);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}