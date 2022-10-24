package com.hacktiv8.joyshop.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.databinding.ActivityAdminHomeBinding;
import com.hacktiv8.joyshop.model.User;
import com.hacktiv8.joyshop.preferences.UserPreference;
import com.hacktiv8.joyshop.ui.MainActivity;

public class AdminHomeActivity extends AppCompatActivity {

    private ActivityAdminHomeBinding binding;
    private UserPreference preference;
    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        preference = new UserPreference(this);
        if (user != null) {
            isLogin = true;
            binding.username.setText("Hello, "+preference.getUserPref().getUsername());
        } else {
            isLogin = false;
            finish();
        }

        binding.btnStaff.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, AdminRegisterStaffActivity.class);
            startActivity(intent);
        });

        binding.btnAddStock.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, AdminProductActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedTab) {
        switch (selectedTab) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                preference.deleteUserPref();
                reload();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isLogin) {
            finishAffinity();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isLogin) {
            reload();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isLogin) {
            reload();
        }
    }

    private void reload() {
        Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}