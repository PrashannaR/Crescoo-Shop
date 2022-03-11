package com.example.crescooshop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.crescooshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Inventory extends AppCompatActivity {

    Button btnAddItem, btnView;
    public String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //get value
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        Intent intentTwo = getIntent();
        phone = intentTwo.getStringExtra("phone");

        btnView = findViewById(R.id.btnView);
        btnAddItem = findViewById(R.id.btnAddItem);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inventory.this, AddItem.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewInv.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });


        //navBar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        bottomNavigationView.setSelectedItemId(R.id.inventory);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        Intent intent = new Intent(getApplicationContext(), Profile.class);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.inventory:
                        finish();
                        return true;
                    case R.id.bill:
                        Intent intentTwo = new Intent(getApplicationContext(), Billing.class);
                        intentTwo.putExtra("phone", phone);
                        startActivity(intentTwo);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

    }
}