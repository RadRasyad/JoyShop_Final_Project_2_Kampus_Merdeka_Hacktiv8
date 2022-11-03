package com.hacktiv8.joyshop.ui.staff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.databinding.ActivityStaffLoginBinding;
import com.hacktiv8.joyshop.model.User;
import com.hacktiv8.joyshop.preferences.UserPreference;

public class StaffLoginActivity extends AppCompatActivity {
    private ActivityStaffLoginBinding binding;
    private FirebaseAuth mAuth;
    private UserPreference preference;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStaffLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        preference = new UserPreference(this);

        binding.btnLoginStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginStaff();
            }
        });


    }

    private void loginStaff() {
        String username = binding.inputUsernameStaff.getText().toString().trim();
        String password = binding.inputPassStaff.getText().toString().trim();

        try {
            mDatabase.child(username).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean isExist = snapshot.exists();
                    if (isExist){
                        User user = snapshot.getValue(User.class);
                        if (user!=null) {
                            if (user.getRole().equals("1")){
                                mAuth.signInWithEmailAndPassword(user.getEmail(), password)
                                        .addOnCompleteListener(StaffLoginActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()){
                                                    User toSave = new User();
                                                    toSave.setuId(user.getuId());
                                                    toSave.setUsername(user.getUsername());
                                                    toSave.setEmail(user.getEmail());
                                                    toSave.setPhone(user.getPhone());
                                                    toSave.setRole(user.getRole());
                                                    saveUser(toSave);
                                                    reload();
                                                } else {
                                                    Log.w("StaffLoginActivity", "signInWithEmail:failure", task.getException());
                                                    Toast.makeText(StaffLoginActivity.this, "Password salah", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(StaffLoginActivity.this, "Anda bukan staff", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {
                        Toast.makeText(StaffLoginActivity.this, "Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(StaffLoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e){
            Toast.makeText(this, "Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            if (currentUser.getUid().equals(preference.getUserPref().getuId())){
                reload();
            }else {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(StaffLoginActivity.this, "Session telah habis", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reload() {
        Intent intent = new Intent(StaffLoginActivity.this, StaffHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void saveUser(User user) {
        UserPreference userPreference = new UserPreference(StaffLoginActivity.this);
        userPreference.setUserPref(user);
    }
}