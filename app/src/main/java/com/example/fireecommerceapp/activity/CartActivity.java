package com.example.fireecommerceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fireecommerceapp.Adapter.MyCartAdapter;
import com.example.fireecommerceapp.R;
import com.example.fireecommerceapp.models.MyCartModel;
import com.example.fireecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    //137.Totl amount getting
    int overAllTotalAmount ;
    TextView overAllAmount;

//132.
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<MyCartModel> cartModelList;
    MyCartAdapter myCartAdapter;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //133.
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //for toolbar
        toolbar =  findViewById(R.id.my_cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //176.press on back arrow of toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


              //137* Totl amount getting///getting data from CartAdapter
                LocalBroadcastManager.getInstance(this)
                        .registerReceiver(broadcastReceiver,new IntentFilter("MyTotalAmount"));
                overAllAmount = findViewById(R.id.textView);//total Amount

        //for Recycler view
        recyclerView = findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
          //get the list first then set on Adpter
            cartModelList = new ArrayList<>();
            //now add list to adapter using constuctor
            myCartAdapter = new MyCartAdapter(this,cartModelList);
            //now list we have get in Adapter Class refernce now set the ref variable on Recycler view
        recyclerView.setAdapter(myCartAdapter);

        //134.
        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //135.
                        if (task.isSuccessful())
                        {
                            for (DocumentSnapshot doc : task.getResult().getDocuments())
                            {

                                MyCartModel myCartModel = doc.toObject(MyCartModel.class);
                                cartModelList.add(myCartModel);
                                myCartAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                });
    }

    //138.Totl amount getting
  public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //139.
            int totalBill = intent.getIntExtra("totalAmount",0);
            overAllAmount.setText("Total Amount : Rs" +totalBill);//now create address_activity.xml
        }
    };

}