package com.example.fireecommerceapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.fireecommerceapp.R;

public class sliderAdapter extends PagerAdapter
{
    Context context;
        //22.
    LayoutInflater layoutInflater;

        //17.constructor
    public sliderAdapter(Context context)
    {
        this.context = context;
    }
    //18.
    int imagesArray[] = {R.drawable.fuel,R.drawable.mob,R.drawable.cart};
    int headingArray[] = {R.string.first_slide,R.string.second_slide,R.string.third_slide};
    int descriptionArray[] = {R.string.decsription,R.string.decsription,R.string.decsription};

    @Override
    public int getCount() {
        //19.
        return headingArray.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        //20.
        return view == (ConstraintLayout)object;
    }

    //21.alt insert to override method
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        //getting the layout view of slider
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliding_layout,container,false);
        ImageView imagev = view.findViewById(R.id.img_slider);
        TextView heading = view.findViewById(R.id.heading);
        TextView description = view.findViewById(R.id.description);

        imagev.setImageResource(imagesArray[position]);
        heading.setText(headingArray[position]);
        description.setText(descriptionArray[position]);
        container.addView(view);

        return view;

    }
    //22.

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
