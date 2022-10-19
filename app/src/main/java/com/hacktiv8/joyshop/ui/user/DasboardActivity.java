package com.hacktiv8.joyshop.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.adapter.CategoryAdapter;
import com.hacktiv8.joyshop.preferences.UserPreference;
import com.hacktiv8.joyshop.ui.MainActivity;
import com.hacktiv8.joyshop.ui.admin.AdminHomeActivity;


public class DasboardActivity extends AppCompatActivity {

    private RecyclerView categoryRv;
    private CategoryAdapter categoryAdapter;
    private UserPreference preference;
    private boolean isLogin = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        preference = new UserPreference(this);
        if (user != null) {
            isLogin = true;
        } else {
            isLogin = false;
            finish();
        }

        categoryRv = findViewById(R.id.kategory_rv);
        String[] categoryName = {"Books", "Clothes", "Electronics", "Other"};
        int[] categoryIcon = {R.drawable.ic_book, R.drawable.ic_book, R.drawable.ic_book, R.drawable.ic_book };
        categoryAdapter = new CategoryAdapter(this, DasboardActivity.this, categoryName, categoryIcon);
        showRecyclerList();




    }

    private void showRecyclerList() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        categoryRv.setLayoutManager(layoutManager);
        categoryRv.setAdapter(categoryAdapter);

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