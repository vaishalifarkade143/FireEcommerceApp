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
import com.example.fireecommerceapp.models.ShowAllModel;

import java.util.List;

//93.
public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.MyViewHolder> {
    Context context;
    List<ShowAllModel> list;
    //99 constructor
     public ShowAllAdapter(Context context, List<ShowAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShowAllAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //94.
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllAdapter.MyViewHolder holder, int position) {
        //98.
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.itemImg);
        holder.itemName.setText(list.get(position).getName());
        holder.itemcost.setText("Rs. "+list.get(position).getPrice());

        //104.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //95.
        return list.size();
    }

    //94.
    public class MyViewHolder extends RecyclerView.ViewHolder{
        //96.
      private ImageView itemImg;
      private TextView itemcost,itemName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //97
            itemImg = itemView.findViewById(R.id.img_items);
            itemcost = itemView.findViewById(R.id.item_cost);
            itemName = itemView.findViewById(R.id.item_name);
        }
    }
}
