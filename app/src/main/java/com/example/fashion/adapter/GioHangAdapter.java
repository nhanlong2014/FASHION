package com.example.fashion.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashion.R;
import com.example.fashion.api.Api;
import com.example.fashion.api.RetrofitClient;
import com.example.fashion.model.GioHang;
import com.example.fashion.model.ListResponse;
import com.example.fashion.model.Products;
import com.example.fashion.model.ReponseModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private Context Context;
    private List<GioHang> list = new ArrayList<>();
    int totalPriceAll = 0;
    GioHangAdapter adapter;

    public GioHangAdapter(Context Context, List<GioHang> list) {
        this.Context = Context;
        this.list = list;
    }


    public void delete(int position) { //removes the row
        list.remove(position);
        notifyItemRemoved(position);
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
        holder.tvTongTien.setText(String.valueOf(list.get(position).getTotal_price() + " đ"));
        holder.tvSize.setText("Size: " + list.get(position).getSize());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_Cart = Integer.parseInt(String.valueOf(list.get(position).getId_cart()));
                GioHang gioHang = new GioHang("", "",
                        "", 0, id_Cart,0, 0, "", 0);
                try {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Context);
                    alertDialogBuilder.setMessage("Are you sure,You wanted to Remove\n");
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Call<ReponseModel> call = RetrofitClient.getApiClient().create(Api.class).deleteToCart(gioHang);
                            call.enqueue(new Callback<ReponseModel>() {
                                @Override
                                public void onResponse(Call<ReponseModel> call, Response<ReponseModel> response) {
                                    if (response.isSuccessful()) {
                                        delete(position);
                                        ((Activity) Context).recreate();


//                                        Handler handler = new Handler();
//                                        handler.postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Toast.makeText(Context, "Delete success!", Toast.LENGTH_SHORT).show();
//
//                                            }
//                                        },0);

                                    }
                                }

                                @Override
                                public void onFailure(Call<ReponseModel> call, Throwable t) {

                                }
                            });
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } catch (Exception e) {
                }

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

