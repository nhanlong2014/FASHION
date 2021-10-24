package com.example.fashion.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashion.R;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.Products;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private Context Context;
    private List<GioHang> list;
    Double totalPrice = 0.0;
    int soLuong = 1;

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
        holder.tvGiaTien.setText(String.valueOf(list.get(position).getGiaTien()+" đ"));
        holder.tvSoLuong.setText(String.valueOf(list.get(position).getSoLuong()));
        holder.tvTongTien.setText(String.valueOf(list.get(position).getGiaTien()));

        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong++;
                holder.tvSoLuong.setText(String.valueOf(soLuong));
                totalPrice = soLuong * list.get(position).getGiaTien();
                holder.tvTongTien.setText(String.valueOf(totalPrice)+"đ");
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int count= Integer.parseInt(String.valueOf(holder.tvSoLuong.getText()));
                soLuong--;
                holder.tvSoLuong.setText(String.valueOf(soLuong));
                totalPrice =  list.get(position).getGiaTien() * soLuong;
                holder.tvTongTien.setText(String.valueOf(totalPrice)+"đ");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView tvTenSP, tvGiaTien, tvSoLuong, tvTongTien;
        AppCompatButton btnTru, btnCong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgCart);
            tvTenSP = itemView.findViewById(R.id.tvTenCart);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTienCart);
            btnTru = itemView.findViewById(R.id.btntru);
            btnCong = itemView.findViewById(R.id.btncong);
            tvSoLuong = itemView.findViewById(R.id.tvsoluong);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);

//            tvMoTa = itemView.findViewById(R.id.card_nb_follower);
        }
    }
}
