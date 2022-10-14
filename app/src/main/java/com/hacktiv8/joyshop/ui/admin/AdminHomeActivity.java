package com.hacktiv8.joyshop.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hacktiv8.joyshop.databinding.ActivityAdminHomeBinding;

public class AdminHomeActivity extends AppCompatActivity {

    private ActivityAdminHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            binding.email.setText(user.getEmail());
        } else {
            // No user is signed in
        }

    }
}