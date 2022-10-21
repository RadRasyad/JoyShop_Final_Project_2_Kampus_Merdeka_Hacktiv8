package com.hacktiv8.joyshop.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.adapter.ProductAdapter;
import com.hacktiv8.joyshop.databinding.ActivityDasboardBinding;
import com.hacktiv8.joyshop.model.Product;
import com.hacktiv8.joyshop.preferences.UserPreference;
import com.hacktiv8.joyshop.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class DasboardActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDasboardBinding binding;
    private UserPreference preference;
    private ImageView books, clothes, electronic, other;
    private boolean isLogin = false;
    private RecyclerView rvProduct;
    private List<Product> list = new ArrayList<>();
    private ProductAdapter productAdapter;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDasboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        preference = new UserPreference(this);
        if (user != null) {
            isLogin = true;
            binding.username.setText("Halo,\n"+user.getEmail());
        } else {
            isLogin = false;
            finish();
        }

        books = findViewById(R.id.categoryBooks);
        books.setOnClickListener(this);
        clothes = findViewById(R.id.categoryClothes);
        clothes.setOnClickListener(this);
        electronic = findViewById(R.id.categoryElectronic);
        electronic.setOnClickListener(this);
        other = findViewById(R.id.categoryOther);
        other.setOnClickListener(this);

        rvProduct = findViewById(R.id.rvProduct);
        
        progressDialog = new ProgressDialog(DasboardActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil Data....");

        



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.categoryBooks:
                break;
            case R.id.categoryClothes:
                break;
            case R.id.categoryElectronic:
                break;
            case R.id.categoryOther:
                break;
        }
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