package com.hacktiv8.joyshop.ui.about;

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
        return binding.getRoot();
    }
}