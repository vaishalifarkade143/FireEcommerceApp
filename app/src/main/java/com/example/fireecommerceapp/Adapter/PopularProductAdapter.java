package com.example.fireecommerceapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fireecommerceapp.R;
import com.example.fireecommerceapp.activity.DetailedActivity;
import com.example.fireecommerceapp.models.PopularProductModel;

import java.util.List;

//62.
public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.MyViewHolder>
{
    //64.
    private Context context;
    List<PopularProductModel> popularProductModelList;

    public PopularProductAdapter(Context context, List<PopularProductModel> popularProductModelList) {
        this.context = context;
        this.popularProductModelList = popularProductModelList;
    }

    @NonNull
    @Override
    public PopularProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //65.
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductAdapter.MyViewHolder holder, int position) {
        //69.
        Glide.with(context).load(popularProductModelList.get(position).getImg_url()).into(holder.imgview);
        holder.name.setText(popularProductModelList.get(position).getName());
        holder.price.setText(String.valueOf(popularProductModelList.get(position).getPrice()));

        //85.when we click on product
        holder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",popularProductModelList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //66.
        return popularProductModelList.size();
    }

    //63.
    public class MyViewHolder extends RecyclerView.ViewHolder{
        //67.
        ImageView imgview;
        TextView name,price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //68.
            imgview = itemView.findViewById(R.id.all_img);
            name = itemView.findViewById(R.id.all_product_name);
            price = itemView.findViewById(R.id.all_price);

        }
    }
}
