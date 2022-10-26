package com.hacktiv8.joyshop.ui.user.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktiv8.joyshop.databinding.ActivityListProductBinding;
import com.hacktiv8.joyshop.model.Product;
import com.hacktiv8.joyshop.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListProductActivity extends AppCompatActivity {

    private ActivityListProductBinding binding;
    private DatabaseReference mDatabase;
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

        mDatabase = FirebaseDatabase.getInstance().getReference("Product");
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
            } else {
                getData(type);
                getSupportActionBar().setTitle("Other");
            }


        }

    }

    private void getData(String type) {

        mDatabase.orderByChild("tipe").equalTo(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i("ListProductActivity", String.valueOf(snapshot.getChildren()));
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Product product = dataSnapshot.getValue(Product.class);

                    if (product!=null) {
                        list.add(product);
                        productAdapter = new ProductAdapter(ListProductActivity.this, list);
                        Log.i("ListProductActivity", String.valueOf(list.size()));
                        productAdapter.notifyDataSetChanged();
                        rvProduct.setAdapter(productAdapter);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("DashboardActivity", "loadPost:onCancelled", error.toException());
            }
        });
    }
}