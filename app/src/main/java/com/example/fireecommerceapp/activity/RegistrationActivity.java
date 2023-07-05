package com.example.fireecommerceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fireecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    EditText name,email,password;

    //step 8 do the setting of fireBase Authentication from Tools//
    //9.
    private FirebaseAuth auth;
    //31.
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //2.to hide Action Bar
//        getSupportActionBar().hide();
        //10. get the instance of firebase
        auth = FirebaseAuth.getInstance();//

        //16.
        if (auth.getCurrentUser() != null)
        {
            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            finish();
        }

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        //31* when we slide the screen dot also change
        sharedPreferences = getSharedPreferences("OnBoardingScreen",MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("firsttime",true);
        if (isFirstTime)
        {
            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putBoolean("firsttime",false);
            editor.commit();

            Intent intent = new Intent(RegistrationActivity.this,OnBoardingActivity.class);
            startActivity(intent);
            finish();
        }
    }
    //3.
    public  void signupNow(View view)
    {
        //7.get the values on field/form
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        //condition  if field is empty
        if(TextUtils.isEmpty(userName))
        {
            Toast.makeText(this, "Enter Name !", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail))
        {
            Toast.makeText(this, "Enter Email !", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword))
        {
            Toast.makeText(this, "Enter Password !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 6)
        {
            Toast.makeText(this, "Password is too Short,enter Minimum 6 character", Toast.LENGTH_SHORT).show();
            return;
        }

        //11.to  create table in FireBaseAuth using Email n Password and call OnCompleteListener
        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(RegistrationActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(RegistrationActivity.this, "Registretion Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });//

    }
    //4.
    public  void signinNow(View view)
    {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }
}