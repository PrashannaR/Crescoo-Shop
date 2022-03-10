package com.example.crescooshop.Signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.crescooshop.Login.LoginPhone;
import com.example.crescooshop.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignupOne extends AppCompatActivity {
    TextInputLayout shopNameInputLayout, phoneInputLayout;
    CountryCodePicker ccp;
    TextView tapLogin;
    Button btnNext;

    public String shopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_one);

        shopNameInputLayout = findViewById(R.id.shopNameInputLayout);
        phoneInputLayout = findViewById(R.id.phoneInputLayout);

        tapLogin = findViewById(R.id.tapLogin);
        btnNext = findViewById(R.id.btnNext);

        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneInputLayout.getEditText());

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!checkShopName() || !checkPhone()){
                    return;
                }

                shopName = shopNameInputLayout.getEditText().getText().toString();

                Intent intent = new Intent(SignupOne.this, SignupTwo.class);
                intent.putExtra("shopName", shopName);
                intent.putExtra("phone", ccp.getFullNumberWithPlus().replace(" ", ""));
                startActivity(intent);
            }
        });

        tapLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupOne.this, LoginPhone.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private boolean checkShopName() {
        String name = shopNameInputLayout.getEditText().getText().toString();

        if(name.isEmpty()){
            shopNameInputLayout.setError("Enter Your Name");
            return false;
        }else {
            shopNameInputLayout.setError(null);
            return true;
        }
    }

    private boolean checkPhone() {
        String ph = phoneInputLayout.getEditText().getText().toString();

        if(ph.isEmpty()){
            phoneInputLayout.setError("Enter Your Phone Number");
            return false;
        }else {
            phoneInputLayout.setError(null);
            return true;
        }
    }
}