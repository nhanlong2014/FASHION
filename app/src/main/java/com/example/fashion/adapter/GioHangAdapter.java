package com.example.fashion.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashion.R;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.ListResponse;
import com.example.fashion.model.Products;
import com.example.fashion.model.ReponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private Context Context;
    private List<GioHang> list;
    Double totalPrice = 0.0;
    int soLuong = 1;
    GioHangAdapter adapter;

    public GioHangAdapter(Context Context, List<GioHang> list) {
        this.Context = Context;
        this.list = list;
    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Context);
        View v = inflater.inflate(R.layout.item_giohang, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvTenSP.setText(list.get(position).getProduct_name());
        Glide.with(Context).load(list.get(position).getImg_url_product()).into(holder.imgProduct);
        holder.tvGiaTien.setText(String.valueOf(list.get(position).getPrice() + " đ"));
        holder.tvSoLuong.setText(String.valueOf(list.get(position).getQuantity()));
        holder.tvTongTien.setText(String.valueOf(list.get(position).getPrice() + " đ"));
        holder.tvSize.setText("Size: " + list.get(position).getSize());
        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong++;
                holder.tvSoLuong.setText(String.valueOf(soLuong));
                totalPrice = soLuong * list.get(position).getPrice();
                holder.tvTongTien.setText(String.valueOf(totalPrice) + "đ");
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int count= Integer.parseInt(String.valueOf(holder.tvSoLuong.getText()));
                soLuong--;
                holder.tvSoLuong.setText(String.valueOf(soLuong));
                totalPrice = list.get(position).getPrice() * soLuong;
                holder.tvTongTien.setText(String.valueOf(totalPrice) + "đ");
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(String.valueOf(list.get(position).getId_cart()));

                Toast.makeText(Context, "aaaaaaaaaaaaaaa"+ s, Toast.LENGTH_SHORT).show();
                GioHang gioHang = new GioHang("", "",
                        "", 0.0, s, 0, 0, "");
                Log.d("log","?????????"+gioHang);
                Call<ReponseModel> call = RetrofitClient.getApiClient().create(Api.class).deleteToCart(gioHang);
                call.enqueue(new Callback<ReponseModel>() {
                    @Override
                    public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {
                        if (response.isSuccessful()) {
                            notifyItemRemoved(position);
                            Toast.makeText(Context, "Delete success!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ReponseModel> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct, imgDelete;
        TextView tvTenSP, tvGiaTien, tvSoLuong, tvTongTien, tvSize;
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
            tvSize = itemView.findViewById(R.id.tvCartSize);
            imgDelete = itemView.findViewById(R.id.imgDelteItemCart);
//            tvMoTa = itemView.findViewById(R.id.card_nb_follower);
//            itemView.findViewById(R.id.imgDelteItemCart).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    adapter.list.remove(getAdapterPosition());
//                    adapter.notifyItemRemoved(getAdapterPosition());
//                }
//            });
        }
    }
//
//    private void deleteToCart(){
//        Call<ReponseModel> call = RetrofitClient.getApiClient().create(Api.class).deleteToCart(gioHang);
//        call.enqueue(new Callback<ReponseModel>() {
//            @Override
//            public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {
//                if (response.isSuccessful()) {
//                    GioHang gioHang = response.body().g;
//                    notifyItemRemoved(position);
//                    Toast.makeText(Context, "Delete success!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ReponseModel> call, Throwable t) {
//
//            }
//        });
}
//    @Override
//    public void onResume() {
//        super.onResume();
//        reloadScreen();
//    }

