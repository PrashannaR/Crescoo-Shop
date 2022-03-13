package com.example.crescooshop.Signup;

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
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crescooshop.Login.LoginPhone;
import com.example.crescooshop.MainActivity;
import com.example.crescooshop.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SignupTwo extends AppCompatActivity {
    TextInputLayout ownerNameInputLayout, menu, locationInputLayout;
    AutoCompleteTextView dropdown_menu;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView tapLogin;
    Button btnNext;

    public String shopName, phone, selectedItem, ownerName, location, myLocation;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_two);

        ownerNameInputLayout = findViewById(R.id.nameInputLayout);
        menu = findViewById(R.id.menu);
        locationInputLayout = findViewById(R.id.locationInputLayout);

        radioGroup = findViewById(R.id.radioGroup);

        dropdown_menu = findViewById(R.id.dropdown_menu);

        tapLogin = findViewById(R.id.tapLogin);
        btnNext = findViewById(R.id.btnNext);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        checkLocationPermission();

        //get values
        Intent intent = getIntent();
        shopName = intent.getStringExtra("shopName");
        phone = intent.getStringExtra("phone");

        //dropdown
        String[] items = {"type1", "type2", "type3"};
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(SignupTwo.this, R.layout.drop_down_list, items);
        dropdown_menu.setAdapter(itemAdapter);

        dropdown_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = itemAdapter.getItem(i);
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ownerName = ownerNameInputLayout.getEditText().getText().toString();
               // location = locationInputLayout.getEditText().getText().toString();

                //get Radio Button value
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                String rbValue = (String) radioButton.getText();

                Intent intent = new Intent(SignupTwo.this, SignupDesc.class);
                intent.putExtra("shopName", shopName);
                intent.putExtra("phone", phone);
                intent.putExtra("selectedItem", selectedItem);
                intent.putExtra("ownerName", ownerName);
                intent.putExtra("location", myLocation);
                intent.putExtra("rb", rbValue);

                startActivity(intent);


            }
        });

        tapLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupTwo.this, LoginPhone.class);
                startActivity(intent);
            }
        });


    }

    private void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(SignupTwo.this,
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
            ActivityCompat.requestPermissions(SignupTwo.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 444);
        }
    }



}