package com.hacktiv8.joyshop.ui.user.product.electronics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.databinding.ActivityElectronicsProductBinding;
import com.hacktiv8.joyshop.model.Product;
import com.hacktiv8.joyshop.ui.adapter.ProductAdapter;
import com.hacktiv8.joyshop.ui.user.product.ListProductActivity;
import com.hacktiv8.joyshop.ui.user.product.clothes.ClothesCategoryListActivity;
import com.hacktiv8.joyshop.ui.user.product.clothes.ClothesProductActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ElectronicsProductActivity extends AppCompatActivity {

    private ActivityElectronicsProductBinding binding;
    private FirebaseFirestore db;
    private RecyclerView rvProduct;
    private List<Product> list = new ArrayList<>();
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityElectronicsProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rvProduct = binding.rvProduct;

        db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            finish();
        }

        rvProduct.setHasFixedSize(true);
        rvProduct.setLayoutManager(new GridLayoutManager(this, 2));

        getData();
        getSupportActionBar().setTitle("Electronics");


        binding.categoryLaptop.setOnClickListener(view -> {
            Intent intent = new Intent(ElectronicsProductActivity.this, ElectronicsCategoyListActivity.class);
            intent.putExtra(ElectronicsCategoyListActivity.EXTRA_CATEGORY, "Laptop");
            startActivity(intent);
        });

        binding.categorySmartphone.setOnClickListener(view -> {
            Intent intent = new Intent(ElectronicsProductActivity.this, ElectronicsCategoyListActivity.class);
            intent.putExtra(ElectronicsCategoyListActivity.EXTRA_CATEGORY, "Smartphone");
            startActivity(intent);
        });

        binding.categoryKeyboard.setOnClickListener(view -> {
            Intent intent = new Intent(ElectronicsProductActivity.this, ElectronicsCategoyListActivity.class);
            intent.putExtra(ElectronicsCategoyListActivity.EXTRA_CATEGORY, "Keyboard");
            startActivity(intent);
        });

        binding.categoryMouse.setOnClickListener(view -> {
            Intent intent = new Intent(ElectronicsProductActivity.this, ElectronicsCategoyListActivity.class);
            intent.putExtra(ElectronicsCategoyListActivity.EXTRA_CATEGORY, "Mouse");
            startActivity(intent);
        });


    }

    private void getData() {

        db.collection("produk").whereEqualTo("tipe", "electronics").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        list.clear();
                        for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                            Product product = documentSnapshot.toObject(Product.class);
                            list.add(product);
                        }
                        Log.d("AdminProduk", String.valueOf(list.size()));
                        productAdapter = new ProductAdapter(ElectronicsProductActivity.this, list);
                        productAdapter.notifyDataSetChanged();
                        rvProduct.setAdapter(productAdapter);
                    } else {
                        Log.w("AdminProduk", "loadPost:onCancelled", task.getException());
                    }
                });
    }
}