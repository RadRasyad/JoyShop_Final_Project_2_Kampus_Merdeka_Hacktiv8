package com.hacktiv8.joyshop.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hacktiv8.joyshop.databinding.ActivityMainBinding;
import com.hacktiv8.joyshop.ui.admin.AdminLoginActivity;
import com.hacktiv8.joyshop.ui.staff.StaffLoginActivity;
import com.hacktiv8.joyshop.ui.user.RegisterUserActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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