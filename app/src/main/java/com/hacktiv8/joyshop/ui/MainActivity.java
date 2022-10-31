package com.hacktiv8.joyshop.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktiv8.joyshop.databinding.ActivityMainBinding;
import com.hacktiv8.joyshop.model.User;
import com.hacktiv8.joyshop.preferences.UserPreference;
import com.hacktiv8.joyshop.ui.about.AboutActivity;
import com.hacktiv8.joyshop.ui.admin.AdminHomeActivity;
import com.hacktiv8.joyshop.ui.admin.AdminLoginActivity;
import com.hacktiv8.joyshop.ui.staff.StaffHomeActivity;
import com.hacktiv8.joyshop.ui.staff.StaffLoginActivity;
import com.hacktiv8.joyshop.ui.user.DasboardActivity;
import com.hacktiv8.joyshop.ui.user.RegisterUserActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private UserPreference preference;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        preference = new UserPreference(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

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

        binding.btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String username = binding.inputUsername.getText().toString().trim();
        String password = binding.inputPassword.getText().toString().trim();

        try {
            databaseReference.child(username).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean isExcist = snapshot.exists();
                    if(isExcist) {
                        User user = snapshot.getValue(User.class);
                        if(user.getRole().equals("2")){
                            mAuth.signInWithEmailAndPassword(user.getEmail(), password)
                                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                User toSave = new User();
                                                toSave.setuId(user.getuId());
                                                toSave.setUsername(user.getUsername());
                                                toSave.setEmail(user.getEmail());
                                                toSave.setPhone(user.getPhone());
                                                toSave.setRole(user.getRole());
                                                String role = user.getRole();
                                                saveUser(toSave);
                                                reload(role);

                                            } else {
                                                Log.w("MainActivity", "signInWithEmail:failure", task.getException());
                                                Toast.makeText(MainActivity.this, "Password Salah",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(MainActivity.this, "Anda Bukan User", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this,"Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(MainActivity.this,"Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
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
            Intent intent = new Intent(MainActivity.this, StaffHomeActivity.class);
            startActivity(intent);
        } else if ("2".equals(role)) {
            Intent intent = new Intent(MainActivity.this, DasboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private void saveUser(User user) {
        UserPreference userPreference = new UserPreference(MainActivity.this);
        userPreference.setUserPref(user);
    }
}