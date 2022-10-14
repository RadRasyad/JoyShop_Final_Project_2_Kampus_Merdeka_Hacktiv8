package com.hacktiv8.joyshop.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktiv8.joyshop.databinding.ActivityMainBinding;
import com.hacktiv8.joyshop.ui.admin.AdminLoginActivity;
import com.hacktiv8.joyshop.ui.staff.StaffLoginActivity;
import com.hacktiv8.joyshop.ui.user.RegisterUserActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        binding.btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterUserActivity.class);
            startActivity(intent);
        });

        binding.btnLogin.setOnClickListener(v -> {
            loginUser();
        });

        binding.btnAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
            startActivity(intent);
        });

        binding.btnStaff.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StaffLoginActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        //
    }

}