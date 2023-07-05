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
import com.example.fireecommerceapp.models.NewProductModel;

import java.util.List;


//49.
public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.MyViewHolder> {

    //55.
    private Context context;
    private List<NewProductModel> list;
    //constructor to fetch the values to other classes
    public NewProductAdapter(Context context, List<NewProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NewProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //53.
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_product,parent,false));//now create NewProductModel
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductAdapter.MyViewHolder holder, int position)
    {
        //56.
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.newImg);
        holder.newName.setText(list.get(position).getName());
        holder.newPrice.setText(String.valueOf(list.get(position).getPrice()));

        //76.when we click on any Product
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //77. get the intent of any product which has been clicked n redirect to the DetailedActivity page
                Intent intent = new Intent(context, DetailedActivity.class);
                        //79. putExtra() and getExtra() for passing and retrieving string data in the android application from pass data from one activity to another for performing some operations.
                            intent.putExtra("detailed",list.get(position));//

                context.startActivity(intent);//now implements Serializable to NewProductModel

            }
        });

    }

    @Override
    public int getItemCount() {
        //57.
        return list.size();
    }

    //50
    public class MyViewHolder extends RecyclerView.ViewHolder{
        //51.
        ImageView newImg;
        TextView newName,newPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //52.
            newImg = itemView.findViewById(R.id.new_img);
            newName = itemView.findViewById(R.id.new_product_name);
            newPrice = itemView.findViewById(R.id.new_price);

        }
    }
}
