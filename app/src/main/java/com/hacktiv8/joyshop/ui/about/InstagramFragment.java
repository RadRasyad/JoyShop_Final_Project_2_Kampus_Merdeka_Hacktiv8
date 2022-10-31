package com.hacktiv8.joyshop.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.databinding.FragmentInstagramBinding;


public class InstagramFragment extends Fragment {
    private FragmentInstagramBinding binding;

    public InstagramFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInstagramBinding.inflate(inflater, container, false);

        binding.igKhonsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openUrl("https://www.instagram.com/fkhonsaa/");}
        });

        binding.igRafi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openUrl("https://www.instagram.com/dan_rsy/");}
        });

        binding.igNadhif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openUrl("https://www.instagram.com/syaifun_/");}
        });
        return binding.getRoot();
    }

    private void openUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}