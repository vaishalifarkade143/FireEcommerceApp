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
import com.example.fireecommerceapp.activity.ShowAllActivity;
import com.example.fireecommerceapp.models.CatagoryModel;

import java.util.List;

//36
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>
{
    //39.
    private Context context;
    private List<CatagoryModel> list ;

    public CategoryAdapter(Context context,List<CatagoryModel> list)
    {
        this.context = context;
        this.list = list;
    }

    //38.
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       //after creating model class
        //42.
        //glide
        Glide.with(context)
                .load(list.get(position).getImg_url())
                .into(holder.catImg);
        holder.catName.setText(list.get(position).getName());

        //108.Category on click of
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowAllActivity.class);
                intent.putExtra("type",list.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //40.
        return list.size();
    }
     //37.
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView catImg;
        TextView catName;

       //constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg = itemView.findViewById(R.id.cat_img);
            catName = itemView.findViewById(R.id.cat_name);
        }
    }
}
