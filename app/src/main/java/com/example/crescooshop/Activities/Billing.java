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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        //navBar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        bottomNavigationView.setSelectedItemId(R.id.bill);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        // startActivity(new Intent(getApplicationContext(), Profiles.class));
                        Intent intent = new Intent(getApplicationContext(), Profile.class);
                        //intent.putExtra("phone", phone);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bill:
                        return true;
                    case R.id.inventory:
                        Intent intentTwo = new Intent(getApplicationContext(), Inventory.class);
                        startActivity(intentTwo);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}