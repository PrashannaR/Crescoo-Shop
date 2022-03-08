package com.example.crescooshop.Signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.crescooshop.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignupDesc extends AppCompatActivity {
    TextInputLayout descInputLayout;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnNext;

    public String shopName, phone, selectedItem, ownerName, location, rb, desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_desc);

        descInputLayout = findViewById(R.id.descInputLayout);
        radioGroup = findViewById(R.id.radioGroup);

        btnNext = findViewById(R.id.btnNext);

        // get Values
        Intent intent = getIntent();
        shopName = intent.getStringExtra("shopName");
        phone = intent.getStringExtra("phone");
        selectedItem = intent.getStringExtra("selectedItem");
        ownerName = intent.getStringExtra("ownerName");
        location = intent.getStringExtra("location");
        rb = intent.getStringExtra("rb");

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get Radio Button value
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                String rbValue = (String) radioButton.getText();

                if (rbValue.equals("Yes")){
                    desc = descInputLayout.getEditText().getText().toString();
                    Intent intent = new Intent(SignupDesc.this, SignupOTP.class);
                    intent.putExtra("shopName", shopName);
                    intent.putExtra("phone", phone);
                    intent.putExtra("selectedItem", selectedItem);
                    intent.putExtra("ownerName", ownerName);
                    intent.putExtra("location", location);
                    intent.putExtra("rb", rb);
                    intent.putExtra("desc", desc);
                    startActivity(intent);

                }else{
                    desc = descInputLayout.getEditText().getText().toString();
                    Intent intent = new Intent(SignupDesc.this, SignupOTP.class);
                    intent.putExtra("shopName", shopName);
                    intent.putExtra("phone", phone);
                    intent.putExtra("selectedItem", selectedItem);
                    intent.putExtra("ownerName", ownerName);
                    intent.putExtra("location", location);
                    intent.putExtra("rb", rb);
                    intent.putExtra("desc", "");
                    startActivity(intent);

                }
            }
        });
    }
}