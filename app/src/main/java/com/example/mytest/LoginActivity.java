package com.example.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        AppCompatButton button_register_in_login = findViewById(R.id.button_register_in_login);
        button_register_in_login.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
        });


        AppCompatButton sign_in = findViewById(R.id.button_sign_in);
        sign_in.setOnClickListener(view ->{
            EditText emailid = findViewById(R.id.editAddress);
            EditText passwordid = findViewById(R.id.editPassword);
            String email = emailid.getText().toString();
            String password = passwordid.getText().toString();
        if(email.contains("@") && email.length()>5 && password.length()>0){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }else if(password.length()==0){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Проверте пароль! Вы оставили поле пустым!",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }else if(!(email.contains("@") && email.length()>5)){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Проверте почту! Необходимо присутсвие @ и ещё минимум 5 символов!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        });
    }
}
