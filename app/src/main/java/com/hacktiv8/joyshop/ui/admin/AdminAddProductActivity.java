package com.hacktiv8.joyshop.ui.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.databinding.ActivityAdminAddProductBinding;
import com.hacktiv8.joyshop.model.Product;

import java.util.Objects;

public class AdminAddProductActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener{

    private ActivityAdminAddProductBinding binding;
    private FirebaseFirestore db;
    public static final String INTENT_TYPE = "intent_type";
    public static final String EXTRA_ID = "extra_id";
    private int lastId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            finish();
        }

        lastId = getIntent().getIntExtra(EXTRA_ID, 0);

        binding.inputId.setText(String.valueOf(lastId+1));

        Spinner spinnerGender = binding.spinnerGender;
        Spinner spinnerTipe = binding.spinnerTipe;
        Spinner spinnerKategori = binding.spinnerCategory;

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterTipe = ArrayAdapter.createFromResource(this,
                R.array.tipe_array, android.R.layout.simple_spinner_item);


        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapterGender);
        spinnerGender.setOnItemSelectedListener(this);

        adapterTipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipe.setAdapter(adapterTipe);

        spinnerTipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            ArrayAdapter<CharSequence> adapterKategori;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tempTipe = spinnerTipe.getSelectedItem().toString();

                switch (tempTipe) {
                    case "clothes":
                        adapterKategori = ArrayAdapter.createFromResource(AdminAddProductActivity.this,
                                R.array.kategori_clothes_array, android.R.layout.simple_spinner_item);
                        break;
                    case "electronics":
                        adapterKategori = ArrayAdapter.createFromResource(AdminAddProductActivity.this,
                                R.array.kategori_electronics_array, android.R.layout.simple_spinner_item);
                        break;
                    case "books":
                        adapterKategori = ArrayAdapter.createFromResource(AdminAddProductActivity.this,
                                R.array.kategori_books_array, android.R.layout.simple_spinner_item);
                        break;
                    default:
                        adapterKategori = ArrayAdapter.createFromResource(AdminAddProductActivity.this,
                                R.array.kategori_other_array, android.R.layout.simple_spinner_item);
                        break;
                }
                adapterKategori.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKategori.setAdapter(adapterKategori);
                spinnerKategori.setOnItemSelectedListener(this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        binding.btnTambah.setOnClickListener(v -> {

            String id = binding.inputId.getText().toString().trim();
            String nama = binding.inputBrand.getText().toString().trim();
            String hrg = binding.inputHrg.getText().toString().trim();
            String stok = binding.inputStok.getText().toString().trim();
            String brand = binding.inputBrand.getText().toString().trim();
            String desc = binding.inputDesc.getText().toString().trim();
            String img = "https://firebasestorage.googleapis.com/v0/b/e-commerce-14d7c.appspot.com/o/sample_product.png?alt=media&token=207f8226-cb2f-4100-bd26-9c5bc91b3ceb";
            String gender = getGender(spinnerGender.getSelectedItem().toString());
            String tipe = spinnerTipe.getSelectedItem().toString();

            Product newProduct = new Product();
            newProduct.setTipe(tipe);
            newProduct.setGender(gender);
            newProduct.setKategori(spinnerKategori.getSelectedItem().toString());
            newProduct.setId(Integer.parseInt(id));
            newProduct.setNama(nama);
            newProduct.setHrg(hrg);
            newProduct.setStok(stok);
            newProduct.setBrand(brand);
            newProduct.setDeskripsi(desc);
            newProduct.setImg(img);

            Log.d("adapterTipe", spinnerTipe.getSelectedItem().toString());
            Log.d("adapterKategori", spinnerKategori.getSelectedItem().toString());
            Log.d("adapterGender", gender);
            inputData(newProduct);
        });

    }

    private String getGender(String gender) {
        if (Objects.equals(gender, "pria")) {
            return "man";
        } else if (Objects.equals(gender, "wanita")) {
            return "woman";
        } else {
            return "unisex";
        }
    }

    private void inputData(Product product) {
        db.collection("produk").document(String.valueOf(product.getId()))
                .set(product)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AdminAddProductActivity.this, "Produk Ditambahkan", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminAddProductActivity.this, "Gagal Menambahkan Produk", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private void isEdit() {
        //
    }

}