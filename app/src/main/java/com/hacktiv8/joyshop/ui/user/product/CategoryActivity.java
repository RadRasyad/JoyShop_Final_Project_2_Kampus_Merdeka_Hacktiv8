package com.hacktiv8.joyshop.ui.user.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hacktiv8.joyshop.databinding.ActivityCategoryBinding;
import com.hacktiv8.joyshop.ui.user.product.clothes.ClothesProductActivity;

public class CategoryActivity extends AppCompatActivity {

    private ActivityCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.man.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, ClothesProductActivity.class);
            intent.putExtra(ClothesProductActivity.GENDER_TYPE,"man");
            startActivity(intent);
        });

        binding.woman.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, ClothesProductActivity.class);
            intent.putExtra(ClothesProductActivity.GENDER_TYPE,"woman");
            startActivity(intent);
        });
        
        getSupportActionBar().setTitle("Clothes");

    }
}