package com.hacktiv8.joyshop.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.databinding.ActivityAdminAddProductBinding;

public class AdminAddProductActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener{

    private ActivityAdminAddProductBinding binding;
    public static final String EXTRA_ID = "extra_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int lastId = getIntent().getIntExtra(EXTRA_ID, 0);
        Log.d("AddProduk", String.valueOf(lastId));
        binding.inputId.setText(String.valueOf(lastId+1));

        Spinner spinnerGender = binding.spinnerGender;
        Spinner spinnerTipe = binding.spinnerTipe;
        Spinner spinnerKategori = binding.spinnerCategory;

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterTipe = ArrayAdapter.createFromResource(this,
                R.array.tipe_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterKategori = ArrayAdapter.createFromResource(this,
                R.array.kategori_array, android.R.layout.simple_spinner_item);

        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapterGender);
        spinnerGender.setOnItemSelectedListener(this);

        adapterTipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipe.setAdapter(adapterTipe);
        spinnerTipe.setOnItemSelectedListener(this);

        adapterKategori.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKategori.setAdapter(adapterKategori);
        spinnerKategori.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Toast.makeText(getApplicationContext(),
                        String.valueOf(parent.getItemAtPosition(pos)),
                        Toast.LENGTH_LONG)
                .show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}