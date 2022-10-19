package com.hacktiv8.joyshop.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.model.Product;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ListViewHolder> {
    private ArrayList<Product> listProduct;
    private Context context;
    private String[] categoryName;
    private int[] categoryIcon;
    Activity activity;

    public CategoryAdapter(Context context, Activity activity, String[] categoryName, int[] categoryIcon){
        this.context = context;
        this.activity = activity;
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
    }




    @NonNull
    @Override
    public CategoryAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ListViewHolder holder, int position) {
        holder.icon.setImageResource(categoryIcon[position]);
//        Product product = listProduct.get(position);
//        Glide.with(holder.itemView.getContext())
//                        .load(product.getIcon())
//                        .apply(new RequestOptions().override(30, 30))
//                        .into(holder.icon);
        holder.title.setText(categoryName[position]);

    }

    @Override
    public int getItemCount() {
        return categoryName.length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView title;
        private LinearLayout linearLayout;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            icon  = itemView.findViewById(R.id.icon_img);
            title = itemView.findViewById(R.id.title_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CategoryAdapter.class);
                    intent.putExtra("category", categoryName[getLayoutPosition()]);
                    context.startActivity(intent);
                }
            });
        }
    }


}
