package com.hacktiv8.joyshop.ui.user.product.clothes;

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
import com.hacktiv8.joyshop.databinding.ActivityClothesProductBinding;
import com.hacktiv8.joyshop.model.Product;
import com.hacktiv8.joyshop.ui.adapter.ProductAdapter;
import com.hacktiv8.joyshop.ui.user.product.ListProductActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClothesProductActivity extends AppCompatActivity {

    private ActivityClothesProductBinding binding;
    public static final String GENDER_TYPE = "gender_type";
    private FirebaseFirestore db;
    private RecyclerView rvProduct;
    private List<Product> list = new ArrayList<>();
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClothesProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rvProduct = binding.rvProduct;
        String gender = getIntent().getStringExtra(GENDER_TYPE);

        db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            finish();
        }

        getSupportActionBar().setTitle("Clothes");

        rvProduct.setHasFixedSize(true);
        rvProduct.setLayoutManager(new GridLayoutManager(this, 2));

        if (gender!=null) {
            if (Objects.equals(gender, "man")) {
                getData(gender);
                binding.type.setText(R.string.pria);
            } else {
                getData(gender);
                binding.type.setText(R.string.wanita);
            }
        }

        binding.categoryTshirt.setOnClickListener(v -> {
            Intent intent = new Intent(ClothesProductActivity.this, ClothesCategoryListActivity.class);
            intent.putExtra(ClothesCategoryListActivity.EXTRA_GENDER, gender);
            intent.putExtra(ClothesCategoryListActivity.EXTRA_CATEGORY, "baju");
            startActivity(intent);
        });

        binding.categoryformal.setOnClickListener(v -> {
            Intent intent = new Intent(ClothesProductActivity.this, ClothesCategoryListActivity.class);
            intent.putExtra(ClothesCategoryListActivity.EXTRA_GENDER, gender);
            intent.putExtra(ClothesCategoryListActivity.EXTRA_CATEGORY, "formal");
            startActivity(intent);
        });

        binding.categoryPant.setOnClickListener(v -> {
            Intent intent = new Intent(ClothesProductActivity.this, ClothesCategoryListActivity.class);
            intent.putExtra(ClothesCategoryListActivity.EXTRA_GENDER, gender);
            intent.putExtra(ClothesCategoryListActivity.EXTRA_CATEGORY, "celana");
            startActivity(intent);
        });

        binding.categoryShoes.setOnClickListener(v -> {
            Intent intent = new Intent(ClothesProductActivity.this, ClothesCategoryListActivity.class);
            intent.putExtra(ClothesCategoryListActivity.EXTRA_GENDER, gender);
            intent.putExtra(ClothesCategoryListActivity.EXTRA_CATEGORY, "sepatu");
            startActivity(intent);
        });

    }

    private void getData(String gender) {

        db.collection("produk").whereEqualTo("tipe", "clothes")
                .whereEqualTo("gender", gender).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        list.clear();
                        for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                            Product product = documentSnapshot.toObject(Product.class);
                            list.add(product);
                        }
                        Log.d("AdminProduk", String.valueOf(list.size()));
                        productAdapter = new ProductAdapter(ClothesProductActivity.this, list);
                        productAdapter.notifyDataSetChanged();
                        rvProduct.setAdapter(productAdapter);
                    } else {
                        Log.w("AdminProduk", "loadPost:onCancelled", task.getException());
                    }
                });
    }
}