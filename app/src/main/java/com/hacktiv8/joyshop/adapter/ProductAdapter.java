package com.hacktiv8.joyshop.adapter;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context context;
    private List<Product> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }


    public ProductAdapter(Context context, List<Product> list){
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(context).inflate(R.layout.card_product, parent, false);
        return new MyViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg()).into(holder.img);
        holder.nama.setText(list.get(position).getName());
        holder.stock.setText(list.get(position).getStock());
        holder.hrg.setText(list.get(position).getStock());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, hrg, stock;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.name_txt);
            hrg  = itemView.findViewById(R.id.hrg_txt);
            stock = itemView.findViewById(R.id.stock_txt);
            img = itemView.findViewById(R.id.imgView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog!= null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
