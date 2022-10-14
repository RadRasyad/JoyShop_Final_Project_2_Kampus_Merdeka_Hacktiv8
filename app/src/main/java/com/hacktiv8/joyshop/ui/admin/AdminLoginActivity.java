package com.hacktiv8.joyshop.ui.admin;

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
import com.hacktiv8.joyshop.databinding.ActivityAdminLoginBinding;
import com.hacktiv8.joyshop.model.User;

public class AdminLoginActivity extends AppCompatActivity {

    private ActivityAdminLoginBinding binding;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        binding.btnLogin.setOnClickListener(v -> {
            loginAdmin();
        });
    }

    private void loginAdmin() {
        String username = binding.inputUsername.getText().toString().trim();
        String password = binding.inputPassword.getText().toString().trim();

        mDatabase.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isExist = snapshot.exists();

                if (isExist) {
                    User user = snapshot.getValue(User.class);
                    Log.i("AdminLoginActivity", String.valueOf(user.getRole()));
                    if (user.getRole().equals("0")) {
                        Log.i("AdminLoginActivity", user.getUsername());
                        Log.i("AdminLoginActivity", user.getEmail());
                        mAuth.signInWithEmailAndPassword(user.getEmail(), password)
                                .addOnCompleteListener(AdminLoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("AdminLoginActivity", "signInWithEmail:success");
                                            Intent intent = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Log.w("AdminLoginActivity", "signInWithEmail:failure", task.getException());
                                            Toast.makeText(AdminLoginActivity.this, "Password Salah",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "Anda Bukan Admin", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AdminLoginActivity.this,"Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("AdminLoginActivity", "Database Failure", error.toException());
                Toast.makeText(AdminLoginActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}