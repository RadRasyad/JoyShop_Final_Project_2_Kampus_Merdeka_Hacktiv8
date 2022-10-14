package com.hacktiv8.joyshop.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hacktiv8.joyshop.databinding.ActivityAdminLoginBinding;

public class AdminLoginActivity extends AppCompatActivity {

    private ActivityAdminLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}