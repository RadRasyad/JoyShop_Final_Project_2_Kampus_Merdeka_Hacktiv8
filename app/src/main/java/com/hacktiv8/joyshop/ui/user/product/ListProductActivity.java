package com.hacktiv8.joyshop.ui.user.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hacktiv8.joyshop.databinding.ActivityListProductBinding;
import com.hacktiv8.joyshop.model.Product;
import com.hacktiv8.joyshop.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListProductActivity extends AppCompatActivity {

    private ActivityListProductBinding binding;
    private FirebaseFirestore db;
    public static final String EXTRA_TYPE = "extra_type";
    private RecyclerView rvProduct;
    private List<Product> list = new ArrayList<>();
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rvProduct = binding.rvProduct;

        db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            finish();
        }

        rvProduct.setHasFixedSize(true);
        rvProduct.setLayoutManager(new GridLayoutManager(this, 2));

        String type = getIntent().getStringExtra(EXTRA_TYPE);

        if (type!=null) {
            if (Objects.equals(type, "books")) {
                getData(type);
                getSupportActionBar().setTitle("Books");
            } else if (Objects.equals(type, "electronics")) {
                getData(type);
                getSupportActionBar().setTitle("Electronics");
            }
            else {
                getData(type);
                getSupportActionBar().setTitle("Other");
            }
        }

    }

    private void getData(String type) {

        db.collection("produk").whereEqualTo("tipe", type).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        list.clear();
                        for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                            Product product = documentSnapshot.toObject(Product.class);
                            list.add(product);
                        }
                        Log.d("AdminProduk", String.valueOf(list.size()));
                        productAdapter = new ProductAdapter(ListProductActivity.this, list);
                        productAdapter.notifyDataSetChanged();
                        rvProduct.setAdapter(productAdapter);
                    } else {
                        Log.w("AdminProduk", "loadPost:onCancelled", task.getException());
                    }
                });
    }
}