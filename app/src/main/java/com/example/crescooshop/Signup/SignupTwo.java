package com.example.crescooshop.Signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.crescooshop.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignupTwo extends AppCompatActivity {
    TextInputLayout ownerNameInputLayout, menu, locationInputLayout;
    AutoCompleteTextView dropdown_menu;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView tapLogin;
    Button btnNext;

    public String shopName, phone, selectedItem, ownerName, location;

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
                location = locationInputLayout.getEditText().getText().toString();

                //get Radio Button value
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                String rbValue = (String) radioButton.getText();

                Intent intent = new Intent(SignupTwo.this, SignupDesc.class);
                intent.putExtra("shopName", shopName);
                intent.putExtra("phone", phone);
                intent.putExtra("selectedItem", selectedItem);
                intent.putExtra("ownerName", ownerName);
                intent.putExtra("location", location);
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


}