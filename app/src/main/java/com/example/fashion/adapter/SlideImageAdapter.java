package com.example.fashion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.fashion.R;
import com.example.fashion.model.Images;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SlideImageAdapter extends RecyclerView.Adapter<SlideImageAdapter.ViewHolder> {
    private List<Images> list;
    Context context;

    public SlideImageAdapter(List<Images> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public SlideImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_slideimage,parent,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Glide.with(context).load(list.get(position).getImage_url()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);

        }
    }

}
