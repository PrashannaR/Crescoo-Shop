package com.example.crescooshop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.crescooshop.R;
import com.example.crescooshop.Signup.SignupDesc;
import com.example.crescooshop.Signup.SignupOTP;
import com.example.crescooshop.Signup.SignupTwo;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class EditProfile extends AppCompatActivity {

    TextInputLayout shopNameInputLayout, nameInputLayout, menu, locationInputLayout, descInputLayout;
    AutoCompleteTextView dropdown_menu;
    Button btnUpdate;
    RadioGroup radioGroup, radioGroupOne;
    RadioButton radioButton, radioButtonOne;

    public String phone, ShopName, ownerName, location, shopType, franchise, selectedItem, rbValue, desc, rbValueOne;
    public String  uShopName, uOwnerName, uLocation, uFranchise, uDesc, myLocation;

    DatabaseReference reference;
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //get Intent
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        ShopName = intent.getStringExtra("shopName");
        ownerName = intent.getStringExtra("ownerName");
        location = intent.getStringExtra("location");
        shopType = intent.getStringExtra("shopType");
        franchise = intent.getStringExtra("franchise");
        desc = intent.getStringExtra("desc");


        shopNameInputLayout = findViewById(R.id.shopNameInputLayout);
        nameInputLayout = findViewById(R.id.nameInputLayout);
        menu = findViewById(R.id.menu);
        locationInputLayout = findViewById(R.id.locationInputLayout);
        descInputLayout = findViewById(R.id.descInputLayout);
        radioGroupOne = findViewById(R.id.radioGroupOne);
        btnUpdate = findViewById(R.id.btnUpdate);

        radioGroup = findViewById(R.id.radioGroup);

        reference = FirebaseDatabase.getInstance().getReference("shops");

        dropdown_menu = findViewById(R.id.dropdown_menu);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        checkLocationPermission();


        //dropdown
        String[] items = {"type1", "type2", "type3"};
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(EditProfile.this, R.layout.drop_down_list, items);
        dropdown_menu.setAdapter(itemAdapter);

        dropdown_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = itemAdapter.getItem(i);
            }
        });

        //set Values
        menu.getEditText().setText(shopType);
        shopNameInputLayout.getEditText().setText(ShopName);
        nameInputLayout.getEditText().setText(ownerName);
        locationInputLayout.getEditText().setText(location);
        descInputLayout.getEditText().setText(desc);
        selectedItem = shopType;




        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                 rbValue = (String) radioButton.getText();

                int radioIDOne = radioGroup.getCheckedRadioButtonId();
                radioButtonOne = findViewById(radioIDOne);
                rbValueOne = (String) radioButton.getText();


                update();
                Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();



            }

            private void update() {
                //public String uPhone, uShopName, uOwnerName, uLocation, uShopType, uFranchise;

                uShopName = shopNameInputLayout.getEditText().getText().toString();
                uOwnerName = nameInputLayout.getEditText().getText().toString();
                uLocation = locationInputLayout.getEditText().getText().toString();
                uFranchise = rbValue;
                uDesc = descInputLayout.getEditText().getText().toString();

                reference.child(phone).child("desc").setValue(uDesc);

                if (rbValueOne.equals("Yes")){
                    reference.child(phone).child("desc").setValue(uDesc);
                    reference.child(phone).child("franchise").setValue(uFranchise);
                    reference.child(phone).child("ownerName").setValue(uOwnerName);
                    reference.child(phone).child("shopLocation").setValue(myLocation);
                    reference.child(phone).child("shopName").setValue(uShopName);
                    reference.child(phone).child("shopType").setValue(selectedItem);

                }else{
                    reference.child(phone).child("desc").setValue("");
                    reference.child(phone).child("franchise").setValue(uFranchise);
                    reference.child(phone).child("ownerName").setValue(uOwnerName);
                    reference.child(phone).child("shopLocation").setValue(myLocation);
                    reference.child(phone).child("shopName").setValue(uShopName);
                    reference.child(phone).child("shopType").setValue(selectedItem);

                }





            }
        });


    }

    private void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(EditProfile.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location loc = task.getResult();
                    if(loc != null){
                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        List<Address> addresses = null;
                        try {
                            addresses = geocoder.getFromLocation(
                                    loc.getLatitude(), loc.getLongitude(),1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //gets the address in non longitudinal/latitudinal form
                        myLocation = addresses.get(0).getAddressLine(0);
                        locationInputLayout.getEditText().setText(myLocation);


                    }
                }
            });


        } else {
            ActivityCompat.requestPermissions(EditProfile.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 444);
        }
    }

}