package com.hacktiv8.joyshop.ui.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hacktiv8.joyshop.databinding.ActivityRegisterUserBinding;
import com.hacktiv8.joyshop.model.User;

public class RegisterUserActivity extends AppCompatActivity {

    private ActivityRegisterUserBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String username, email, phone, pass1, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        binding.btnRegister.setOnClickListener(v -> {
            registerUser();
        });

        binding.btnLogin.setOnClickListener(v -> {
            finish();
        });
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isValidData() {
        username = binding.inputUsername.getText().toString().trim();
        email = binding.inputEmail.getText().toString().trim();
        phone = binding.inputPhone.getText().toString().trim();
        pass1 = binding.inputPass1.getText().toString().trim();
        pass2 = binding.inputPass2.getText().toString().trim();

        if ("".equals(username) && "".equals(email) && "".equals(phone) &&
                "".equals(pass1) && "".equals(pass2)
        ) {
            Toast.makeText(this, "Tolong Isi Semua TextField", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidEmail(email)) {
            Toast.makeText(this, "Email Tidak Valid", Toast.LENGTH_LONG).show();
            return false;
        } else if (!pass2.equals(pass1)) {
            Toast.makeText(this, "Password Tidak Sama", Toast.LENGTH_LONG).show();
            return false;
        } else if (pass2.length() < 6) {
            Toast.makeText(this, "Password Harus Lebih Dari 6", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void registerUser() {
        boolean isValid = isValidData();
        if (isValid) {
            progressBar(true);
            mAuth.createUserWithEmailAndPassword(email, pass1)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            String uId = task.getResult().getUser().getUid();
                            User user = new User(uId, username, email, phone, "2");
                            if (task.isSuccessful()) {
                                mDatabase.child(username).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressBar(false);
                                            Toast.makeText(RegisterUserActivity.this, "Terdaftar", Toast.LENGTH_LONG).show();
                                            new Handler().postDelayed(RegisterUserActivity.this::finish, 1000);
                                        } else {
                                            Toast.makeText(RegisterUserActivity.this, "Gagal Mendaftar", Toast.LENGTH_LONG).show();
                                            progressBar(false);
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(RegisterUserActivity.this, "Gagal Mendaftar", Toast.LENGTH_LONG).show();
                                progressBar(false);
                            }
                        }
                    });
        }
    }

    private void progressBar(boolean state) {
        if (state) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}