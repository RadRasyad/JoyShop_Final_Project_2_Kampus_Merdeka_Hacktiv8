package com.hacktiv8.joyshop.ui.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.databinding.ActivityDetailProductBinding;
import com.hacktiv8.joyshop.model.Product;
import com.hacktiv8.joyshop.ui.MainActivity;
import com.squareup.picasso.Picasso;

public class DetailProductActivity extends AppCompatActivity {
    private ActivityDetailProductBinding binding;
    String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nameTxt.setText(getIntent().getStringExtra("nama"));
        binding.stockTxt.setText(getIntent().getStringExtra("stock"));
        binding.deskripsiTxt.setText(getIntent().getStringExtra("desc"));
        binding.hrgTxt.setText("Rp "+getIntent().getStringExtra( "harga"));
        img = getIntent().getStringExtra("img");

        Picasso.Builder builder = new Picasso.Builder(this);

        builder.build().load(img)
                .resize(1460, 1460)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(binding.imgView);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}