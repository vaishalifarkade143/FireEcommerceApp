package com.example.fireecommerceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fireecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//141
public class AddAddressActivity extends AppCompatActivity {
   //141*
    EditText name,address,city,postalcode,phoneNumber;
    Toolbar toolbar;
    Button addAddress;
    //145.
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        toolbar = findViewById(R.id.add_address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //176.press on back arrow of toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //145*
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //141.**
        name = findViewById(R.id.add_name);
        address = findViewById(R.id.add_adress_line);
        city = findViewById(R.id.add_city);
        postalcode = findViewById(R.id.add_postal);
        phoneNumber = findViewById(R.id.add_phone);
        addAddress = findViewById(R.id.ad_address_btn);

        //142.
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //142* get the field values
                String username = name.getText().toString();
                String useraddress = address.getText().toString();
                String usercity = city.getText().toString();
                String userpostal = postalcode.getText().toString();
                String usernumber = phoneNumber.getText().toString();

                //143.
                String final_adrress = "";
                if (!username.isEmpty())
                {
                    final_adrress+= username+", ";
                }
                if (!useraddress.isEmpty())
                {
                    final_adrress+= useraddress+", ";
                }
                if (!usercity.isEmpty())
                {
                    final_adrress+= usercity+", ";
                }
                if (!userpostal.isEmpty())
                {
                    final_adrress+= userpostal+", ";
                }
                if (!usernumber.isEmpty())
                {
                    final_adrress+= usernumber+". ";
                }

                //144.
                if (!username.isEmpty() && !useraddress.isEmpty() && !usercity.isEmpty() && !userpostal.isEmpty() && !usernumber.isEmpty())
                {
                    Map<String,String> map = new HashMap<>();
                    map.put("userAddress",final_adrress);
                    //146. to create table in FireStoreDatabase
                    firestore.collection("CurrentUser")
                            .document(auth.getCurrentUser().getUid())
                            .collection("Address")
                            .add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
                                        //168.
                                        startActivity(new Intent(AddAddressActivity.this, DetailedActivity.class));
                                        finish();//now add rayzor pay depedency
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(AddAddressActivity.this, "Address Not Added", Toast.LENGTH_SHORT).show();
                }//now go to DetailedActivity

            }
        });
    }
}