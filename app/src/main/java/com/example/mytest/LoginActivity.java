package com.example.mytest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        sign_in.setOnClickListener(view -> {
            EditText emailid = findViewById(R.id.editAddress);
            EditText passwordid = findViewById(R.id.editPassword);
            String login = emailid.getText().toString();
            String password = passwordid.getText().toString();
            UserLoginModel user_login_model = new UserLoginModel(login, password);

            if (login.length() == 0) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Проверте почту! Вы оставили это поле пустым!",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
            if (!(login.contains("@") && login.length() > 5)) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Проверте почту! Необходимо присутсвие @ и минимум 5 других символов!",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;
            } if (password.length() == 0) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Проверте пароль! Вы оставили поле пустым!",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://mskko2021.mad.hakta.pro/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofit.create(APIInt.class).user_login_response(user_login_model).enqueue(
                    new Callback<UserLoginResponse>() {
                        @Override
                        public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                            if (response.isSuccessful()) {
                                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                                main.putExtra("userInfo", response.body());

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                SharedPreferences.Editor preferencesEditor = preferences.edit();
                                preferencesEditor.putString("email", login);
                                preferencesEditor.putString("password", password);
                                preferencesEditor.commit();

                                startActivity(main);
                            } else {
                                Toast.makeText(LoginActivity.this, "Введен неверный имейл или пароль", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);

                            dialogBuilder.setCancelable(true)
                                    .setTitle("Ошибка сервера")
                                    .setMessage(t.getMessage());

                            AlertDialog errDialog = dialogBuilder.create();

                            errDialog.show();
                        }
                    });


        });

    }
}