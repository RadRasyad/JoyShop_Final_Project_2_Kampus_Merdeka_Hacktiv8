package com.hacktiv8.joyshop.ui.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hacktiv8.joyshop.databinding.ActivityAdminProductBinding;
import com.hacktiv8.joyshop.model.Product;
import com.hacktiv8.joyshop.ui.adapter.AdminProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdminProductActivity extends AppCompatActivity {

    private ActivityAdminProductBinding binding;
    private FirebaseFirestore db;
    private RecyclerView rvProduct;
    private List<Product> list = new ArrayList<>();
    private AdminProductAdapter productAdapter;
    private int lastId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            finish();
        }

        rvProduct = binding.rvProduct;

        getData();
        rvProduct.setHasFixedSize(true);
        rvProduct.setLayoutManager(new GridLayoutManager(this, 2));

        binding.fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(AdminProductActivity.this, AdminAddProductActivity.class);
            intent.putExtra(AdminAddProductActivity.INTENT_TYPE, false);
            intent.putExtra(AdminAddProductActivity.EXTRA_ID, lastId);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {

        db.collection("produk")
                .orderBy("id", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        list.clear();
                        for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                            Product product = documentSnapshot.toObject(Product.class);
                            list.add(product);
                        }
                        Log.d("AdminProduk", String.valueOf(list.size()));
                        lastId = list.get(list.size()-1).getId();
                        Log.d("AdminProduk", String.valueOf(lastId));
                        productAdapter = new AdminProductAdapter(AdminProductActivity.this, list);
                        productAdapter.notifyDataSetChanged();
                        rvProduct.setAdapter(productAdapter);
                    } else {
                        Log.w("AdminProduk", "loadPost:onCancelled", task.getException());
                    }
                });
    }
}