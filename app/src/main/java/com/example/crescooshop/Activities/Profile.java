package com.example.crescooshop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crescooshop.R;
import com.example.crescooshop.Signup.SignupOne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {
    TextView nameTV, phoneTV, shopTV, franchiseTV, locationTV, shopTypeTV, descTV;
    ImageView edit;
    Button btnLogout;
    DatabaseReference databaseReference;
    public String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        nameTV = findViewById(R.id.nameTV);
        phoneTV = findViewById(R.id.phoneTV);
        shopTV = findViewById(R.id.shopTV);
        franchiseTV = findViewById(R.id.franchiseTV);
        locationTV = findViewById(R.id.locationTV);
        shopTypeTV = findViewById(R.id.shopType);
        descTV = findViewById(R.id.descTV);

        edit = findViewById(R.id.edit);
        btnLogout = findViewById(R.id.btnLogout);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://crescoo-53eff-default-rtdb.firebaseio.com/");

        //get value
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        Intent intentTwo = getIntent();
        phone = intentTwo.getStringExtra("phone");


        //navBar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.inventory:
                        // startActivity(new Intent(getApplicationContext(), Profiles.class));
                        Intent intent = new Intent(getApplicationContext(), Inventory.class);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.profile:
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

        getValuesFromDB();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent1 = new Intent(Profile.this, SignupOne.class);
                startActivity(intent1);
                finish();
            }
        });


    }

    private void getValuesFromDB() {
        databaseReference = FirebaseDatabase.getInstance().getReference("shops");
        databaseReference.child(phone).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot= task.getResult();
                        String shopName = String.valueOf(dataSnapshot.child("shopName").getValue());
                        String ownerName = String.valueOf(dataSnapshot.child("ownerName").getValue());
                        String phoneNum = String.valueOf(dataSnapshot.child("phone").getValue());
                        String location = String.valueOf(dataSnapshot.child("shopLocation").getValue());
                        String shopType = String.valueOf(dataSnapshot.child("shopType").getValue());
                        String desc = String.valueOf(dataSnapshot.child("desc").getValue());
                        String franchise = String.valueOf(dataSnapshot.child("franchise").getValue());

                        shopTV.setText(ownerName);
                        nameTV.setText(shopName);
                        phoneTV.setText(phoneNum);
                        locationTV.setText(location);
                        shopTypeTV.setText(shopType);
                        franchiseTV.setText(new StringBuilder().append("Franchise: ").append(franchise).toString());

                        if(desc.equals("")){
                            descTV.setText("Oops You haven't added your pitch yet!");
                        }else {
                            descTV.setText(desc);
                        }

                    }
                }
            }
        });
    }
}