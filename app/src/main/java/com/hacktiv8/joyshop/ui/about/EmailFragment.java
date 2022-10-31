package com.hacktiv8.joyshop.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.databinding.FragmentEmailBinding;


public class EmailFragment extends Fragment {
    private FragmentEmailBinding binding;

    public EmailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEmailBinding.inflate(inflater, container, false);

        binding.emailKhonsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("text/plain");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"khonsa.fathimah@gmail.com"});
                startActivity(Intent.createChooser(email, "Send mail"));
            }
        });

        binding.emailRafi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("text/plain");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"Rafidany21@gmail.com"});
                startActivity(Intent.createChooser(email, "Send mail"));
            }
        });

        binding.emailNadhif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("text/plain");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"syaifunnadhif@gmail.com"});
                startActivity(Intent.createChooser(email, "Send email"));
            }
        });

        return binding.getRoot();
    }
}