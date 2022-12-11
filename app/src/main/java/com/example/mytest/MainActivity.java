package com.example.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ConstraintLayout home = findViewById(R.id.str_home);
        ConstraintLayout sound = findViewById(R.id.str_sound);
        ConstraintLayout profile = findViewById(R.id.str_profile);
        AppCompatButton menu_button1 = findViewById(R.id.menu_button1);
        menu_button1.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),MenuActivity.class));
        });
        AppCompatButton go_to_profile = findViewById(R.id.imageButton);
        go_to_profile.setOnClickListener(view -> {
            home.setVisibility(View.GONE);
            sound.setVisibility(View.GONE);
            profile.setVisibility(View.VISIBLE);
        });
        AppCompatButton menu_button = findViewById(R.id.menu_button);
        menu_button.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),MenuActivity.class));
        });
        AppCompatButton exit_button = findViewById(R.id.button_exit_profile);
        exit_button.setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home_str:
                            home.setVisibility(View.VISIBLE);
                            sound.setVisibility(View.GONE);
                            profile.setVisibility(View.GONE);
                            break;
                        case R.id.sound:
                            home.setVisibility(View.GONE);
                            sound.setVisibility(View.VISIBLE);
                            profile.setVisibility(View.GONE);
                            break;
                        case R.id.profile:
                            home.setVisibility(View.GONE);
                            sound.setVisibility(View.GONE);
                            profile.setVisibility(View.VISIBLE);
                            break;
                    };
                    return false;}
                    });
    };
}