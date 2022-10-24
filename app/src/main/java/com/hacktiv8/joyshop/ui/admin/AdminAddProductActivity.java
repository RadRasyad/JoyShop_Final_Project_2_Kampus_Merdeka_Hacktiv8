package com.hacktiv8.joyshop.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hacktiv8.joyshop.databinding.ActivityAdminAddProductBinding;

public class AdminAddProductActivity extends AppCompatActivity {

    private ActivityAdminAddProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}