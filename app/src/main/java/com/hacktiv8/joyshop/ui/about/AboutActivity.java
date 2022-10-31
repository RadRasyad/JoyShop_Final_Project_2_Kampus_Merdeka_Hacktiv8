package com.hacktiv8.joyshop.ui.about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.hacktiv8.joyshop.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {
    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openInstagramFragment(); }
        });

        binding.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openEmailFragment(); }
        });

        binding.github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openGithubFragment(); }
        });
    }

    private void openGithubFragment() {
        GithubFragment fragment = new GithubFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.frameLayout.getId(), fragment, "Fragment_TAG");
        fragmentTransaction.commit();
    }

    private void openEmailFragment() {
        EmailFragment fragment = new EmailFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.frameLayout.getId(), fragment, "Fragment_TAG");
        fragmentTransaction.commit();
    }

    private void openInstagramFragment() {
        InstagramFragment fragment = new InstagramFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.frameLayout.getId(), fragment, "Fragment_TAG");
        fragmentTransaction.commit();
    }
}