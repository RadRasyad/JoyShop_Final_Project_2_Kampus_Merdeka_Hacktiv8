package com.hacktiv8.joyshop.ui.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.ui.adapter.ProductAdapter;
import com.hacktiv8.joyshop.databinding.ActivityDasboardBinding;
import com.hacktiv8.joyshop.model.Product;
import com.hacktiv8.joyshop.preferences.UserPreference;
import com.hacktiv8.joyshop.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class DasboardActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDasboardBinding binding;
    private UserPreference preference;
    private DatabaseReference mDatabase;

    private ImageView books, clothes, electronic, other;
    private boolean isLogin = false;
    private RecyclerView rvProduct;
    private List<Product> list = new ArrayList<>();
    private ProductAdapter productAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDasboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("Product");
        preference = new UserPreference(this);
        if (user != null) {
            isLogin = true;
            binding.username.setText("Halo,\n"+preference.getUserPref().getUsername());
        } else {
            isLogin = false;
            finish();
        }

        books = binding.categoryBooks;
        books.setOnClickListener(this);
        clothes = binding.categoryClothes;
        clothes.setOnClickListener(this);
        electronic = binding.categoryElectronic;
        electronic.setOnClickListener(this);
        other = binding.categoryOther;
        other.setOnClickListener(this);

        rvProduct = binding.rvProduct;

        getData();
        rvProduct.setHasFixedSize(true);
        rvProduct.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.categoryBooks:
                Intent intent = new Intent(DasboardActivity.this, ListProductActivity.class);
                intent.putExtra(ListProductActivity.EXTRA_TYPE, "books");
                startActivity(intent);
                break;
            case R.id.categoryClothes:
                break;
            case R.id.categoryElectronic:
                break;
            case R.id.categoryOther:
                break;
        }
    }

    private void getData() {

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Product product = dataSnapshot.getValue(Product.class);
                    if (product!=null) {
                        list.add(product);
                        productAdapter = new ProductAdapter(DasboardActivity.this, list);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedTab) {
        switch (selectedTab) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                preference.deleteUserPref();
                reload();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isLogin) {
            finishAffinity();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isLogin) {
            reload();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isLogin) {
            reload();
        }
    }

    private void reload() {
        Intent intent = new Intent(DasboardActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }



}

