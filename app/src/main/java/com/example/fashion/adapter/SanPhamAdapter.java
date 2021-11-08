package com.example.fashion.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.fashion.R;
import com.example.fashion.activities.ProductDetailsActivity;
import com.example.fashion.model.Products;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    private Context Context;
    private List<Products> list;

    public SanPhamAdapter(android.content.Context context, List<Products> list) {
        Context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SanPhamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Context);
        View v = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTenSP.setText(list.get(position).getProduct_name());
        Glide.with(Context).load(list.get(position).getImage_url_product()).into(holder.imgProduct);
        holder.tvGiaTien.setText(list.get(position).getPrice()+"đ");
        holder.imgYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Context, "Yêu thích", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Context, ProductDetailsActivity.class);
                i.putExtra("product_name", list.get(position).getProduct_name());
                i.putExtra("price", String.valueOf(list.get(position).getPrice()));
                i.putExtra("description", list.get(position).getDescription());
                i.putExtra("product_id", String.valueOf(list.get(position).getId_product()));
                i.putExtra("img_url_product",(list.get(position).getImage_url_product()));
//                i.putExtra("maSanPham",String.valueOf(list.get(position).getMaSanPham()));
//                i.putExtra("maHinhAnh",String.valueOf(list.get(position).getMaHinhAnh()));
//                i.putExtra("maTheLoai",String.valueOf(list.get(position).getMaTheLoai()));

                Context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct,imgYeuThich;
        TextView tvTenSP, tvGiaTien, tvMoTa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgSanPham);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTien);
            imgYeuThich = itemView.findViewById(R.id.imgYeuThich);
//            tvMoTa = itemView.findViewById(R.id.card_nb_follower);
        }
    }

    public void getFilter(ArrayList<Products> filterList){
            list = filterList;
            notifyDataSetChanged();
    }
}
