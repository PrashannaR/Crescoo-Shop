package com.example.crescooshop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.crescooshop.Adapter.InventoryAdapter;
import com.example.crescooshop.Constructor.addItemConstructor;
import com.example.crescooshop.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewInv extends AppCompatActivity {

    public String phone;

    RecyclerView recyclerView;
    InventoryAdapter inventoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inv);

        //get value
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");


        recyclerView = findViewById(R.id.inv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //get values from firebase
        FirebaseRecyclerOptions<addItemConstructor> options =
                new FirebaseRecyclerOptions.Builder<addItemConstructor>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("shops").child(phone).child("Inventory"), addItemConstructor.class)
                        .build();


        inventoryAdapter = new InventoryAdapter(options);
        recyclerView.setAdapter(inventoryAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        inventoryAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        inventoryAdapter.stopListening();
    }
}