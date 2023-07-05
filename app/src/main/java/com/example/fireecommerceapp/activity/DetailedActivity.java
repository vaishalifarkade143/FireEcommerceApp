package com.example.fireecommerceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fireecommerceapp.R;
import com.example.fireecommerceapp.models.NewProductModel;
import com.example.fireecommerceapp.models.PopularProductModel;
import com.example.fireecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    //80.
    ImageView detailedImage;
    TextView rating,name,descri,price;
                //115.plus minus
                TextView quantity;
    Button addToCart,buyNow;
    ImageView addItem,removeItem;

    //125.toolbar
    Toolbar toolbar;

    //117.
    int totalQuantity = 1;//
    //118.when we add or remove the product that time change in ammount
    int totalPrice = 0;//


    //82.New Product
    NewProductModel newProductModel = null;//New Product//
    private FirebaseFirestore firestore;

    //86 .Popular Product
    PopularProductModel popularProductModel = null;
    //106.show All
    ShowAllModel showAllModel = null ;
    //114.
    FirebaseAuth auth;//



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        //125.*
        toolbar = findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//now add toobar in activity_show_all.xml
                                                            //now go to ShowallActivity for 126.

        //176.press on back arrow of toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //82*.
        firestore = FirebaseFirestore.getInstance();
        //114.*
        auth = FirebaseAuth.getInstance();
        final Object obj = getIntent().getSerializableExtra("detailed");//getting from 79.
        if(obj instanceof NewProductModel)
        {
            newProductModel = (NewProductModel) obj;
        }
        //86.*
        else if(obj instanceof PopularProductModel)
        {
            popularProductModel = (PopularProductModel) obj;
        }
        //107.
        else if(obj instanceof ShowAllModel)
        {
            showAllModel = (ShowAllModel) obj;
        }

        //81.
        detailedImage = findViewById(R.id.detailed_img);
        name = findViewById(R.id.detailed_name);
        rating = findViewById(R.id.rating);
        descri = findViewById(R.id.desc);
        price = findViewById(R.id.detailed_price);//
            //115*
                quantity = findViewById(R.id.quantity);//

        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);

        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.minus_item);

        //83.New Product
        if(newProductModel != null)
        {
                    Glide.with(getApplicationContext()).load(newProductModel.getImg_url()).into(detailedImage);
                    name.setText(newProductModel.getName());
                    rating.setText(newProductModel.getRating());
                    descri.setText(newProductModel.getDescription());
            Log.d("desc Not print=========","say:" +descri.toString());
                    price.setText(String.valueOf(newProductModel.getPrice()));
                    name.setText(newProductModel.getName());

                    //119. when we add or remove the product that time change in ammount
            totalPrice = newProductModel.getPrice() * totalQuantity;

        }

        //87.Popular Product
                if(popularProductModel != null)
                {
                            Glide.with(getApplicationContext()).load(popularProductModel.getImg_url()).into(detailedImage);
                            name.setText(popularProductModel.getName());
                            rating.setText(popularProductModel.getRating());
                            descri.setText(popularProductModel.getDescription());
                    Log.d("desc Not print=========","say:" +descri.toString());
                            price.setText(String.valueOf(popularProductModel.getPrice()));
                            name.setText(popularProductModel.getName());

                    //119. when we add or remove the product that time change in ammount
                    totalPrice = popularProductModel.getPrice() * totalQuantity;

                }//now we work on See All of Category,Popular,NewProduct check HomeFragment Now//
        //107.Show All
        if(showAllModel != null)
        {
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImage);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            descri.setText(showAllModel.getDescription());
            Log.d("desc Not print=========","say:" +descri.toString());
            price.setText(String.valueOf(showAllModel.getPrice()));
            name.setText(showAllModel.getName());

            //119. when we add or remove the product that time change in ammount
            totalPrice = showAllModel.getPrice() * totalQuantity;
        }
        //147.Buy Now Button
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(DetailedActivity.this,AddressActivity.class);
                //163.
                Intent intent = new Intent(DetailedActivity.this,AddressActivity.class);
                        if (newProductModel != null)
                        {
                            intent.putExtra("item",newProductModel);
                        }
                        if (popularProductModel !=null)
                        {
                            intent.putExtra("item",popularProductModel);
                        }
                        if (showAllModel !=null)
                        {
                            intent.putExtra("item",showAllModel);
                        }
                startActivity(intent);
            }
        });


        //111.Add to Cart Button
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addToCart();
            }
        });

        //116.plus /addItem
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10 )
                {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                    //120. when we add or remove the product that time change in ammount
                    if (newProductModel != null)
                    {
                        totalPrice = newProductModel.getPrice() * totalQuantity;
                    }
                    if (popularProductModel != null)
                    {
                        totalPrice = popularProductModel.getPrice() * totalQuantity;
                    }
                    if (showAllModel != null)
                    {
                        totalPrice = showAllModel.getPrice() * totalQuantity;
                    }//now created tool bar using resource File //121.

                }


            }
        });
        //minus/remove items
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1 )
                {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));

                    //120. when we add or remove the product that time change in ammount
                }
            }
        });


    }
//112.Add To Cart
    private void addToCart() {
        //get current  the date n time
        String saveCurrentTime,saveCurrentDate;
        //for date
        Calendar calforDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate = currentDate.format(calforDate.getTime());

       //for time
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calforDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentDate);//

        //121. when we add or remove the product that time change in amount
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        //113.to create AddTo Cart table in Firestore cloud
        firestore.collection("AddToCart")
                .document(auth.getUid())
                .collection("User").add(cartMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this, "Added to The Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}