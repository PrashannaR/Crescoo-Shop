package com.example.crescooshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crescooshop.Constructor.addItemConstructor;
import com.example.crescooshop.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItem extends AppCompatActivity {
    TextInputLayout productNameInputLayout, quantityInputLayout, cpInputLayout, spInputLayout;
    Button btnAddItem;
    public  String phone;
    public String prodName, quantity, cp, sp;

    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //get values
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        productNameInputLayout = findViewById(R.id.productNameInputLayout);
        quantityInputLayout = findViewById(R.id.quantityInputLayout);
        cpInputLayout = findViewById(R.id.cpInputLayout);
        spInputLayout = findViewById(R.id.spInputLayout);

        btnAddItem = findViewById(R.id.btnAddItem);

        reference = FirebaseDatabase.getInstance().getReference("shops");

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!checkProdName() || !checkQuantity() || !checkCP() || !checkSP()){
                    return;
                }

                addItem();
                Toast.makeText(AddItem.this, "Item Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Inventory.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            }
        });

    }

    private boolean checkSP() {
        sp = spInputLayout.getEditText().getText().toString();

        if(sp.isEmpty()){
            spInputLayout.setError("Please Enter The Selling Price");
            return false;
        }else {
            spInputLayout.setError(null);
            return true;
        }

    }

    private boolean checkCP() {
        cp = cpInputLayout.getEditText().getText(). toString();
        if(cp.isEmpty()){
            cpInputLayout.setError("Please Enter The Cost Price");
            return false;
        }else {
            cpInputLayout.setError(null);
            return true;
        }
    }

    private boolean checkQuantity() {
        quantity = quantityInputLayout.getEditText().getText().toString();
        if(quantity.isEmpty()){
            quantityInputLayout.setError("Please Enter The Quantity");
            return false;
        }else {
            quantityInputLayout.setError(null);
            return true;
        }
    }

    private boolean checkProdName() {
        prodName = productNameInputLayout.getEditText().getText().toString();

        if(prodName.isEmpty()){
            productNameInputLayout.setError("Please Enter The product Name");
            return false;
        }else {
            productNameInputLayout.setError(null);
            return true;
        }
    }

    private void addItem() {
        prodName = productNameInputLayout.getEditText().getText().toString();
        quantity = quantityInputLayout.getEditText().getText().toString();
        cp = cpInputLayout.getEditText().getText(). toString();
        sp = spInputLayout.getEditText().getText().toString();

        addItemConstructor addItemConstructor = new addItemConstructor(prodName, quantity, cp, sp);
        reference.child(phone).child("Inventory").child(prodName).setValue(addItemConstructor);
        Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();




    }
}