package com.example.crescooshop.Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crescooshop.Activities.Home;
import com.example.crescooshop.Constructor.Constructor;
import com.example.crescooshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import java.util.concurrent.TimeUnit;

public class SignupOTP extends AppCompatActivity {

    Button btnNext;
    OtpView otpView;
    TextView resendOTP;

    FirebaseAuth auth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public String shopName, phone, selectedItem, ownerName, location, rb, desc, verificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_otp);

        btnNext = findViewById(R.id.btnNext);
        otpView = findViewById(R.id.otp_view);
        resendOTP = findViewById(R.id.resendOTP);

        //firebase
        auth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();


        //get values
        Intent intent = getIntent();
        shopName = intent.getStringExtra("shopName");
        phone = intent.getStringExtra("phone");
        selectedItem = intent.getStringExtra("selectedItem");
        ownerName = intent.getStringExtra("ownerName");
        location = intent.getStringExtra("location");
        rb = intent.getStringExtra("rb");
        desc = intent.getStringExtra("desc");

        getOTP();
    }

    private void getOTP() {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(SignupOTP.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String verifyId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verifyId, forceResendingToken);
                        verificationID = verifyId;
                    }
                }).build();

        PhoneAuthProvider.verifyPhoneNumber(options);

        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, otp);

                auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            sendData();
                            Intent intent = new Intent(SignupOTP.this, Home.class);
                            startActivity(intent);
                            finishAffinity();
                        }else {
                            Toast.makeText(SignupOTP.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
                    @Override
                    public void onOtpCompleted(String otp) {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, otp);

                        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    sendData();
                                    Intent intent = new Intent(SignupOTP.this, Home.class);
                                    startActivity(intent);
                                    finishAffinity();
                                }else {
                                    Toast.makeText(SignupOTP.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });


        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(SignupOTP.this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                            }

                            @Override
                            public void onCodeSent(@NonNull String newOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(newOTP, forceResendingToken);
                                verificationID = newOTP;
                                Toast.makeText(SignupOTP.this, "Resent", Toast.LENGTH_SHORT).show();
                            }
                        }).build();

                PhoneAuthProvider.verifyPhoneNumber(options);

            }
        });
    }

    private void sendData() {
        reference = rootNode.getReference("shops");
        Constructor constructor = new Constructor(shopName, phone, ownerName, selectedItem, location, rb, desc);
        reference.child(phone).setValue(constructor);
    }
}