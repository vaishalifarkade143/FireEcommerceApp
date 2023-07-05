package com.example.fireecommerceapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fireecommerceapp.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;
                                                        //174.implements PaymentResultListener
public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    //167.
    double amount  = 0.0;
    //161
   Toolbar toolbar;
   //162.
   TextView subTotal,discount,shippping,total;

   //171.RazorPay
    Button paymentBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //161*for toolbar
        toolbar =  findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //176.press on back arrow of toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
                //167.*
                    amount= getIntent().getDoubleExtra("amount" ,0.0);
                    //162.*
        subTotal = findViewById(R.id.sub_total);
        discount = findViewById(R.id.discount);
        shippping = findViewById(R.id.shipping);
        total = findViewById(R.id.total);
                //171*
                paymentBtn = findViewById(R.id.payment_btn);

                //167*
        subTotal.setText(amount+ "Rs. ");

        //172.
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentMethod();
            }
        });

    }
//173.
    private void paymentMethod() {
        Checkout checkout = new Checkout();
        final Activity activity = PaymentActivity.this;
        try
        {
            JSONObject option = new JSONObject();
            //Set Company Name
            option.put("name" ,"My Firebase Eccomerce App");
            //Ref no
            option.put("description","Reference No. #123456");
            //Image to be display
            option.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//            option.put("order_id","order_9A33XWu170gUtm");
            //Currency type
            option.put("currency","USD");
//            double total = Double.parseDouble(mAmountText.getText().toString());
            //multiply with 100 to get exact amount in rupee
            amount = amount * 100;
            //amount
            option.put("amount",amount);
            JSONObject preFill = new JSONObject();
            //email
            preFill.put("email", "developer.vaishalirau23@gmail.com");
            //contact
            preFill.put("contact","8657487971");

            option.put("preFill" ,preFill);
            checkout.open(activity,option);

        }
        catch (Exception e)
        {
            Log.e("TAG","Error in starting Razorpay checkout", e);
        }
    }
                                                            //175.

                                                            @Override
                                                            public void onPaymentSuccess(String s) {
                                                                Toast.makeText(this, "payment successful", Toast.LENGTH_SHORT).show();
                                                            }

                                                            @Override
                                                            public void onPaymentError(int i, String s) {
                                                                Toast.makeText(this, "payment Failed", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }