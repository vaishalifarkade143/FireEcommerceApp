package com.example.fireecommerceapp.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fireecommerceapp.R;
import com.example.fireecommerceapp.models.AddressModel;

import java.util.List;

//151.
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {
    Context context;
    List<AddressModel> list;
    //154
    SelectedAddress selectedAddress;
    private  RadioButton selectedRadioBtn;
    //constructor
    public AddressAdapter(Context context, List<AddressModel> list, SelectedAddress selectedAddress) {
        this.context = context;
        this.list = list;
        this.selectedAddress = selectedAddress;
    }

    @NonNull
    @Override
    public AddressAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.MyViewHolder holder, int position) {
        //155.
        holder.addressAdd.setText(list.get(position).getUserAddress());
        holder.radioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (AddressModel address : list)
                {
                    address.setSelected(false);
                }
                list.get(position).setSelected(true);
                if (selectedRadioBtn !=null)
                {
                    selectedRadioBtn.setChecked(false);
                }
                selectedRadioBtn= (RadioButton) v;
                selectedRadioBtn.setChecked(true);
                selectedAddress.setAddress(list.get(position).getUserAddress());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //152.
        TextView addressAdd;
        RadioButton radioBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            addressAdd = itemView.findViewById(R.id.address_add);
            radioBtn = itemView.findViewById(R.id.select_address);

        }
    }
    //153.
    public interface SelectedAddress
    {
        void setAddress(String address);
    }
}
