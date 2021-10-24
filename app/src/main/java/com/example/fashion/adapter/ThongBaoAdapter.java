package com.example.fashion.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashion.R;
import com.example.fashion.activities.ProductDetailsActivity;
import com.example.fashion.model.Products;
import com.example.fashion.model.ThongBao;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ViewHolder> {
    private Context Context;
    private List<ThongBao> list;

    public ThongBaoAdapter(android.content.Context context, List<ThongBao> list) {
        Context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ThongBaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Context);
        View v = inflater.inflate(R.layout.item_thongbao, parent, false);
        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvDetails.setText(list.get(position).getDetails());
        holder.tvDate.setText(list.get(position).getDate());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Context, ProductDetailsActivity.class);
//                i.putExtra("tenSanPham",list.get(position).getTenSanPham());
//                i.putExtra("moTa",list.get(position).getMoTa());
//                i.putExtra("maHinhAnh",String.valueOf(list.get(position).getMaHinhAnh()));
//                i.putExtra("giaTien",String.valueOf(list.get(position).getGiaTien()));
////                i.putExtra("soLuong",String.valueOf(list.get(position).getSoLuong()));
////                i.putExtra("maSanPham",String.valueOf(list.get(position).getMaSanPham()));
////                i.putExtra("maHinhAnh",String.valueOf(list.get(position).getMaHinhAnh()));
////                i.putExtra("maTheLoai",String.valueOf(list.get(position).getMaTheLoai()));
//
//                Context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDetails, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitleThongBao);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            tvDate = itemView.findViewById(R.id.tvDate);
//            tvMoTa = itemView.findViewById(R.id.card_nb_follower);
        }
    }


}
