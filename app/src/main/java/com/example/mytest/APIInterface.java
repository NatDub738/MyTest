package com.example.mytest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("user/login")
    Call<UserLoginResponse> user_login_response(@Body UserLoginModel user_login_model);
}
