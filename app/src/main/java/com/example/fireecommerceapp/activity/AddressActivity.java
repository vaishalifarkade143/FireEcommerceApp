package com.example.fireecommerceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fireecommerceapp.Adapter.AddressAdapter;
import com.example.fireecommerceapp.R;
import com.example.fireecommerceapp.models.AddressModel;
import com.example.fireecommerceapp.models.MyCartModel;
import com.example.fireecommerceapp.models.NewProductModel;
import com.example.fireecommerceapp.models.PopularProductModel;
import com.example.fireecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
                                                        //157.implements AddressAdapter.SelectedAddress
public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress{
//148.
    Button addAdress;
    //150.
    RecyclerView recyclerView;
    private List<AddressModel> addressModelsList;
    private AddressAdapter addressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    Button paymentContinueBtn;
    Toolbar toolbar;
            //160.
             String mAddress = "";//now create Payment Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        //150*
        //for toolbar
        toolbar =  findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //176.press on back arrow of toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

                 //165. to get data from detailed Activity
                 Object obj = getIntent().getSerializableExtra("item");


        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        //RecyclerView
        recyclerView = findViewById(R.id.address_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                //156.
                addressModelsList = new ArrayList<>();
                addressAdapter = new AddressAdapter(getApplicationContext(),addressModelsList,this);
                recyclerView.setAdapter(addressAdapter);
                        //159.
                        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful())
                                        {
                                            for (DocumentSnapshot doc : task.getResult().getDocuments())
                                            {

                                                AddressModel addressModel = doc.toObject(AddressModel.class);
                                                addressModelsList.add(addressModel);
                                                addressAdapter.notifyDataSetChanged();

                                            }
                                        }
                                    }
                                });

        //PaymentButton
        paymentContinueBtn = findViewById(R.id.continue_btn);
        paymentContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(AddressActivity.this,PaymentActivity.class));
                //166.
                double amount = 0.0;
                if (obj instanceof NewProductModel)
                {
                    NewProductModel newProductModel = (NewProductModel) obj;
                    amount = newProductModel.getPrice();
                }
                if (obj instanceof PopularProductModel)
                {
                    PopularProductModel popularProductModel = (PopularProductModel) obj;
                    amount = popularProductModel.getPrice();
                }
                if (obj instanceof ShowAllModel)
                {
                    ShowAllModel showAllModel = (ShowAllModel) obj;
                    amount = showAllModel.getPrice();
                }
                Intent intent = new Intent(AddressActivity.this,PaymentActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);

            }
        });//AddressAdapter create now 151.

        //148.*
        addAdress= findViewById(R.id.btn_add_address_btn);

        addAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
            }
        });

    }
    //158. overriden methods
       @Override
       public void setAddress(String address) {
            mAddress = address ;
        }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
