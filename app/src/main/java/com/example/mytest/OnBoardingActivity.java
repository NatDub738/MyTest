package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class OnBoardingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_boarding_activity);

        AppCompatButton button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        });
        AppCompatButton button_register = findViewById(R.id.button_register);
        button_register.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
        });

    }
}
