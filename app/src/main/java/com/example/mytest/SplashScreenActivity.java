package com.example.mytest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SplashScreenActivity.this);
                String email = preferences.getString("email", "");
                String password = preferences.getString("password","");
                UserLoginModel user_login_model = new UserLoginModel(email,password);
                if(email.length()==0 || password.length()==0){
                    startActivity(new Intent(getApplicationContext(),OnBoardingActivity.class));
                }
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://mskko2021.mad.hakta.pro/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                retrofit.create(APIInt.class).user_login_response(user_login_model).enqueue(
                        new Callback<UserLoginResponse>() {
                            @Override
                            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("UserInfo",response.body());
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Проверте подключение к интернету!/n Невозможно подключиться к серверу!",
                                        Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
                            }
                        }
                );
            }
        }, 2000);
    }
}