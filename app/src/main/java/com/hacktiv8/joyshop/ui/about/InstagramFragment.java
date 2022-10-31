package com.hacktiv8.joyshop.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return binding.getRoot();
    }
}