package com.hacktiv8.joyshop.ui.staff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.databinding.ActivityStaffHomeBinding;
import com.hacktiv8.joyshop.databinding.ItemEmailBinding;
import com.hacktiv8.joyshop.databinding.ItemPhoneBinding;
import com.hacktiv8.joyshop.preferences.UserPreference;
import com.hacktiv8.joyshop.ui.MainActivity;

public class StaffHomeActivity extends AppCompatActivity {
    private ActivityStaffHomeBinding binding;
    private UserPreference preference;
    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStaffHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        preference = new UserPreference(this);

        if (user != null) {
            isLogin = true;
            binding.usernameStaff.setText(preference.getUserPref().getUsername());
            binding.itemPhone.phoneStaff.setText(preference.getUserPref().getPhone());
            binding.itemEmail.emailStaff.setText(preference.getUserPref().getEmail());
        } else {
            isLogin = false;
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.staff_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int selectedTab) {
        switch (selectedTab) {
            case R.id.logoutStaff:
                FirebaseAuth.getInstance().signOut();
                preference.deleteUserPref();
                reload();
                break;
        }
    }

    private void reload() {
        Intent intent = new Intent(StaffHomeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}