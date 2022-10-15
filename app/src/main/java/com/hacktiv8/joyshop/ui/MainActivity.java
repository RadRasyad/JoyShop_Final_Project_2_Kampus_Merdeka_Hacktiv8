package com.hacktiv8.joyshop.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktiv8.joyshop.databinding.ActivityMainBinding;
import com.hacktiv8.joyshop.preferences.UserPreference;
import com.hacktiv8.joyshop.ui.admin.AdminHomeActivity;
import com.hacktiv8.joyshop.ui.admin.AdminLoginActivity;
import com.hacktiv8.joyshop.ui.staff.StaffLoginActivity;
import com.hacktiv8.joyshop.ui.user.RegisterUserActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private UserPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        preference = new UserPreference(this);

        binding.btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterUserActivity.class);
            startActivity(intent);
        });

        binding.btnLogin.setOnClickListener(v -> {
            loginUser();
        });

        binding.btnAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            startActivity(intent);
        });

        binding.btnStaff.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StaffLoginActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        // TODO lakukan sesuatu untuk login user
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            if (currentUser.getUid().equals(preference.getUserPref().getuId())) {
                reload(preference.getUserPref().getRole());
            } else {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Session telah habis", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reload(String role) {

        if ("0".equals(role)) {
            Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class);
            startActivity(intent);
        } else if ("1".equals(role)) {
//            Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class);
//            startActivity(intent);
        } if ("2".equals(role)) {
//            Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class);
//            startActivity(intent);
        }
    }
}