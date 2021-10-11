package com.example.fashion.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashion.R;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.Products;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private Context Context;
    private List<GioHang> list;

    public GioHangAdapter(Context Context, List<GioHang> list) {
        this.Context = Context;
        this.list = list;
    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Context);
        View v = inflater.inflate(R.layout.item_giohang, parent, false);
        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTenSP.setText(list.get(position).getTenSanPham());
        Glide.with(Context).load(list.get(position).getHinhAnh()).into(holder.imgProduct);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Context, ProductDetailsActivity.class);
//                i.putExtra("tenSp",list.get(position).getTenSanPham());
//                Context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView tvTenSP, tvGiaTien, tvMoTa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgCart);
            tvTenSP = itemView.findViewById(R.id.tvTenCart);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTienCart);
//            tvMoTa = itemView.findViewById(R.id.card_nb_follower);
        }
    }
}
