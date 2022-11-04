package com.hacktiv8.joyshop.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.databinding.AdminItemProductBinding;
import com.hacktiv8.joyshop.databinding.ItemProductBinding;
import com.hacktiv8.joyshop.model.Product;
import com.hacktiv8.joyshop.ui.admin.AdminAddProductActivity;
import com.hacktiv8.joyshop.ui.user.DetailProductActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context context;
    private List<Product> list;


    public ProductAdapter(Context context, List<Product> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        Product product = list.get(position);
        if (product!=null) {
            holder.bind(product);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemProductBinding binding;

        public MyViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product product) {
            binding.tvJudul.setText(product.getNama());
            binding.tvHarga.setText("Rp."+product.getHrg());
            binding.tvStok.setText(product.getStok());
            binding.tvBrand.setText(product.getBrand());

            Picasso.get().load(product.getImg())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .resize(1460, 1460)
                    .centerCrop()
                    .into(binding.ivProduct);

            int position = getAdapterPosition();
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("img", list.get(position).getImg());
                intent.putExtra("nama", list.get(position).getNama());
                intent.putExtra("harga",list.get(position).getHrg());
                intent.putExtra("stock",list.get(position).getStok());
                intent.putExtra("desc",list.get(position).getDeskripsi());

                itemView.getContext().startActivity(intent);
            });

        }
    }
}
