package com.example.fireecommerceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    //12.
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //1.to hide Action Bar
       // getSupportActionBar().hide();

        //13. get the instance of firebase
        auth = FirebaseAuth.getInstance();//

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

    }
    //5.
    public  void signIn(View view)
    {
        //14.get the values on field/form
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        //condition  if field is empty
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
        //15. FireBase Authentication for sign in
        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Login Failed!!"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //6.
    public  void signUp(View view)
    {
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }
}