package com.example.venril.mobileinternshiptask.ApiData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UserApiService {

    @GET("users/{user}")
    Call<UserModel> getUserInfo(@Path("user") String user);
}
